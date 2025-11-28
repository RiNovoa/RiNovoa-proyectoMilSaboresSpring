package com.pasteleria.Controller;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.security.ApiKeyService;
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

    @Autowired
    private ApiKeyService apiKeyService;
    
    // ================== RUTAS PÚBLICAS (si quieres que el cliente se registre solo) ==================
    // Si prefieres que SOLO admin cree usuarios, también puedes proteger esta con API KEY.

    // Crear un usuario (por ahora la dejo pública; si quieres protegerla, le agregamos header)
    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario u) {
        return service.saveUsuario(u);
    }
    
    // ================== RUTAS SOLO ADMIN (con API KEY) ==================

    // Crear varios usuarios (solo admin)
    @PostMapping("/batch")
    public List<Usuario> addUsuarios(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody List<Usuario> usuarios) {

        apiKeyService.validate(apiKey);
        return service.saveUsuarios(usuarios);
    }
    
    // Listar todos (puede ser público o solo admin, tú decides; aquí lo dejo público)
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
    
    // Eliminar (SOLO ADMIN)
    @DeleteMapping("/{id}")
    public String deleteUsuario(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id) {

        apiKeyService.validate(apiKey);
        return service.deleteUsuario(id);
    }
    
    // Actualizar (SOLO ADMIN)
    @PutMapping("/{id}")
    public Usuario updateUsuario(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id,
            @RequestBody Usuario u) {

        apiKeyService.validate(apiKey);
        return service.updateUsuario(id, u);
    }
}
