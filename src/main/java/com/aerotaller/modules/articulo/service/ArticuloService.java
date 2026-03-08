package com.aerotaller.modules.articulo.service;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modules.articulo.dto.CreateArticuloRequest;
import com.aerotaller.modules.articulo.repository.ArticuloRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloService {

    private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public List<Articulo> guardarVarios(List<CreateArticuloRequest> requestList) {
        List<Articulo> articulos = new ArrayList<>();

        for (CreateArticuloRequest req : requestList) {
            if (articuloRepository.existsByCodigo(req.getCodigo())) {
                throw new RuntimeException("Ya existe un artículo con código: " + req.getCodigo());
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

        return articuloRepository.saveAll(articulos);
    }

    public List<Articulo> listarTodos() {
        return articuloRepository.findAll();
    }
}