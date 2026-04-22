package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.Condicion;
import com.aerotaller.modelos.DetalleEntradaArticulo;
import com.aerotaller.modelos.EntradaArticulo;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloExportRequestDto;
import com.aerotaller.modules.entradaart.repository.EntradaArticuloRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaArticuloPdfServiceImpl implements EntradaArticuloPdfService
{
    private static final int TOTAL_FILAS = 40;
    private static final float PAGE_WIDTH = PDRectangle.A4.getWidth();
    private static final float PAGE_HEIGHT = PDRectangle.A4.getHeight();
    private static final float MARGIN = 20f;

    private final EntradaArticuloRepository entradaArticuloRepository;
    private final CondicionRepository condicionRepository;

    public EntradaArticuloPdfServiceImpl(
            EntradaArticuloRepository entradaArticuloRepository,
            CondicionRepository condicionRepository
    )
    {
        this.entradaArticuloRepository = entradaArticuloRepository;
        this.condicionRepository = condicionRepository;
    }

    @Override
    public byte[] generarPdfEntrada(Integer idEntrada, EntradaArticuloExportRequestDto exportDto)
    {
        EntradaArticulo entrada = entradaArticuloRepository.findById(idEntrada)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada."));

        try (PDDocument document = new PDDocument();
             ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            PDFont fontRegular = cargarFuente(document, "fonts/calibri.ttf",
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA));
            PDFont fontBold = cargarFuente(document, "fonts/calibrib.ttf",
                    new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD));

            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream cs = new PDPageContentStream(document, page))
            {
                dibujarFormato(document, cs, entrada, exportDto, fontRegular, fontBold);
            }

            document.save(baos);
            return baos.toByteArray();

        } catch (Exception e)
        {
            throw new RuntimeException("Error al generar el PDF de la entrada: " + e.getMessage(), e);
        }
    }

    private void dibujarFormato(
            PDDocument document,
            PDPageContentStream cs,
            EntradaArticulo entrada,
            EntradaArticuloExportRequestDto exportDto,
            PDFont fontRegular,
            PDFont fontBold
    ) throws IOException
    {
        Color azul = new Color(12, 132, 173);
        Color azulClaro = new Color(90, 183, 226);
        Color negro = Color.BLACK;

        float x = MARGIN;
        float yTop = PAGE_HEIGHT - MARGIN;
        float usableWidth = PAGE_WIDTH - (MARGIN * 2);

        // =========================
        // ENCABEZADO
        // =========================
        float h1 = 74f;
        float logoW = 108f;
        float tituloW = 235f;
        float empresaW = usableWidth - logoW - tituloW;

        rect(cs, x, yTop - h1, usableWidth, h1, null, negro, 0.8f);

        // Logo
        rect(cs, x, yTop - h1, logoW, h1, null, negro, 0.6f);
        dibujarLogo(document, cs, x + 8, yTop - h1 + 8, logoW - 16, h1 - 16);

        // Título azul
        rect(cs, x + logoW, yTop - h1, tituloW, h1, azul, negro, 0.6f);
        textoCentrado(cs, "ENTRADA DE ALMACÉN.", x + logoW, yTop - 42, tituloW, fontBold, 11, Color.WHITE);

        // Bloque derecho empresa (más chico)
        rect(cs, x + logoW + tituloW, yTop - h1, empresaW, h1, null, negro, 0.6f);
        textoCentrado(cs, "AG AVIATION SUPPLIERS S.A DE C.V.", x + logoW + tituloW, yTop - 28, empresaW, fontBold, 9.5f, negro);
        textoCentrado(cs, "TALLER AERONAUTICO AUTORIZADO", x + logoW + tituloW, yTop - 43, empresaW, fontBold, 9.5f, negro);
        textoCentrado(cs, "A.F.A.C NO. 505.", x + logoW + tituloW, yTop - 58, empresaW, fontBold, 9.5f, negro);

        // =========================
        // FECHA / PROVEEDOR / FOLIO
        // =========================
        float y2 = yTop - h1;
        float h2 = 24f;

        rect(cs, x, y2 - h2, usableWidth, h2, null, negro, 0.6f);
        rect(cs, x + usableWidth - 115, y2 - h2, 115, h2, azulClaro, negro, 0.6f);

        texto(cs, "Fecha: " + formatearFechaEntrada(entrada), x + 6, y2 - 16, fontBold, 8.5f, negro);
        texto(cs, "Proveedor: " + obtenerProveedor(entrada), x + 95, y2 - 16, fontBold, 8.5f, negro);
        texto(cs, "FOLIO: " + safe(entrada.getFolio()), x + usableWidth - 108, y2 - 16, fontBold, 8.5f, negro);

        // =========================
        // OBS / DEPARTAMENTO
        // =========================
        float y3 = y2 - h2;
        float h3 = 56f;
        rect(cs, x, y3 - h3, usableWidth, h3, null, negro, 0.6f);

        texto(cs, "Observaciones: " + safe(entrada.getObservaciones()), x + 6, y3 - 16, fontBold, 8.5f, negro);
        texto(cs, "Departamento: Almacén.", x + 355, y3 - 16, fontBold, 8.5f, negro);

        // =========================
        // TABLA
        // =========================
        float yTableTop = y3 - h3;
        float headerH = 18f;
        float rowH = 12f;

        float[] cols = {28f, 35f, 115f, 95f, 92f, 70f, 64f, 56f};
        String[] headers = {
                "ITEM", "QTY", "DESCRIPCIÓN", "NUMERO DE PARTE",
                "NUMERO DE SERIE", "CONDICIÓN", "PROVEEDOR", "OBSERVACIONES"
        };

        float currentX = x;
        for (int i = 0; i < cols.length; i++)
        {
            rect(cs, currentX, yTableTop - headerH, cols[i], headerH, azul, negro, 0.6f);
            float fontSize = (i == 7) ? 6.2f : 7.0f;
            textoCentrado(cs, headers[i], currentX, yTableTop - 11.5f, cols[i], fontBold, fontSize, Color.WHITE);
            currentX += cols[i];
        }

        List<DetalleEntradaArticulo> detalles = entrada.getDetalles();
        for (int i = 0; i < TOTAL_FILAS; i++)
        {
            float rowY = yTableTop - headerH - (i * rowH);
            currentX = x;

            String item = String.valueOf(i + 1);
            String qty = "";
            String descripcion = "";
            String noParte = "";
            String noSerie = "";
            String condicion = "";
            String proveedor = "";
            String observaciones = "";

            if (i < detalles.size())
            {
                DetalleEntradaArticulo detalle = detalles.get(i);
                Articulo articulo = detalle.getArticulo();

                qty = detalle.getCantidad() != null ? String.valueOf(detalle.getCantidad()) : "";
                descripcion = articulo != null ? safe(articulo.getDescripcion()) : "";
                noParte = articulo != null ? safe(articulo.getCodigo()) : "";
                noSerie = articulo != null ? safe(articulo.getNoSerie()) : "";
                condicion = obtenerNombreCondicion(articulo);
                proveedor = obtenerProveedor(entrada);
            }

            String[] values = {item, qty, descripcion, noParte, noSerie, condicion, proveedor, observaciones};

            for (int c = 0; c < cols.length; c++)
            {
                rect(cs, currentX, rowY - rowH, cols[c], rowH, null, negro, 0.5f);

                if (c == 0 || c == 1)
                {
                    textoDerecha(cs, values[c], currentX + cols[c] - 3, rowY - 9, fontRegular, 7, negro);
                }
                else
                    if (c == 5)
                    {
                        textoCentrado(cs, recortar(values[c], 15), currentX, rowY - 9, cols[c], fontRegular, 7, negro);
                    }
                    else
                    {
                        texto(cs, recortar(values[c], c == 2 ? 24 : 16), currentX + 2, rowY - 9, fontRegular, 7, negro);
                    }

                currentX += cols[c];
            }
        }

        // =========================
        // NOTA
        // =========================
        float yNota = yTableTop - headerH - (TOTAL_FILAS * rowH) - 34;
        rect(cs, x, yNota, usableWidth, 18f, azul, negro, 0.6f);
        texto(
                cs,
                "* NOTA: LA CONDICIÓN SE DEFINE POR: NUEVO DE FABRICA(NF), NEW SURPLUS (NS), OVERHAULED (OH), REMOVIDO (AR), SERVICIABLE(SV), REPARABLE (RP), CALIBRADO (CA).",
                x + 4,
                yNota + 5,
                fontBold,
                6.3f,
                Color.WHITE
        );

        // =========================
        // FIRMAS - CORREGIDAS
        // =========================
        float firmasTituloY = yNota - 44;
        float firmasDataY = firmasTituloY - 48;
        float blockWidth = 150f;
        float gap = 22f;

        float b1x = x + 10;
        float b2x = b1x + blockWidth + gap;
        float b3x = b2x + blockWidth + gap;

        dibujarBloqueFirma(
                cs, "ENCARGADO DE ALMACEN:",
                safe(exportDto.getEncargadoAlmacen()),
                formatearFecha(exportDto.getFechaEncargado()),
                b1x, firmasTituloY, blockWidth, fontRegular, fontBold
        );

        dibujarBloqueFirma(
                cs, "TRASLADA:",
                safe(exportDto.getTraslada()),
                formatearFecha(exportDto.getFechaTraslada()),
                b2x, firmasTituloY, blockWidth, fontRegular, fontBold
        );

        dibujarBloqueFirma(
                cs, "RECIBE:",
                safe(exportDto.getRecibe()),
                formatearFecha(exportDto.getFechaRecibe()),
                b3x, firmasTituloY, blockWidth, fontRegular, fontBold
        );
    }

    private void dibujarBloqueFirma(
            PDPageContentStream cs,
            String titulo,
            String nombre,
            String fecha,
            float x,
            float yTitulo,
            float blockWidth,
            PDFont fontRegular,
            PDFont fontBold
    ) throws IOException
    {
        Color negro = Color.BLACK;
        Color azulLinea = new Color(60, 110, 220);

        textoCentrado(cs, titulo, x, yTitulo, blockWidth, fontBold, 9f, negro);

        float yData = yTitulo - 45;

        // NOMBRE/FIRMA
        texto(cs, "NOMBRE/FIRMA:", x, yData, fontBold, 7.5f, negro);

        float nameX = x + 78;
        float maxNameWidth = blockWidth - 78;
        String nombreAjustado = ajustarTextoPorAncho(nombre, fontBold, 7.5f, maxNameWidth);
        texto(cs, nombreAjustado, nameX, yData, fontBold, 7.5f, negro);
        linea(cs, nameX, yData - 2, x + blockWidth, yData - 2, azulLinea, 1f);

        // FECHA
        texto(cs, "FECHA:", x, yData - 18, fontBold, 7.5f, negro);

        float fechaX = x + 40;
        float maxFechaWidth = blockWidth - 40;
        String fechaAjustada = ajustarTextoPorAncho(fecha, fontBold, 7.5f, maxFechaWidth);
        texto(cs, fechaAjustada, fechaX, yData - 18, fontBold, 7.5f, negro);
        linea(cs, fechaX, yData - 20, x + 88, yData - 20, azulLinea, 1f);
    }

    private void dibujarLogo(
            PDDocument document,
            PDPageContentStream cs,
            float x,
            float y,
            float maxWidth,
            float maxHeight
    ) throws IOException
    {
        try (InputStream is = new ClassPathResource("reports/assets/logo_ag.png").getInputStream())
        {
            byte[] bytes = is.readAllBytes();
            PDImageXObject image = PDImageXObject.createFromByteArray(document, bytes, "logo_ag");

            float imgW = image.getWidth();
            float imgH = image.getHeight();

            float scale = Math.min(maxWidth / imgW, maxHeight / imgH);
            float drawW = imgW * scale;
            float drawH = imgH * scale;

            float drawX = x + (maxWidth - drawW) / 2f;
            float drawY = y + (maxHeight - drawH) / 2f;

            cs.drawImage(image, drawX, drawY, drawW, drawH);
        } catch (Exception e)
        {
            // fallback por si no encuentra el logo
        }
    }

    private PDFont cargarFuente(PDDocument document, String resourcePath, PDFont fallback)
    {
        try (InputStream is = new ClassPathResource(resourcePath).getInputStream())
        {
            return PDType0Font.load(document, is);
        } catch (Exception e)
        {
            return fallback;
        }
    }

    private String ajustarTextoPorAncho(String texto, PDFont font, float fontSize, float maxWidth) throws IOException
    {
        if (texto == null) return "";

        String value = texto;
        while (!value.isEmpty() && (font.getStringWidth(value) / 1000 * fontSize) > maxWidth)
        {
            value = value.substring(0, value.length() - 1);
        }

        if (!value.equals(texto) && value.length() > 3)
        {
            value = value.substring(0, value.length() - 3) + "...";
        }

        return value;
    }

    private String obtenerProveedor(EntradaArticulo entrada)
    {
        return entrada.getProveedor() != null ? safe(entrada.getProveedor().getNombre()) : "";
    }

    private String obtenerNombreCondicion(Articulo articulo)
    {
        if (articulo == null || articulo.getCondicion() == null) return "";
        Optional<Condicion> condicion = condicionRepository.findById(articulo.getCondicion());
        return condicion.map(Condicion::getNombre).orElse("");
    }

    private String formatearFechaEntrada(EntradaArticulo entrada)
    {
        if (entrada.getFechaEntrada() == null) return "";
        return entrada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private String formatearFecha(String fechaIso)
    {
        if (fechaIso == null || fechaIso.isBlank()) return "";
        try
        {
            return LocalDate.parse(fechaIso).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e)
        {
            return fechaIso;
        }
    }

    private String recortar(String valor, int max)
    {
        if (valor == null) return "";
        return valor.length() > max ? valor.substring(0, max) : valor;
    }

    private String safe(String value)
    {
        return value != null ? value : "";
    }

    private void rect(PDPageContentStream cs, float x, float y, float w, float h, Color fill, Color stroke, float lineWidth) throws IOException
    {
        if (fill != null)
        {
            cs.setNonStrokingColor(fill);
            cs.addRect(x, y, w, h);
            cs.fill();
        }

        cs.setStrokingColor(stroke);
        cs.setLineWidth(lineWidth);
        cs.addRect(x, y, w, h);
        cs.stroke();
    }

    private void linea(PDPageContentStream cs, float x1, float y1, float x2, float y2, Color color, float width) throws IOException
    {
        cs.setStrokingColor(color);
        cs.setLineWidth(width);
        cs.moveTo(x1, y1);
        cs.lineTo(x2, y2);
        cs.stroke();
    }

    private void texto(PDPageContentStream cs, String text, float x, float y, PDFont font, float size, Color color) throws IOException
    {
        cs.beginText();
        cs.setFont(font, size);
        cs.setNonStrokingColor(color);
        cs.newLineAtOffset(x, y);
        cs.showText(text != null ? text : "");
        cs.endText();
    }

    private void textoCentrado(PDPageContentStream cs, String text, float x, float y, float width, PDFont font, float size, Color color) throws IOException
    {
        String value = text != null ? text : "";
        float textWidth = font.getStringWidth(value) / 1000 * size;
        float textX = x + (width - textWidth) / 2f;
        texto(cs, value, textX, y, font, size, color);
    }

    private void textoDerecha(PDPageContentStream cs, String text, float rightX, float y, PDFont font, float size, Color color) throws IOException
    {
        String value = text != null ? text : "";
        float textWidth = font.getStringWidth(value) / 1000 * size;
        texto(cs, value, rightX - textWidth, y, font, size, color);
    }
}