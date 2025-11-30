package com.pasteleria.service;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.Entity.RolUsuario;
import com.pasteleria.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    // ================== CRUD ==================

    // Crear un usuario (ADMIN, VENDEDOR o CLIENTE)
    public Usuario saveUsuario(Usuario u) {

        // Forzamos que sea un INSERT
        u.setId(null);

        // Normalizar email
        if (u.getEmail() != null) {
            u.setEmail(u.getEmail().trim().toLowerCase());
        }

        // Si no viene rol → por defecto CLIENTE
        if (u.getRol() == null) {
            u.setRol(RolUsuario.CLIENTE);
        }

        return repository.save(u);
    }

    // Crear varios usuarios
    public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
        usuarios.forEach(u -> {
            u.setId(null);

            if (u.getEmail() != null) {
                u.setEmail(u.getEmail().trim().toLowerCase());
            }

            if (u.getRol() == null) {
                u.setRol(RolUsuario.CLIENTE);
            }
        });
        return repository.saveAll(usuarios);
    }

    // Listar todos
    public List<Usuario> getUsuarios() {
        return repository.findAll();
    }

    // Buscar por ID
    public Usuario getUsuarioById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Buscar por RUN
    public Usuario getUsuarioByRun(String run) {
        return repository.findByRun(run);
    }

    // Buscar por email
    public Usuario getUsuarioByEmail(String email) {
        if (email == null) return null;
        return repository.findByEmail(email.trim().toLowerCase());
    }

    // Eliminar usuario
    public String deleteUsuario(Integer id) {
        repository.deleteById(id);
        return "Usuario eliminado: " + id;
    }

    // Actualizar usuario
    public Usuario updateUsuario(Integer id, Usuario u) {
        Optional<Usuario> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }

        Usuario existingUsuario = opt.get();

        existingUsuario.setRun(u.getRun());
        existingUsuario.setNombre(u.getNombre());
        existingUsuario.setApellido(u.getApellido());

        if (u.getEmail() != null) {
            existingUsuario.setEmail(u.getEmail().trim().toLowerCase());
        }

        existingUsuario.setPassword(u.getPassword());
        existingUsuario.setTelefono(u.getTelefono());
        existingUsuario.setDireccion(u.getDireccion());

        // Solo cambiamos el rol si viene uno explícito
        if (u.getRol() != null) {
            existingUsuario.setRol(u.getRol());
        }

        return repository.save(existingUsuario);
    }

    // ================== LOGIN ==================

    public Usuario login(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        String emailNorm = email.trim().toLowerCase();
        Usuario u = repository.findByEmail(emailNorm);
        if (u == null) {
            return null;
        }

        // Por ahora comparación directa (password en texto plano)
        if (!password.equals(u.getPassword())) {
            return null;
        }

        return u;
    }
}
