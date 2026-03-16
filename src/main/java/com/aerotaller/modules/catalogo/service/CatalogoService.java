package com.aerotaller.modules.catalogo.service;

import com.aerotaller.modules.catalogo.dto.CatalogoOptionDto;

import java.util.List;

public interface CatalogoService
{
    List<CatalogoOptionDto> obtenerCategorias();

    List<CatalogoOptionDto> obtenerUnidades();

    List<CatalogoOptionDto> obtenerAlmacenes();

    List<CatalogoOptionDto> obtenerProveedores();

    List<CatalogoOptionDto> obtenerCondiciones();

    List<CatalogoOptionDto> obtenerEstadosEntrada();
}