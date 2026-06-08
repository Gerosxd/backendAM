package com.aerotaller.modules.tareaProgramada.controller;

import com.aerotaller.modelos.ReporteProgramado;
import com.aerotaller.modules.tareaProgramada.dto.TareaProgramadaDTO;
import com.aerotaller.modules.tareaProgramada.service.TareaProgramadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programadas")
@CrossOrigin(origins = "*")
public class TareaProgramadaController {

    private final TareaProgramadaService service;

    public TareaProgramadaController(TareaProgramadaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReporteProgramado>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @PostMapping
    public ResponseEntity<ReporteProgramado> registrar(@RequestBody TareaProgramadaDTO dto) {
        ReporteProgramado resultado = service.guardar(dto);
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(resultado);
    }
}