package com.aerotaller.modules.articulo.repository;

import com.aerotaller.modelos.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer>
{
    Optional<Articulo> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);
}