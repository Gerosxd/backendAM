package com.aerotaller.modules.catalogo.service;

import com.aerotaller.modules.catalogo.dto.CatalogoOptionDto;
import com.aerotaller.modules.catalogo.repository.AlmacenRepository;
import com.aerotaller.modules.catalogo.repository.CategoriaRepository;
import com.aerotaller.modules.catalogo.repository.CondicionRepository;
import com.aerotaller.modules.catalogo.repository.ProveedorRepository;
import com.aerotaller.modules.catalogo.repository.UnidadMedidaRepository;
import org.springframework.stereotype.Service;
import com.aerotaller.modules.entradaart.repository.EstadoEntradaRepository;

import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService
{

    private final CategoriaRepository categoriaRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final AlmacenRepository almacenRepository;
    private final ProveedorRepository proveedorRepository;
    private final CondicionRepository condicionRepository;
    private final EstadoEntradaRepository estadoEntradaRepository;

    public CatalogoServiceImpl(
            CategoriaRepository categoriaRepository,
            UnidadMedidaRepository unidadMedidaRepository,
            AlmacenRepository almacenRepository,
            ProveedorRepository proveedorRepository,
            CondicionRepository condicionRepository,
            EstadoEntradaRepository estadoEntradaRepository
    )
    {
        this.categoriaRepository = categoriaRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.almacenRepository = almacenRepository;
        this.proveedorRepository = proveedorRepository;
        this.condicionRepository = condicionRepository;
        this.estadoEntradaRepository = estadoEntradaRepository;
    }

    @Override
    public List<CatalogoOptionDto> obtenerCategorias()
    {
        return categoriaRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdCategoria(), item.getNombre()))
                .toList();
    }

    @Override
    public List<CatalogoOptionDto> obtenerUnidades()
    {
        return unidadMedidaRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdUnidad(), item.getNombre()))
                .toList();
    }

    @Override
    public List<CatalogoOptionDto> obtenerAlmacenes()
    {
        return almacenRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdAlmacen(), item.getNombre()))
                .toList();
    }

    @Override
    public List<CatalogoOptionDto> obtenerProveedores()
    {
        return proveedorRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdProveedor(), item.getNombre()))
                .toList();
    }

    @Override
    public List<CatalogoOptionDto> obtenerCondiciones()
    {
        return condicionRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdCondicion(), item.getNombre()))
                .toList();
    }

    @Override
    public List<CatalogoOptionDto> obtenerEstadosEntrada()
    {
        return estadoEntradaRepository.findAll()
                .stream()
                .map(item -> new CatalogoOptionDto(item.getIdEstadoEntrada(), item.getNombre()))
                .toList();
    }
}