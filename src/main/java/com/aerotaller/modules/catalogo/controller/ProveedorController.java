package com.aerotaller.modules.catalogo.controller;

import com.aerotaller.modelos.Proveedor;
import com.aerotaller.modules.catalogo.repository.ProveedorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodos() {
        return ResponseEntity.ok(proveedorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorRepository.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Integer id, @RequestBody Proveedor detalles) {
        return proveedorRepository.findById(id)
                .map(prov -> {
                    prov.setNombre(detalles.getNombre());
                    prov.setContacto(detalles.getContacto());
                    prov.setCorreo(detalles.getCorreo());
                    prov.setDireccion(detalles.getDireccion());
                    prov.setCodigoPostal(detalles.getCodigoPostal());
                    prov.setCiudad(detalles.getCiudad());
                    prov.setTelefono(detalles.getTelefono());
                    prov.setEstado(detalles.getEstado());
                    return ResponseEntity.ok(proveedorRepository.save(prov));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Integer id) {
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}