package com.aerotaller.modules.catalogo.repository;

import com.aerotaller.modelos.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, Integer>
{
}