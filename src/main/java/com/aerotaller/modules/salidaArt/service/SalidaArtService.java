package com.aerotaller.modules.salidaArt.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modelos.DetalleSalidaArt;
import com.aerotaller.modelos.SalidaArt;
import com.aerotaller.modules.articulo.repository.ArticuloRepository;
import com.aerotaller.modules.detalleSalidaArt.dto.CreateDetalleSalidaArtRequest;
import com.aerotaller.modules.detalleSalidaArt.repository.DetalleSalidaArtRepository;
import com.aerotaller.modules.salidaArt.dto.ArticuloSalidaResponse;
import com.aerotaller.modules.salidaArt.dto.CreateSalidaArtRequest;
import com.aerotaller.modules.salidaArt.dto.SalidaArtDetalleResponse;
import com.aerotaller.modules.salidaArt.dto.SalidaArtResponse;
import com.aerotaller.modules.salidaArt.repository.SalidaArtRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalidaArtService {

    private final SalidaArtRepository salidaRepository;
    private final DetalleSalidaArtRepository detalleSalidaArtRepository;
    private final ArticuloRepository articuloRepository;

    public SalidaArtService(SalidaArtRepository salidaRepository, DetalleSalidaArtRepository detalleSalidaArtRepository, ArticuloRepository articuloRepository) {
        this.salidaRepository = salidaRepository;
        this.detalleSalidaArtRepository = detalleSalidaArtRepository;
        this.articuloRepository = articuloRepository;
    }

    @Transactional
    public SalidaArtDetalleResponse crearSalida(CreateSalidaArtRequest request) {

        SalidaArt salida = new SalidaArt();
        salida.setNoSalida(request.getNoSalida());
        salida.setFecha(LocalDate.now());
        salida.setDestinatario(request.getDestinatario());
        salida.setDireccionDestinatario(request.getDireccionDestinatario());
        salida.setEncargadoAlmacen(request.getEncargadoAlmacen());
        salida.setTraslada(request.getTraslada());
        salida.setRecibe(request.getRecibe());

        SalidaArt salidaGuardada = salidaRepository.save(salida);

        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new RuntimeException("La salida debe incluir al menos un articulo.");
        }

        for (CreateDetalleSalidaArtRequest detalleReq : request.getDetalles()) {

            Articulo articulo = resolverArticulo(detalleReq);

            if (articulo.getStock() == 0) {
                throw new RuntimeException("El artículo " + articulo.getCodigo() + " no tiene stock disponible.");
            }

            if (articulo.getStock() < detalleReq.getCantidad()) {
                throw new RuntimeException(
                        "Stock insuficiente para el artículo: " + articulo.getCodigo() +
                                ". Stock disponible: " + articulo.getStock()
                );
            }

            articulo.setStock(articulo.getStock() - detalleReq.getCantidad());
            articuloRepository.save(articulo);

            DetalleSalidaArt detalle = new DetalleSalidaArt();
            detalle.setSalidaArt(salidaGuardada);
            detalle.setArticulo(articulo);
            detalle.setCantidad(detalleReq.getCantidad());
            detalle.setObservaciones(detalleReq.getObservaciones());

            detalleSalidaArtRepository.save(detalle);
        }

        return obtenerDetalle(salidaGuardada.getIdSalida());
    }

    private Articulo resolverArticulo(CreateDetalleSalidaArtRequest detalleReq) {
        if (detalleReq == null) {
            throw new RuntimeException("Detalle de articulo invalido.");
        }

        Articulo articulo;
        if (detalleReq.getIdArticulo() != null) {
            articulo = articuloRepository.findById(detalleReq.getIdArticulo())
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
        } else if (detalleReq.getNoSerie() != null && !detalleReq.getNoSerie().isBlank()) {
            String noSerie = detalleReq.getNoSerie().trim();
            articulo = articuloRepository.findByNoSerie(noSerie)
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado con número de serie: " + noSerie));
        } else if (detalleReq.getCodigo() != null && !detalleReq.getCodigo().isBlank()) {
            String codigo = detalleReq.getCodigo().trim();
            articulo = articuloRepository.findByCodigo(codigo)
                    .orElseThrow(() -> new RuntimeException("Artículo no encontrado con número de parte: " + codigo));
        } else {
            throw new RuntimeException("El detalle debe incluir idArticulo, noSerie o codigo.");
        }

        if (detalleReq.getNoSerie() != null && !detalleReq.getNoSerie().isBlank()) {
            String noSerie = detalleReq.getNoSerie().trim();
            if (articulo.getNoSerie() == null || !articulo.getNoSerie().equalsIgnoreCase(noSerie)) {
                throw new RuntimeException("El número de serie no corresponde al artículo seleccionado.");
            }
        }
        if (detalleReq.getCodigo() != null && !detalleReq.getCodigo().isBlank()) {
            String codigo = detalleReq.getCodigo().trim();
            if (articulo.getCodigo() == null || !articulo.getCodigo().equalsIgnoreCase(codigo)) {
                throw new RuntimeException("El número de parte no corresponde al artículo seleccionado.");
            }
        }

        return articulo;
    }

    public List<SalidaArtResponse> listarTodas() {
        return salidaRepository.findAll()
                .stream()
                .map(SalidaArtResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public SalidaArtResponse obtenerPorId(Integer id) {
        SalidaArt salida = salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada"));
        return SalidaArtResponse.fromEntity(salida);
    }

    @Transactional
    public void eliminar(Integer id) {
        SalidaArt salida = salidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada"));

        List<DetalleSalidaArt> detalles =
                detalleSalidaArtRepository.findBySalidaArt_IdSalidaArt(id);
        for (DetalleSalidaArt detalle : detalles) {

            Articulo articulo = detalle.getArticulo();

            articulo.setStock(articulo.getStock() + detalle.getCantidad());

            articuloRepository.save(articulo);
        }
        salidaRepository.delete(salida);
    }

    public SalidaArtDetalleResponse obtenerDetalle(Integer idSalida) {

        SalidaArt salida = salidaRepository.findById(idSalida)
                .orElseThrow(() -> new RuntimeException("Salida no encontrada"));

        var detalles = detalleSalidaArtRepository.findBySalidaArt_IdSalidaArt(idSalida);

        SalidaArtDetalleResponse response = new SalidaArtDetalleResponse();

        response.setIdSalida(salida.getIdSalida());
        response.setNoSalida(salida.getNoSalida());
        response.setFecha(salida.getFecha());
        response.setDestinatario(salida.getDestinatario());
        response.setDireccionDestinatario(salida.getDireccionDestinatario());
        response.setEncargadoAlmacen(salida.getEncargadoAlmacen());
        response.setTraslada(salida.getTraslada());
        response.setRecibe(salida.getRecibe());

        response.setArticulos(
                detalles.stream().map(detalle -> {

                    ArticuloSalidaResponse art = new ArticuloSalidaResponse();

                    art.setCodigo(detalle.getArticulo().getCodigo());
                    art.setNoSerie(detalle.getArticulo().getNoSerie());
                    art.setDescripcion(detalle.getArticulo().getDescripcion());
                    art.setCondicion(detalle.getArticulo().getCondicion());
                    art.setCantidad(detalle.getCantidad());
                    art.setObservaciones(detalle.getObservaciones());

                    return art;

                }).collect(Collectors.toList())
        );

        return response;
    }
}
