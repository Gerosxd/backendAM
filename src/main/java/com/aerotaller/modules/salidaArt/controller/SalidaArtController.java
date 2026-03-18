package com.aerotaller.modules.salidaArt.controller;

import com.aerotaller.modules.salidaArt.dto.CreateSalidaArtRequest;
import com.aerotaller.modules.salidaArt.dto.SalidaArtDetalleResponse;
import com.aerotaller.modules.salidaArt.dto.SalidaArtResponse;
import com.aerotaller.modules.salidaArt.service.SalidaArtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
public class SalidaArtController {
    private final SalidaArtService salidaService;

    public SalidaArtController(SalidaArtService salidaService) {
        this.salidaService = salidaService;
    }

    @PostMapping
    public ResponseEntity<SalidaArtDetalleResponse> crearSalida(@RequestBody CreateSalidaArtRequest request) {
        return ResponseEntity.ok(salidaService.crearSalida(request));
    }

    @GetMapping
    public ResponseEntity<List<SalidaArtResponse>> listar() {
        return ResponseEntity.ok(salidaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaArtResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(salidaService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        salidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<SalidaArtDetalleResponse> obtenerDetalle(@PathVariable Integer id) {
        return ResponseEntity.ok(salidaService.obtenerDetalle(id));
    }
}
