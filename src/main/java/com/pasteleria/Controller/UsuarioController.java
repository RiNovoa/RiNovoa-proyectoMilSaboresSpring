package com.pasteleria.Controller;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Cristóbal Pérez
 */

@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Usuarios", description = "Operaciones CRUD de usuarios")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    // Crear un usuario (cliente o creado por admin)
    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario u) {
        return service.saveUsuario(u);
    }
    
    // Crear varios usuarios
    @PostMapping("/batch")
    public List<Usuario> addUsuarios(@RequestBody List<Usuario> usuarios) {
        return service.saveUsuarios(usuarios);
    }
    
    // Listar todos
    @GetMapping
    public List<Usuario> findAllUsuarios() {
        return service.getUsuarios();
    }
    
    // Buscar por ID
    @GetMapping("/{id}")
    public Usuario findUsuarioById(@PathVariable Integer id) {
        return service.getUsuarioById(id);
    }

    // Buscar por RUN
    @GetMapping("/run/{run}")
    public Usuario findUsuarioByRun(@PathVariable String run) {
        return service.getUsuarioByRun(run);
    }
    
    // Buscar por email
    @GetMapping("/email/{email}")
    public Usuario findUsuarioByEmail(@PathVariable String email) {
        return service.getUsuarioByEmail(email);
    }
    
    // Eliminar
    @DeleteMapping("/{id}")
    public String deleteUsuario(@PathVariable Integer id) {
        return service.deleteUsuario(id);
    }
    
    // Actualizar
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
        return service.updateUsuario(id, u);
    }
}
