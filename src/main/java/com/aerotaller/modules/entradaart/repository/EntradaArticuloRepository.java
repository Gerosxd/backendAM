package com.aerotaller.modules.entradaart.repository;

import com.aerotaller.modelos.EntradaArticulo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntradaArticuloRepository extends JpaRepository<EntradaArticulo, Integer> {

    @EntityGraph(attributePaths = {
            "usuario",
            "proveedor",
            "almacenDestino",
            "estadoEntrada",
            "detalles",
            "detalles.articulo"
    })
    List<EntradaArticulo> findAll();

    @EntityGraph(attributePaths = {
            "usuario",
            "proveedor",
            "almacenDestino",
            "estadoEntrada",
            "detalles",
            "detalles.articulo"
    })
    Optional<EntradaArticulo> findById(Integer id);
}