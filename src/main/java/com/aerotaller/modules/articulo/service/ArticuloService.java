package com.aerotaller.modules.articulo.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modules.articulo.dto.ArticuloResponse;
import com.aerotaller.modules.articulo.dto.CreateArticuloRequest;
import com.aerotaller.modules.articulo.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<ArticuloResponse> guardarVarios(List<CreateArticuloRequest> requestList) {
        List<Articulo> articulos = new ArrayList<>();

        for (CreateArticuloRequest req : requestList) {
            if (articuloRepository.existsByCodigo(req.getCodigo())) {
                throw new RuntimeException("Ya existe un artÃ­culo con cÃ³digo: " + req.getCodigo());
            }

            Articulo articulo = new Articulo();
            articulo.setCodigo(req.getCodigo());
            articulo.setNoSerie(req.getNoSerie());
            articulo.setDescripcion(req.getDescripcion());
            articulo.setCategoria(req.getCategoria());
            articulo.setUnidadMedida(req.getUnidadMedida());
            articulo.setAlmacen(req.getAlmacen());
            articulo.setUbicacion(req.getUbicacion());
            articulo.setProveedor(req.getProveedor());
            articulo.setPrecioCompra(req.getPrecioCompra());
            articulo.setStock(req.getStock());
            articulo.setCondicion(req.getCondicion());

            articulos.add(articulo);
        }

        return articuloRepository.saveAll(articulos)
                .stream()
                .map(ArticuloResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ArticuloResponse> listarTodos() {
        return articuloRepository.findAll()
                .stream()
                .map(ArticuloResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public ArticuloResponse buscarPorCodigo(String codigo) {
        Articulo articulo = articuloRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("ArtÃ­culo no encontrado con cÃ³digo: " + codigo));
        return ArticuloResponse.fromEntity(articulo);
    }

    public ArticuloResponse buscarPorNoSerie(String noSerie) {
        Articulo articulo = articuloRepository.findByNoSerie(noSerie)
                .orElseThrow(() -> new RuntimeException("ArtÃ­culo no encontrado con nÃºmero de serie: " + noSerie));
        return ArticuloResponse.fromEntity(articulo);
    }

    public void descontarStock(Integer idArticulo, Integer cantidad) {

        Articulo articulo = articuloRepository.findById(idArticulo)
                .orElseThrow(() -> new RuntimeException("ArtÃ­culo no encontrado"));

        if (articulo.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        articulo.setStock(articulo.getStock() - cantidad);

        articuloRepository.save(articulo);
    }

    public List<ArticuloResponse> buscar(String termino) {

        List<Articulo> porCodigo = articuloRepository.findByCodigoContainingIgnoreCase(termino);
        List<Articulo> porSerie = articuloRepository.findByNoSerieContainingIgnoreCase(termino);

        porSerie.forEach(art -> {
            if (!porCodigo.contains(art)) {
                porCodigo.add(art);
            }
        });

        return porCodigo.stream()
                .map(ArticuloResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

