package com.aerotaller.modules.aeronave.controller;

import com.aerotaller.modules.aeronave.dto.AeronaveResponse;
import com.aerotaller.modules.aeronave.dto.CreateAeronaveRequest;
import com.aerotaller.modules.aeronave.service.AeronaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aeronaves")
@CrossOrigin
public class AeronaveController {

    private final AeronaveService aeronaveService;

    public AeronaveController(AeronaveService aeronaveService) {
        this.aeronaveService = aeronaveService;
    }

    @PostMapping
    public AeronaveResponse guardarAeronave(@RequestBody CreateAeronaveRequest dto) {
        return aeronaveService.guardarAeronave(dto);
    }
    @GetMapping
    public List<AeronaveResponse> listarAeronaves() {
        return aeronaveService.listarAeronaves();
    }
}
