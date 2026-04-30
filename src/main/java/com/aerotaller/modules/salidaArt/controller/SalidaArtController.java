package com.aerotaller.modules.salidaArt.controller;

import com.aerotaller.modules.salidaArt.dto.CreateSalidaArtRequest;
import com.aerotaller.modules.salidaArt.dto.SalidaArtDetalleResponse;
import com.aerotaller.modules.salidaArt.dto.SalidaArtExportRequestDto;
import com.aerotaller.modules.salidaArt.dto.SalidaArtResponse;
import com.aerotaller.modules.salidaArt.service.SalidaArtService;
import com.aerotaller.modules.salidaArt.service.SalidaArtExcelService; // Importar
import com.aerotaller.modules.salidaArt.service.SalidaArtPdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
public class SalidaArtController {
    private final SalidaArtService salidaService;
    private final SalidaArtExcelService excelService;
    private final SalidaArtPdfService pdfService;

    public SalidaArtController(
            SalidaArtService salidaService,
            SalidaArtExcelService excelService,
            SalidaArtPdfService pdfService
    ) {
        this.salidaService = salidaService;
        this.excelService = excelService;
        this.pdfService = pdfService;
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
    public ResponseEntity<SalidaArtDetalleResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(salidaService.obtenerDetalle(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        salidaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/exportar-excel")
    public ResponseEntity<byte[]> exportarExcel(@PathVariable Integer id, @RequestBody SalidaArtExportRequestDto dto) {
        byte[] data = excelService.generarExcelSalida(id, dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=salida.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }

    @PostMapping("/{id}/exportar-pdf")
    public ResponseEntity<byte[]> exportarPdf(@PathVariable Integer id, @RequestBody SalidaArtExportRequestDto dto) {
        byte[] data = pdfService.generarPdfSalida(id, dto);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=salida.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);

    }
}