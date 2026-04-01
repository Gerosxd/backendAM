package com.aerotaller.modules.catalogo.controller;

import com.aerotaller.modelos.Cliente;
import com.aerotaller.modules.catalogo.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodos() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") Integer id, @RequestBody Cliente detallesCliente) {

        // Buscar si el cliente existe en la base de datos
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();

            // 2. Actualizamos los datos
            cliente.setCompania(detallesCliente.getCompania());
            cliente.setContacto(detallesCliente.getContacto());
            cliente.setTelefono(detallesCliente.getTelefono());
            cliente.setCorreo(detallesCliente.getCorreo());
            cliente.setDireccion(detallesCliente.getDireccion());
            cliente.setRfc(detallesCliente.getRfc());
            cliente.setEstado(detallesCliente.getEstado());

            // 3. Guardamos los cambios
            Cliente clienteGuardado = clienteRepository.save(cliente);

            // 4. Retornamos el cliente actualizado con un status 200 OK
            return ResponseEntity.ok(clienteGuardado);
        } else {
            // Si el ID no existe, retornamos un error 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // ==========================================
    // Eliminar cliente
    // ==========================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Integer id) {

        // 1. Verificamos si existe
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            // 2. Si existe, lo borramos
            clienteRepository.deleteById(id);
            // Retornamos un status 204 No Content
            return ResponseEntity.noContent().build();
        } else {
            // Si no existe, retornamos 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}