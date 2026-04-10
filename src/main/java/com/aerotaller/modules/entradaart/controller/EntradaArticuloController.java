package com.aerotaller.modules.entradaart.controller;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloListadoResponseDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroResponseDto;
import com.aerotaller.modules.entradaart.service.EntradaArticuloReportService;
import com.aerotaller.modules.entradaart.service.EntradaArticuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@RestController
@RequestMapping("/api/entradas-articulos")
public class EntradaArticuloController
{

    private final EntradaArticuloService entradaArticuloService;
    private final EntradaArticuloReportService entradaArticuloReportService;


    public EntradaArticuloController(
            EntradaArticuloService entradaArticuloService,
            EntradaArticuloReportService entradaArticuloReportService
    )
    {
        this.entradaArticuloService = entradaArticuloService;
        this.entradaArticuloReportService = entradaArticuloReportService;
    }

    @PostMapping("/registro-completo")
    public ResponseEntity<EntradaArticuloRegistroResponseDto> registrarEntradaCompleta(
            @RequestBody EntradaArticuloRegistroRequestDto dto
    )
    {
        return ResponseEntity.ok(entradaArticuloService.registrarEntradaCompleta(dto));
    }

    @GetMapping
    public ResponseEntity<List<EntradaArticuloListadoResponseDto>> listarEntradas()
    {
        return ResponseEntity.ok(entradaArticuloService.listarEntradas());
    }

    @GetMapping("/{idEntrada}")
    public ResponseEntity<EntradaArticuloRegistroResponseDto> obtenerEntradaPorId(
            @PathVariable Integer idEntrada
    )
    {
        return ResponseEntity.ok(entradaArticuloService.obtenerEntradaPorId(idEntrada));
    }

    @GetMapping("/{idEntrada}/pdf")
    public ResponseEntity<byte[]> descargarPdfEntrada(@PathVariable Integer idEntrada)
    {
        byte[] pdf = entradaArticuloReportService.generarPdfEntrada(idEntrada);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=entrada_" + idEntrada + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}