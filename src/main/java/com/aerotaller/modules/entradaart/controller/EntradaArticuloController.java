package com.aerotaller.modules.entradaart.controller;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloListadoResponseDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroResponseDto;
import com.aerotaller.modules.entradaart.service.EntradaArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas-articulos")
public class EntradaArticuloController {

    private final EntradaArticuloService entradaArticuloService;

    public EntradaArticuloController(EntradaArticuloService entradaArticuloService) {
        this.entradaArticuloService = entradaArticuloService;
    }

    @PostMapping("/registro-completo")
    public ResponseEntity<EntradaArticuloRegistroResponseDto> registrarEntradaCompleta(
            @RequestBody EntradaArticuloRegistroRequestDto dto
    ) {
        return ResponseEntity.ok(entradaArticuloService.registrarEntradaCompleta(dto));
    }

    @GetMapping
    public ResponseEntity<List<EntradaArticuloListadoResponseDto>> listarEntradas() {
        return ResponseEntity.ok(entradaArticuloService.listarEntradas());
    }

    @GetMapping("/{idEntrada}")
    public ResponseEntity<EntradaArticuloRegistroResponseDto> obtenerEntradaPorId(
            @PathVariable Integer idEntrada
    ) {
        return ResponseEntity.ok(entradaArticuloService.obtenerEntradaPorId(idEntrada));
    }
}