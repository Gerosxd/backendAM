package com.aerotaller.modules.catalogo.repository;

import com.aerotaller.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
{
}