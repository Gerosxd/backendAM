package com.aerotaller.modules.catalogo.controller;

import com.aerotaller.modelos.ModeloAeronave;
import com.aerotaller.modules.catalogo.repository.ModeloAeronaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modelos")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ModeloAeronaveController {

    @Autowired
    private ModeloAeronaveRepository modeloRepository;

    @GetMapping
    public List<ModeloAeronave> listarModelos() {
        return modeloRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> guardarModelo(@RequestBody ModeloAeronave modeloAeronave) {
        if (modeloAeronave.getModelo() == null || modeloAeronave.getModelo().trim().isEmpty() ||
                modeloAeronave.getMarca() == null || modeloAeronave.getMarca().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Error: El modelo y la marca son obligatorios.");
        }

        if (modeloRepository.existsByModeloIgnoreCase(modeloAeronave.getModelo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Error: El modelo '" + modeloAeronave.getModelo() + "' ya está registrado.");
        }

        ModeloAeronave nuevoModelo = modeloRepository.save(modeloAeronave);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoModelo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarModelo(@PathVariable("id") Integer id, @RequestBody ModeloAeronave detalles) {
        Optional<ModeloAeronave> modeloExistente = modeloRepository.findById(id);

        if (modeloExistente.isPresent()) {
            ModeloAeronave m = modeloExistente.get();

            if (!m.getModelo().equalsIgnoreCase(detalles.getModelo()) &&
                    modeloRepository.existsByModeloIgnoreCase(detalles.getModelo())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: El modelo '" + detalles.getModelo() + "' ya existe.");
            }

            m.setModelo(detalles.getModelo());
            m.setMarca(detalles.getMarca());
            m.setTipoAeronave(detalles.getTipoAeronave());

            return ResponseEntity.ok(modeloRepository.save(m));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarModelo(@PathVariable("id") Integer id) {
        if (modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}