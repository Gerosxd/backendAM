package com.aerotaller.modules.salidaArt.repository;

import com.aerotaller.modelos.SalidaArt;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SalidaArtRepository extends JpaRepository<SalidaArt, Integer> {
    @EntityGraph(attributePaths = {"detalles", "detalles.articulo"})
    Optional<SalidaArt> findById(Integer id);
}
