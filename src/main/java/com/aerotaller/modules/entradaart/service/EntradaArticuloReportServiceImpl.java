package com.aerotaller.modules.entradaart.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.Condicion;
import com.aerotaller.modelos.DetalleEntradaArticulo;
import com.aerotaller.modelos.EntradaArticulo;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloPdfDetalleDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloPdfResponseDto;
import com.aerotaller.modules.entradaart.repository.EntradaArticuloRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class EntradaArticuloReportServiceImpl implements EntradaArticuloReportService
{

    private static final int TOTAL_FILAS_FORMATO = 40;

    private final EntradaArticuloRepository entradaArticuloRepository;
    private final CondicionRepository condicionRepository;

    public EntradaArticuloReportServiceImpl(
            EntradaArticuloRepository entradaArticuloRepository,
            CondicionRepository condicionRepository
    )
    {
        this.entradaArticuloRepository = entradaArticuloRepository;
        this.condicionRepository = condicionRepository;
    }

    @Override
    public byte[] generarPdfEntrada(Integer idEntrada)
    {
        try
        {
            EntradaArticulo entrada = entradaArticuloRepository.findById(idEntrada)
                    .orElseThrow(() -> new RuntimeException("Entrada no encontrada."));

            EntradaArticuloPdfResponseDto data = mapToPdfDto(entrada);

            InputStream reporteStream = new ClassPathResource("reports/entrada_articulo.jrxml").getInputStream();
            JasperReport jasperReport = JasperCompileManager.compileReport(reporteStream);

            Map<String, Object> params = new HashMap<>();
            params.put("P_FOLIO", safe(data.getFolio()));
            params.put("P_FECHA", safe(data.getFechaEntrada()));
            params.put("P_PROVEEDOR", safe(data.getProveedor()));
            params.put("P_OBSERVACIONES", safe(data.getObservaciones()));
            params.put("P_DEPARTAMENTO", safe(data.getDepartamento()));
            params.put(
                    "P_LOGO_PATH",
                    new ClassPathResource("reports/assets/logo_ag.png").getFile().getAbsolutePath()
            );

            JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(data.getDetalles());

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ds);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e)
        {
            throw new RuntimeException("Error al generar el PDF de la entrada: " + e.getMessage(), e);
        }
    }

    private EntradaArticuloPdfResponseDto mapToPdfDto(EntradaArticulo entrada)
    {
        EntradaArticuloPdfResponseDto dto = new EntradaArticuloPdfResponseDto();

        dto.setIdEntrada(entrada.getIdEntrada());
        dto.setFolio(entrada.getFolio());
        dto.setFechaEntrada(
                entrada.getFechaEntrada() != null
                        ? entrada.getFechaEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        : ""
        );
        dto.setProveedor(entrada.getProveedor() != null ? entrada.getProveedor().getNombre() : "");
        dto.setObservaciones(entrada.getObservaciones() != null ? entrada.getObservaciones() : "");
        dto.setDepartamento(
                entrada.getAlmacenDestino() != null
                        ? entrada.getAlmacenDestino().getNombre()
                        : ""
        );

        List<EntradaArticuloPdfDetalleDto> detallesPdf = new ArrayList<>();

        int item = 1;
        for (DetalleEntradaArticulo detalle : entrada.getDetalles())
        {
            Articulo articulo = detalle.getArticulo();

            EntradaArticuloPdfDetalleDto det = new EntradaArticuloPdfDetalleDto();
            det.setItem(item++);
            det.setCantidad(detalle.getCantidad());
            det.setDescripcion(articulo != null ? safe(articulo.getDescripcion()) : "");
            det.setNumeroParte(articulo != null ? safe(articulo.getCodigo()) : "");
            det.setNumeroSerie(articulo != null ? safe(articulo.getNoSerie()) : "");
            det.setCondicion(obtenerNombreCondicion(articulo));
            det.setProveedor(entrada.getProveedor() != null ? safe(entrada.getProveedor().getNombre()) : "");
            det.setObservaciones("");

            detallesPdf.add(det);
        }

        while (detallesPdf.size() < TOTAL_FILAS_FORMATO)
        {
            EntradaArticuloPdfDetalleDto vacio = new EntradaArticuloPdfDetalleDto();
            vacio.setItem(detallesPdf.size() + 1);
            vacio.setCantidad(null);
            vacio.setDescripcion("");
            vacio.setNumeroParte("");
            vacio.setNumeroSerie("");
            vacio.setCondicion("");
            vacio.setProveedor("");
            vacio.setObservaciones("");
            detallesPdf.add(vacio);
        }

        dto.setDetalles(detallesPdf);
        return dto;
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

    private String safe(String value)
    {
        return value != null ? value : "";
    }
}