package com.pasteleria.service;

import com.pasteleria.Entity.Usuario;
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
    
    // Crear un usuario (ADMIN, VENDEDOR o CLIENTE)
    public Usuario saveUsuario(Usuario u) {
        // Forzamos que sea un INSERT
        u.setId(null);
        return repository.save(u);
    }
    
    // Crear varios usuarios
    public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
        usuarios.forEach(u -> u.setId(null));
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
        return repository.findByEmail(email);
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
        existingUsuario.setEmail(u.getEmail());
        existingUsuario.setPassword(u.getPassword());
        existingUsuario.setTelefono(u.getTelefono());
        existingUsuario.setDireccion(u.getDireccion());
        existingUsuario.setRol(u.getRol());  

        return repository.save(existingUsuario);
    }
}
