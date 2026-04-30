package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.Condicion;
import com.aerotaller.modelos.DetalleSalidaArt;
import com.aerotaller.modelos.SalidaArt;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.salidaArt.dto.SalidaArtExportRequestDto;
import com.aerotaller.modules.salidaArt.repository.SalidaArtRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalidaArtPdfServiceImpl implements SalidaArtPdfService {

    private final SalidaArtRepository repository;
    private final float MARGIN = 30f;
    private final CondicionRepository condicionRepository;

    public SalidaArtPdfServiceImpl(
            SalidaArtRepository repository,
            CondicionRepository condicionRepository // Inyectar aquí
    ) {
        this.repository = repository;
        this.condicionRepository = condicionRepository;
    }
    @Override
    public byte[] generarPdfSalida(Integer idSalida, SalidaArtExportRequestDto exportDto) {
        SalidaArt salida = repository.findById(idSalida)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada."));

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDType1Font fontRegular = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
            PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            try (PDPageContentStream cs = new PDPageContentStream(document, page)) {

                dibujarEncabezado(document, cs, salida, fontBold, fontRegular);

                float yDespuesDeTabla = dibujarTablaArticulos(cs, salida.getDetalles(), fontBold, fontRegular);

                dibujarSeccionInspeccion(cs, yDespuesDeTabla - 20, fontBold, fontRegular);

                dibujarFirmas(cs, 100, exportDto, fontBold, fontRegular);
            }

            document.save(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF de Salida: " + e.getMessage(), e);
        }
    }

    private void dibujarEncabezado(PDDocument doc, PDPageContentStream cs, SalidaArt salida, PDType1Font bold, PDType1Font reg) throws IOException {
        float upperY = 800; // Punto de inicio superior
        Color azulInstitucional = new Color(12, 132, 173);

        // 1. LOGO (Izquierda)
        try (InputStream is = new ClassPathResource("reports/assets/logo_ag.png").getInputStream()) {
            PDImageXObject logo = PDImageXObject.createFromByteArray(doc, is.readAllBytes(), "logo");
            cs.drawImage(logo, 30, upperY - 60, 150, 70); // Ajustado al tamaño de la imagen
        } catch (Exception e) {
            // Log error o fallback
        }

        // 2. TEXTO CENTRAL (Título y Datos del Taller)
        cs.setNonStrokingColor(Color.BLACK);
        // Título Principal Subrayado
        dibujarTextoCentrado(cs, "SALIDA DE ALMACÉN", 200, upperY - 20, 250, bold, 18, Color.BLACK);
        // Dibujar línea de subrayado manualmente
        cs.setLineWidth(1.5f);
        cs.moveTo(215, upperY - 23);
        cs.lineTo(435, upperY - 23);
        cs.stroke();

        dibujarTextoCentrado(cs, "AG AVIATION SUPPLIERS S.A DE C.V.", 200, upperY - 40, 250, bold, 10, Color.BLACK);
        dibujarTextoCentrado(cs, "TALLER AERONAUTICO AUTORIZADO", 200, upperY - 55, 250, bold, 10, Color.BLACK);
        dibujarTextoCentrado(cs, "A.F.A.C NO. 505.", 200, upperY - 70, 250, bold, 10, Color.BLACK);

        // 3. CUADRO "NO. DE SALIDA" (Derecha)
        float xNoSalida = 450;
        cs.setNonStrokingColor(azulInstitucional);
        cs.addRect(xNoSalida, upperY - 40, 115, 25);
        cs.fill();
        dibujarTextoCentrado(cs, "NO. DE SÁLIDA:", xNoSalida, upperY - 33, 115, bold, 9, Color.WHITE);

        // Borde y contenido del Folio
        cs.setStrokingColor(Color.BLACK);
        cs.setLineWidth(1f);
        cs.addRect(xNoSalida, upperY - 75, 115, 35);
        cs.stroke();
        dibujarTextoCentrado(cs, salida.getNoSalida(), xNoSalida, upperY - 63, 115, bold, 11, Color.BLACK);

        // 4. DATOS DEL DESTINATARIO
        float yDest = upperY - 110;
        cs.setNonStrokingColor(Color.BLACK);
        dibujarTexto(cs, 30, yDest, "DESTINATARIO:", bold, 8);
        dibujarTexto(cs, 110, yDest, (salida.getDestinatario() != null ? salida.getDestinatario() : ""), reg, 8);

        dibujarTexto(cs, 30, yDest - 12, "DIRECCIÓN DEL DESTINATARIO:", bold, 8);
        // Asumiendo que existe el campo direccion en tu modelo, si no, se deja espacio
        // dibujarTexto(cs, 180, yDest - 15, (salida.getDireccion() != null ? salida.getDireccion() : ""), reg, 8);

        // 5. BLOQUE FECHA Y REFERENCIA
        float yFecha = yDest - 45;
        cs.setNonStrokingColor(azulInstitucional);
        cs.addRect(30, yFecha, 100, 20); // Caja azul Fecha
        cs.fill();
        dibujarTextoCentrado(cs, "FECHA:", 30, yFecha + 6, 100, bold, 9, Color.WHITE);

        cs.setStrokingColor(Color.BLACK);
        cs.addRect(130, yFecha, 150, 20); // Caja blanca Fecha
        cs.stroke();
        String fechaStr = salida.getFecha() != null ? salida.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "00/00/2026";
        dibujarTextoCentrado(cs, fechaStr, 130, yFecha + 6, 150, reg, 9, Color.BLACK);

        // 6. BLOQUE REFERENCIA (Espaciado de la Fecha)
        float yRef = yFecha - 35; // Bajamos la referencia para que no esté pegada
        cs.setNonStrokingColor(azulInstitucional);
        cs.addRect(30, yRef, 100, 35); // Caja azul Referencia
        cs.fill();
        dibujarTextoCentrado(cs, "REFERENCIA:", 30, yRef + 14, 100, bold, 9, Color.WHITE);

        cs.setStrokingColor(Color.BLACK);
        cs.addRect(130, yRef, 435, 35); // Caja blanca Referencia
        cs.stroke();

        // Etiqueta FECHA
        cs.setNonStrokingColor(azulInstitucional);
        cs.addRect(30, yFecha, 100, 20);
        cs.fill();
        dibujarTextoCentrado(cs, "FECHA:", 30, yFecha + 6, 100, bold, 9, Color.WHITE);



        // Etiqueta REFERENCIA (Cuadro Grande)
        cs.setNonStrokingColor(azulInstitucional);
        cs.addRect(30, yFecha - 50, 100, 50);
        cs.fill();
        dibujarTextoCentrado(cs, "REFERENCIA:", 30, yFecha - 28, 100, bold, 9, Color.WHITE);

    }

    private float dibujarTablaArticulos(PDPageContentStream cs, List<DetalleSalidaArt> detalles, PDType1Font bold, PDType1Font reg) throws IOException {
        float yTable = 510; // Bajamos el inicio de la tabla para dar aire a la referencia
        float rowHeight = 20f;
        float currentX = 30f;
        Color azulInstitucional = new Color(12, 132, 173);

        // Anchos de columna calculados para que sumen 535 puntos aprox.
        // NO(25), QTY(35), DESC(160), N.PARTE(80), N.SERIE(80), COND(65), OBS(90)
        float[] colWidths = {25, 35, 160, 80, 80, 65, 90};
        String[] headers = {"NO", "QTY", "DESCRIPCIÓN", "NÚMERO DE PARTE", "NÚMERO DE SERIE", "CONDICIÓN", "OBSERVACIÓN"};

        // 1. ENCABEZADO DE LA TABLA
        cs.setNonStrokingColor(azulInstitucional);
        for (int i = 0; i < headers.length; i++) {
            cs.addRect(currentX, yTable, colWidths[i], rowHeight);
            cs.fill();
            // Usamos una fuente más pequeña (7) para que quepan los títulos largos
            dibujarTextoCentrado(cs, headers[i], currentX, yTable + 6, colWidths[i], bold, 6.5f, Color.WHITE);
            currentX += colWidths[i];
        }

        // 2. CUERPO DE LA TABLA (10 FILAS)
        cs.setStrokingColor(Color.BLACK);
        float yRow = yTable - rowHeight;

        for (int i = 0; i < 10; i++) {
            currentX = 30f;
            for (float colWidth : colWidths) {
                cs.addRect(currentX, yRow, colWidth, rowHeight);
                cs.stroke();
                currentX += colWidth;
            }

            if (i < detalles.size()) {
                DetalleSalidaArt d = detalles.get(i);
                Articulo a = d.getArticulo();

                float x = 30f;
                dibujarTextoCentrado(cs, String.valueOf(i + 1), x, yRow + 6, colWidths[0], reg, 8, Color.BLACK); x += colWidths[0];
                dibujarTextoCentrado(cs, String.valueOf(d.getCantidad()), x, yRow + 6, colWidths[1], reg, 8, Color.BLACK); x += colWidths[1];

                // Descripción (limitada para que no invada otras celdas)
                String desc = (a != null && a.getDescripcion() != null) ? a.getDescripcion() : "";
                dibujarTexto(cs, x + 3, yRow + 6, desc.length() > 35 ? desc.substring(0, 35) : desc, reg, 7); x += colWidths[2];

                // No. Parte
                dibujarTextoCentrado(cs, (a != null ? a.getCodigo() : ""), x, yRow + 6, colWidths[3], reg, 7, Color.BLACK); x += colWidths[3];

                // No. Serie
                dibujarTextoCentrado(cs, (a != null && a.getNoSerie() != null ? a.getNoSerie() : ""), x, yRow + 6, colWidths[4], reg, 7, Color.BLACK); x += colWidths[4];

                // Condición (Ahora dibuja el nombre real)
                String nombreCondicion = obtenerNombreCondicion(a);
                dibujarTextoCentrado(cs, nombreCondicion, x, yRow + 6, colWidths[5], reg, 7, Color.BLACK);
                x += colWidths[5];

                // Observación
                dibujarTexto(cs, x + 3, yRow + 6, "", reg, 7);
            }
            yRow -= rowHeight;
        }

        return yRow;
    }

    private void dibujarSeccionInspeccion(PDPageContentStream cs, float yStart, PDType1Font bold, PDType1Font reg) throws IOException {
        // 1. NOTA DE CONDICIÓN (Con ajuste de línea)
        float yNota = yStart;
        cs.setStrokingColor(Color.BLACK);
        cs.addRect(MARGIN, yNota - 15, 545, 30); // Rectángulo un poco más alto
        cs.stroke();

        String textoNota = "* NOTA: LA CONDICIÓN SE DEFINE POR: NUEVO DE FABRICA(NF), NEW SURPLUS (NS), OVERHAULED (OH), REMOVIDO (AR), SERVICIABLE(SV), REPARABLE (RP), CALIBRADO (CA)";
        dibujarTextoConAjuste(cs, textoNota, MARGIN + 5, yNota + 7, 535, bold, 6.5f);

// 2. CHECKLIST SI/NO
        float yCheck = yNota - 50;
        dibujarTexto(cs, 495, yCheck + 15, "SI", bold, 9);
        dibujarTexto(cs, 535, yCheck + 15, "NO", bold, 9);

        String[] preguntas = {
                "¿EL COMPONENTE(S)/EQUIPO(S)/MATERIAL(ES) PRESENTA DAÑOS FÍSICOS?",
                "¿EL NÚMERO DE PARTE Y EL NÚMERO DE SERIE DEL COMPONENTE(S)/EQUIPO(S)/MATERIAL(ES) COINCIDEN CON LO REQUERIDO?",
                "¿LA DOCUMENTACIÓN ANEXA DEL COMPONENTE(S)/EQUIPO(S)/MATERIAL(ES) (CERTIFICACIÓN DEL MATERIAL, CERTIFICADO DE CONFORMIDAD, CERTIFICADO DE CALIBRACIÓN, SERVICIO DE MANTENIMIENTO REALIZADO, ETC) ES CORRECTA Y SE ENCUENTRA FIRMADA?"
        };

        float yFila = yCheck;
        for (int i = 0; i < preguntas.length; i++) {
            // Usamos dibujarTextoConAjuste solo para la última pregunta o si es muy larga
            if (i == 2) {
                dibujarTextoConAjuste(cs, preguntas[i], MARGIN, yFila, 480, reg, 7);
            } else {
                dibujarTexto(cs, MARGIN, yFila, preguntas[i], reg, 7);
            }

            // Dibujar cuadritos SI/NO (alineados con la primera línea de la pregunta)
            cs.addRect(490, yFila - 3, 15, 12);
            cs.addRect(530, yFila - 3, 15, 12);
            cs.stroke();

            yFila -= (i == 2) ? 30 : 20; // Más espacio para la pregunta multilínea
        }
    }

    private void dibujarFirmas(PDPageContentStream cs, float yPos, SalidaArtExportRequestDto dto, PDType1Font bold, PDType1Font reg) throws IOException {
        float col = 180f;
        dibujarBloqueFirma(cs, MARGIN, yPos, "ENCARGADO ALMACÉN", dto.getEncargadoAlmacen(), dto.getFechaEncargado(), bold, reg);
        dibujarBloqueFirma(cs, MARGIN + col, yPos, "TRASLADA", dto.getTraslada(), dto.getFechaTraslada(), bold, reg);
        dibujarBloqueFirma(cs, MARGIN + (col * 2), yPos, "RECIBE", dto.getRecibe(), dto.getFechaRecibe(), bold, reg);
    }

    private void dibujarBloqueFirma(PDPageContentStream cs, float x, float y, String titulo, String nombre, String fecha, PDType1Font bold, PDType1Font reg) throws IOException {
        dibujarTextoCentrado(cs, titulo, x, y + 40, 160, bold, 8, Color.BLACK);
        cs.moveTo(x + 10, y + 20);
        cs.lineTo(x + 150, y + 20);
        cs.stroke();
        dibujarTextoCentrado(cs, nombre != null ? nombre : "", x, y + 25, 160, reg, 7, Color.BLACK);
        dibujarTextoCentrado(cs, "FECHA: " + (fecha != null ? fecha : ""), x, y + 10, 160, reg, 6, Color.BLACK);
    }

    // --- MÉTODOS DE UTILIDAD ---
    private void dibujarTexto(PDPageContentStream cs, float x, float y, String text, PDType1Font font, float size) throws IOException {
        cs.beginText();
        cs.setFont(font, size);
        cs.newLineAtOffset(x, y);
        cs.showText(text != null ? text : "");
        cs.endText();
    }

    private void dibujarTextoCentrado(PDPageContentStream cs, String text, float x, float y, float width, PDType1Font font, float size, Color color) throws IOException {
        cs.setNonStrokingColor(color);
        float titleWidth = font.getStringWidth(text != null ? text : "") / 1000 * size;
        float startX = x + (width - titleWidth) / 2;
        cs.beginText();
        cs.setFont(font, size);
        cs.newLineAtOffset(startX, y);
        cs.showText(text != null ? text : "");
        cs.endText();
        cs.setNonStrokingColor(Color.BLACK);
    }

    private void dibujarTextoConAjuste(PDPageContentStream cs, String texto, float x, float y, float maxWidth, PDType1Font font, float fontSize) throws IOException {
        List<String> lineas = new ArrayList<>();
        String[] palabras = texto.split(" ");
        StringBuilder lineaActual = new StringBuilder();

        for (String palabra : palabras) {
            String prueba = lineaActual.length() == 0 ? palabra : lineaActual + " " + palabra;
            float anchoPrueba = font.getStringWidth(prueba) / 1000 * fontSize;
            if (anchoPrueba > maxWidth) {
                lineas.add(lineaActual.toString());
                lineaActual = new StringBuilder(palabra);
            } else {
                lineaActual.append(lineaActual.length() == 0 ? "" : " ").append(palabra);
            }
        }
        lineas.add(lineaActual.toString());

        float tempY = y;
        for (String linea : lineas) {
            cs.beginText();
            cs.setFont(font, fontSize);
            cs.newLineAtOffset(x, tempY);
            cs.showText(linea);
            cs.endText();
            tempY -= fontSize + 2; // Espaciado entre líneas
        }
    }

    private String obtenerNombreCondicion(Articulo articulo) {
        if (articulo == null || articulo.getCondicion() == null) {
            return "";
        }

        String nombreCompleto = condicionRepository.findById(articulo.getCondicion())
                .map(c -> c.getNombre())
                .orElse("");

        // Lógica para extraer solo la abreviación (lo que está entre paréntesis)
        if (nombreCompleto.contains("(") && nombreCompleto.contains(")")) {
            return nombreCompleto.substring(nombreCompleto.indexOf("(") + 1, nombreCompleto.indexOf(")"));
        }

        // Si no tiene paréntesis, devolvemos los primeros 2 o 3 caracteres para que no rompa la tabla
        return nombreCompleto.length() > 3 ? nombreCompleto.substring(0, 2) : nombreCompleto;
    }
}