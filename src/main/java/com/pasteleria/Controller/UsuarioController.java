package com.pasteleria.Controller;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.dto.LoginRequest;
import com.pasteleria.security.ApiKeyService;
import com.pasteleria.service.UsuarioService;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Cristóbal Pérez
 */

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)
@Tag(name = "Usuarios", description = "Operaciones CRUD y login de usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private ApiKeyService apiKeyService;

    // ========================= LOGIN (PÚBLICO) =========================

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest request) {

        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }

        Usuario usuario = service.login(request.getEmail(), request.getPassword());

        if (usuario == null) {
            return ResponseEntity.status(401).build(); // credenciales inválidas
        }

        return ResponseEntity.ok(usuario);
    }

    // ========================= CRUD (SOLO ADMIN CON API KEY) =========================

    // Crear usuario
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody Usuario u) {

        apiKeyService.validate(apiKey);
        Usuario creado = service.saveUsuario(u);
        return ResponseEntity.ok(creado);
    }

    // Crear varios usuarios
    @PostMapping("/batch")
    public ResponseEntity<List<Usuario>> addUsuarios(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody List<Usuario> usuarios) {

        apiKeyService.validate(apiKey);
        List<Usuario> creados = service.saveUsuarios(usuarios);
        return ResponseEntity.ok(creados);
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey) {

        apiKeyService.validate(apiKey);
        return ResponseEntity.ok(service.getUsuarios());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findUsuarioById(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id) {

        apiKeyService.validate(apiKey);
        Usuario u = service.getUsuarioById(id);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    // Buscar por RUN
    @GetMapping("/run/{run}")
    public ResponseEntity<Usuario> findUsuarioByRun(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable String run) {

        apiKeyService.validate(apiKey);
        Usuario u = service.getUsuarioByRun(run);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    // Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> findUsuarioByEmail(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable String email) {

        apiKeyService.validate(apiKey);
        Usuario u = service.getUsuarioByEmail(email);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id) {

        apiKeyService.validate(apiKey);
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id,
            @RequestBody Usuario u) {

        apiKeyService.validate(apiKey);
        Usuario updated = service.updateUsuario(id, u);
        return ResponseEntity.ok(updated);
    }
}
