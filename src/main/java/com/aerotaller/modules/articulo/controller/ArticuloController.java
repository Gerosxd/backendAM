package com.aerotaller.modules.articulo.controller;

import com.aerotaller.modules.articulo.dto.ArticuloResponse;
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
    public ResponseEntity<List<ArticuloResponse>> listar()
    {
        return ResponseEntity.ok(articuloService.listarTodos());
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ArticuloResponse>> guardarVarios(
            @RequestBody List<CreateArticuloRequest> requestList)
    {
        System.out.println("ENTRO AL CONTROLLER");
        return ResponseEntity.ok(articuloService.guardarVarios(requestList));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ArticuloResponse> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(articuloService.buscarPorCodigo(codigo));
    }

    @GetMapping("/serie/{noSerie}")
    public ResponseEntity<ArticuloResponse> buscarPorSerie(@PathVariable String noSerie) {
        return ResponseEntity.ok(articuloService.buscarPorNoSerie(noSerie));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ArticuloResponse>> buscar(@RequestParam String termino)
    {
        return ResponseEntity.ok(articuloService.buscar(termino));
    }
}
