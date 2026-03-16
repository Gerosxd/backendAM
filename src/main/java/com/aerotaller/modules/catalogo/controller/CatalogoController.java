package com.aerotaller.modules.catalogo.controller;

import com.aerotaller.modules.catalogo.dto.CatalogoOptionDto;
import com.aerotaller.modules.catalogo.service.CatalogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController
{

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService)
    {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerCategorias()
    {
        return ResponseEntity.ok(catalogoService.obtenerCategorias());
    }

    @GetMapping("/unidades")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerUnidades()
    {
        return ResponseEntity.ok(catalogoService.obtenerUnidades());
    }

    @GetMapping("/almacenes")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerAlmacenes()
    {
        return ResponseEntity.ok(catalogoService.obtenerAlmacenes());
    }

    @GetMapping("/proveedores")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerProveedores()
    {
        return ResponseEntity.ok(catalogoService.obtenerProveedores());
    }

    @GetMapping("/condiciones")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerCondiciones()
    {
        return ResponseEntity.ok(catalogoService.obtenerCondiciones());
    }

    @GetMapping("/estados-entrada")
    public ResponseEntity<List<CatalogoOptionDto>> obtenerEstadosEntrada() {
        return ResponseEntity.ok(catalogoService.obtenerEstadosEntrada());
    }
}