package com.aerotaller.modules.ot.controller;

import com.aerotaller.modules.ot.dto.AeronaveComboResponse;
import com.aerotaller.modules.ot.dto.CrearOTRequest;
import com.aerotaller.modules.ot.dto.CrearOTResponse;
import com.aerotaller.modules.ot.dto.SiguienteNoOTResponse;
import com.aerotaller.modules.ot.service.OTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.aerotaller.modules.ot.dto.OTListadoResponse;

import java.util.List;

@RestController
@RequestMapping("/api/ots")
public class OTController
{

    private final OTService otService;

    public OTController(OTService otService)
    {
        this.otService = otService;
    }

    @GetMapping("/matriculas")
    public ResponseEntity<List<AeronaveComboResponse>> obtenerMatriculas()
    {
        return ResponseEntity.ok(otService.obtenerMatriculas());
    }

    @GetMapping("/siguiente-noot")
    public ResponseEntity<SiguienteNoOTResponse> obtenerSiguienteNoOT()
    {
        return ResponseEntity.ok(otService.obtenerSiguienteNoOT());
    }

    @PostMapping
    public ResponseEntity<?> crearOT(@RequestBody CrearOTRequest request)
    {
        try
        {
            CrearOTResponse response = otService.crearOT(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al crear la OT.");
        }
    }

    @GetMapping
    public ResponseEntity<List<OTListadoResponse>> listarOTs()
    {
        return ResponseEntity.ok(otService.listarOTs());
    }
}