package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.Condicion;
import com.aerotaller.modelos.DetalleEntradaArticulo;
import com.aerotaller.modelos.EntradaArticulo;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.entradaart.repository.EntradaArticuloRepository;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloExportRequestDto;
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
import java.util.Optional;

@Service
public class EntradaArticuloExcelServiceImpl implements EntradaArticuloExcelService
{

    private static final int FILA_INICIO_DETALLES = 6;  // Excel row 7
    private static final int FILA_FIN_DETALLES = 45;    // Excel row 46
    private static final int TOTAL_FILAS = 40;

    private final EntradaArticuloRepository entradaArticuloRepository;
    private final CondicionRepository condicionRepository;

    public EntradaArticuloExcelServiceImpl(
            EntradaArticuloRepository entradaArticuloRepository,
            CondicionRepository condicionRepository
    )
    {
        this.entradaArticuloRepository = entradaArticuloRepository;
        this.condicionRepository = condicionRepository;
    }

    @Override
    public byte[] generarExcelEntrada(Integer idEntrada, EntradaArticuloExportRequestDto exportDto)
    {
        EntradaArticulo entrada = entradaArticuloRepository.findById(idEntrada)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada."));

        try (
                InputStream templateStream = new ClassPathResource("templates/PlantillaEntrada.xlsx").getInputStream();
                XSSFWorkbook workbook = new XSSFWorkbook(templateStream);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        )
        {
            XSSFSheet sheet = workbook.getSheetAt(0);

            llenarEncabezado(sheet, entrada);
            limpiarTabla(sheet);
            llenarDetalles(sheet, entrada.getDetalles(), entrada);
            limpiarFirmas(sheet);
            llenarFirmas(sheet, exportDto);

            workbook.write(outputStream);
            return outputStream.toByteArray();

        } catch (Exception e)
        {
            throw new RuntimeException("Error al generar el Excel de la entrada: " + e.getMessage(), e);
        }
    }

    private void limpiarFirmas(XSSFSheet sheet)
    {
        clearCell(getOrCreateRow(sheet, 58), 1); // B59
        clearCell(getOrCreateRow(sheet, 59), 1); // B60

        clearCell(getOrCreateRow(sheet, 58), 3); // D59
        clearCell(getOrCreateRow(sheet, 59), 3); // D60

        clearCell(getOrCreateRow(sheet, 58), 5); // F59
        clearCell(getOrCreateRow(sheet, 59), 5); // F60
    }

    private void llenarEncabezado(XSSFSheet sheet, EntradaArticulo entrada)
    {
        String fecha = entrada.getFechaEntrada() != null
                ? entrada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                : "";

        String proveedor = entrada.getProveedor() != null ? safe(entrada.getProveedor().getNombre()) : "";
        String folio = safe(entrada.getFolio());
        String observaciones = safe(entrada.getObservaciones());

        setCellValue(sheet, 3, 0, "Fecha: " + fecha); // A4
        setCellValue(sheet, 3, 2, "Proveedor: " + proveedor); // C4
        setCellValue(sheet, 3, 7, "FOLIO: " + folio); // H4
        setCellValue(sheet, 4, 0, "Observaciones: " + observaciones); // A5
        setCellValue(sheet, 4, 5, "Departamento: Almacén."); // F5
    }

    private void limpiarTabla(XSSFSheet sheet)
    {
        for (int rowIndex = FILA_INICIO_DETALLES; rowIndex <= FILA_FIN_DETALLES; rowIndex++)
        {
            Row row = getOrCreateRow(sheet, rowIndex);

            clearCell(row, 1); // B qty
            clearCell(row, 2); // C descripcion
            clearCell(row, 3); // D numero parte
            clearCell(row, 4); // E numero serie
            clearCell(row, 5); // F condicion
            clearCell(row, 6); // G proveedor
            clearCell(row, 7); // H observaciones
        }
    }

