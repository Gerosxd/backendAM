package com.aerotaller.modules.articulo.controller;

import com.aerotaller.modelos.Articulo;
import com.aerotaller.modules.articulo.dto.CreateArticuloRequest;
import com.aerotaller.modules.articulo.service.ArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloController
{

    private final ArticuloService articuloService;

    public ArticuloController(ArticuloService articuloService)
    {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<List<Articulo>> listar()
    {
        return ResponseEntity.ok(articuloService.listarTodos());
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Articulo>> guardarVarios(@RequestBody List<CreateArticuloRequest> requestList)
    {
        return ResponseEntity.ok(articuloService.guardarVarios(requestList));
    }
}