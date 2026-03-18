package com.aerotaller.modules.catalogo.controller;

import com.aerotaller.modelos.Almacen;
import com.aerotaller.modules.catalogo.repository.AlmacenRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
public class AlmacenController {

    private final AlmacenRepository almacenRepository;

    public AlmacenController(AlmacenRepository almacenRepository) {
        this.almacenRepository = almacenRepository;
    }

    @GetMapping
    public ResponseEntity<List<Almacen>> obtenerTodos() {
        return ResponseEntity.ok(almacenRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Almacen> crearAlmacen(@RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenRepository.save(almacen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizarAlmacen(@PathVariable Integer id, @RequestBody Almacen detalles) {
        return almacenRepository.findById(id)
                .map(almacen -> {
                    almacen.setNombre(detalles.getNombre());
                    almacen.setDireccion(detalles.getDireccion());
                    almacen.setCiudad(detalles.getCiudad());
                    almacen.setEstado(detalles.getEstado());
                    return ResponseEntity.ok(almacenRepository.save(almacen));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}