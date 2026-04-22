package com.aerotaller.modules.entradaart.controller;

import com.aerotaller.modules.entradaart.dto.EntradaArticuloListadoResponseDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroRequestDto;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloRegistroResponseDto;
import com.aerotaller.modules.entradaart.service.EntradaArticuloExcelService;
import com.aerotaller.modules.entradaart.service.EntradaArticuloPdfService;
import com.aerotaller.modules.entradaart.service.EntradaArticuloReportService;
import com.aerotaller.modules.entradaart.service.EntradaArticuloService;
import com.aerotaller.modules.entradaart.dto.EntradaArticuloExportRequestDto;
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
    private final EntradaArticuloExcelService entradaArticuloExcelService;
    private final EntradaArticuloPdfService entradaArticuloPdfService;


    public EntradaArticuloController(
            EntradaArticuloService entradaArticuloService,
            EntradaArticuloReportService entradaArticuloReportService,
            EntradaArticuloExcelService entradaArticuloExcelService,
            EntradaArticuloPdfService entradaArticuloPdfService
    )
    {
        this.entradaArticuloService = entradaArticuloService;
        this.entradaArticuloReportService = entradaArticuloReportService;
        this.entradaArticuloExcelService = entradaArticuloExcelService;
        this.entradaArticuloPdfService = entradaArticuloPdfService;
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

    @PostMapping("/{idEntrada}/exportar-excel")
    public ResponseEntity<byte[]> exportarExcelEntrada(
            @PathVariable Integer idEntrada,
            @RequestBody EntradaArticuloExportRequestDto exportDto
    )
    {
        byte[] excel = entradaArticuloExcelService.generarExcelEntrada(idEntrada, exportDto);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=entrada_" + idEntrada + ".xlsx"
                )
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                ))
                .body(excel);
    }

    @PostMapping("/{idEntrada}/exportar-pdf")
    public ResponseEntity<byte[]> exportarPdfEntrada(
            @PathVariable Integer idEntrada,
            @RequestBody EntradaArticuloExportRequestDto exportDto
    )
    {
        byte[] pdf = entradaArticuloPdfService.generarPdfEntrada(idEntrada, exportDto);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=entrada_" + idEntrada + ".pdf"
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}