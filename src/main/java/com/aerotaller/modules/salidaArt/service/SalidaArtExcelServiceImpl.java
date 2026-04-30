package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.DetalleSalidaArt;
import com.aerotaller.modelos.SalidaArt;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.salidaArt.dto.SalidaArtExportRequestDto;
import com.aerotaller.modules.salidaArt.repository.SalidaArtRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SalidaArtExcelServiceImpl implements SalidaArtExcelService {

    private final SalidaArtRepository repository;
    private final CondicionRepository condicionRepository;
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public SalidaArtExcelServiceImpl(SalidaArtRepository repository, CondicionRepository condicionRepository) {
        this.repository = repository;
        this.condicionRepository = condicionRepository;
    }

    @Override
    public byte[] generarExcelSalida(Integer idSalida, SalidaArtExportRequestDto dto) {
        SalidaArt salida = repository.findById(idSalida)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada."));

        try (InputStream is = new ClassPathResource("templates/AG-SA-26-000_2.xlsx").getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(is);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            // 1. DATOS DE ENCABEZADO
            // Destinatario: E9 (8, 4)
            setCellValue(sheet, 8, 4, safe(salida.getDestinatario()));

            // Fecha Creación: E15 (14, 4)
            String fechaSalida = salida.getFecha() != null ? salida.getFecha().format(fmt) : "";
            setCellValue(sheet, 14, 4, fechaSalida);

            // Referencia: E16 (15, 4)
            setCellValue(sheet, 15, 4, "");

            // 2. TABLA DE DETALLES (Fila 21 -> Índice 20)
            List<DetalleSalidaArt> detalles = salida.getDetalles();
            int filaInicio = 20;

            for (int i = 0; i < detalles.size(); i++) {
                Row row = getOrCreateRow(sheet, filaInicio + i);
                DetalleSalidaArt det = detalles.get(i);
                Articulo art = det.getArticulo();

                setCellValue(row, 0, i + 1);                       // A: NO
                setCellValue(row, 1, det.getCantidad());           // B: QTY
                setCellValue(row, 3, art != null ? safe(art.getDescripcion()) : ""); // D: DESCRIPCIÓN
                setCellValue(row, 10, art != null ? safe(art.getCodigo()) : "");     // K: NO. PARTE
                setCellValue(row, 15, art != null ? safe(art.getNoSerie()) : "");    // P: NO. SERIE
                setCellValue(row, 20, obtenerAbreviacionCondicion(art));             // U: CONDICIÓN
                setCellValue(row, 23, "");                                          // X: OBSERVACIÓN
            }

            // 3. SECCIÓN DE FIRMAS Y FECHAS (Fila 47 y 48)
            // Nombres (Fila 47 -> 46)
            setCellValue(sheet, 46, 3, safe(dto.getEncargadoAlmacen())); // D47
            setCellValue(sheet, 46, 13, safe(dto.getTraslada()));        // N47
            setCellValue(sheet, 46, 23, safe(dto.getRecibe()));          // X47

            // Fechas de Firmas (Fila 48 -> 47)
            setCellValue(sheet, 47, 3, formatearFechaIso(dto.getFechaEncargado())); // D48
            setCellValue(sheet, 47, 13, formatearFechaIso(dto.getFechaTraslada())); // N48
            setCellValue(sheet, 47, 23, formatearFechaIso(dto.getFechaRecibe()));    // X48

            workbook.write(baos);
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar Excel: " + e.getMessage());
        }
    }

    // --- MÉTODOS DE APOYO ---

    private String obtenerAbreviacionCondicion(Articulo articulo) {
        if (articulo == null || articulo.getCondicion() == null) return "";
        return condicionRepository.findById(articulo.getCondicion())
                .map(c -> {
                    String n = c.getNombre();
                    if (n != null && n.contains("(") && n.contains(")")) {
                        return n.substring(n.indexOf("(") + 1, n.indexOf(")"));
                    }
                    return (n != null && n.length() > 3) ? n.substring(0, 2) : safe(n);
                }).orElse("");
    }

    private String formatearFechaIso(String fechaIso) {
        if (fechaIso == null || fechaIso.isBlank()) return "";
        try {
            return java.time.LocalDate.parse(fechaIso).format(fmt);
        } catch (Exception e) {
            return fechaIso;
        }
    }

    private void setCellValue(XSSFSheet sheet, int rowIdx, int colIdx, String val) {
        Row row = getOrCreateRow(sheet, rowIdx);
        getOrCreateCell(row, colIdx).setCellValue(val != null ? val : "");
    }

    private void setCellValue(Row row, int colIdx, Object val) {
        Cell cell = getOrCreateCell(row, colIdx);
        if (val instanceof Integer) cell.setCellValue((Integer) val);
        else cell.setCellValue(val != null ? val.toString() : "");
    }

    private Row getOrCreateRow(XSSFSheet sheet, int idx) {
        Row row = sheet.getRow(idx);
        return row != null ? row : sheet.createRow(idx);
    }

    private Cell getOrCreateCell(Row row, int idx) {
        Cell cell = row.getCell(idx);
        return cell != null ? cell : row.createCell(idx);
    }

    private String safe(String s) { return s != null ? s : ""; }
}