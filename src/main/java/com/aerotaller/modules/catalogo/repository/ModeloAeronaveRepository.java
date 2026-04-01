package com.aerotaller.modules.catalogo.repository;

import com.aerotaller.modelos.ModeloAeronave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloAeronaveRepository extends JpaRepository<ModeloAeronave, Integer> {
    // Validación automática para saber si el modelo ya existe
    boolean existsByModeloIgnoreCase(String modelo);
}