    private void llenarDetalles(XSSFSheet sheet, List<DetalleEntradaArticulo> detalles, EntradaArticulo entrada)
    {
        int limite = Math.min(detalles != null ? detalles.size() : 0, TOTAL_FILAS);

        for (int i = 0; i < limite; i++)
        {
            int rowIndex = FILA_INICIO_DETALLES + i;
            Row row = getOrCreateRow(sheet, rowIndex);

            DetalleEntradaArticulo detalle = detalles.get(i);
            Articulo articulo = detalle.getArticulo();

            setCellValue(row, 1, detalle.getCantidad()); // B
            setCellValue(row, 2, articulo != null ? safe(articulo.getDescripcion()) : ""); // C
            setCellValue(row, 3, articulo != null ? safe(articulo.getCodigo()) : ""); // D
            setCellValue(row, 4, articulo != null ? safe(articulo.getNoSerie()) : ""); // E
            setCellValue(row, 5, obtenerNombreCondicion(articulo)); // F
            setCellValue(row, 6, entrada.getProveedor() != null ? safe(entrada.getProveedor().getNombre()) : ""); // G
            setCellValue(row, 7, ""); // H
        }
    }

    private String obtenerNombreCondicion(Articulo articulo)
    {
        if (articulo == null || articulo.getCondicion() == null)
        {
            return "";
        }

        Optional<Condicion> condicion = condicionRepository.findById(articulo.getCondicion());
        return condicion.map(Condicion::getNombre).orElse("");
    }

    private Row getOrCreateRow(XSSFSheet sheet, int rowIndex)
    {
        Row row = sheet.getRow(rowIndex);
        return row != null ? row : sheet.createRow(rowIndex);
    }

    private Cell getOrCreateCell(Row row, int colIndex)
    {
        Cell cell = row.getCell(colIndex);
        return cell != null ? cell : row.createCell(colIndex);
    }

    private void setCellValue(XSSFSheet sheet, int rowIndex, int colIndex, String value)
    {
        Row row = getOrCreateRow(sheet, rowIndex);
        setCellValue(row, colIndex, value);
    }

    private void setCellValue(Row row, int colIndex, String value)
    {
        Cell cell = getOrCreateCell(row, colIndex);
        cell.setCellValue(value != null ? value : "");
    }

    private void setCellValue(Row row, int colIndex, Integer value)
    {
        Cell cell = getOrCreateCell(row, colIndex);
        if (value == null)
        {
            cell.setBlank();
        }
        else
        {
            cell.setCellValue(value);
        }
    }

    private void clearCell(Row row, int colIndex)
    {
        Cell cell = getOrCreateCell(row, colIndex);
        cell.setBlank();
    }

    private String safe(String value)
    {
        return value != null ? value : "";
    }

    private void llenarFirmas(XSSFSheet sheet, EntradaArticuloExportRequestDto dto)
    {
        if (dto == null) return;

        String fechaEncargado = formatearFecha(dto.getFechaEncargado());
        String fechaTraslada = formatearFecha(dto.getFechaTraslada());
        String fechaRecibe = formatearFecha(dto.getFechaRecibe());

        // ENCARGADO DE ALMACEN
        setCellValue(sheet, 58, 1, "NOMBRE/FIRMA:" + safe(dto.getEncargadoAlmacen())); // B59
        setCellValue(sheet, 59, 1, "FECHA:" + fechaEncargado); // B60

        // TRASLADA
        setCellValue(sheet, 58, 3, "NOMBRE/FIRMA:" + safe(dto.getTraslada())); // D59
        setCellValue(sheet, 59, 3, "FECHA:" + fechaTraslada); // D60

        // RECIBE
        setCellValue(sheet, 58, 5, "NOMBRE/FIRMA:" + safe(dto.getRecibe())); // F59
        setCellValue(sheet, 59, 5, "FECHA:" + fechaRecibe); // F60
    }

    private String formatearFecha(String fechaIso)
    {
        if (fechaIso == null || fechaIso.isBlank())
        {
            return "";
        }

        try
        {
            return java.time.LocalDate.parse(fechaIso)
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e)
        {
            return fechaIso;
        }
    }
}