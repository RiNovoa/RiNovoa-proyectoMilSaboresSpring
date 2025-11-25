package com.pasteleria.service;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.repository.UsuarioRepository;
import java.util.List;
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
    
    //CRUD:
    public Usuario saveUsuario(Usuario u){
        return repository.save(u);
    }
    
    public List<Usuario> saveUsuarios(List<Usuario> usuarios){
        return repository.saveAll(usuarios);
    }
    
    public List<Usuario> getUsuarios(){
        return repository.findAll();
    }
    
    public Usuario getUsuarioById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public Usuario getUsuarioByEmail(String email){
        return repository.findByEmail(email);
    }
    
    public String deleteUsuario(int id){
        repository.deleteById(id);
        return "Usuario eliminado!! " + id;
    }
    
    public Usuario updateUsuario(Usuario u){
        Usuario existingUsuario = repository.findById(u.getId()).orElse(null);
        existingUsuario.setNombre( u.getNombre() );
        existingUsuario.setApellido( u.getApellido() );
        existingUsuario.setEmail( u.getEmail() );
        existingUsuario.setPassword( u.getPassword() );
        existingUsuario.setTelefono( u.getTelefono() );
        existingUsuario.setDireccion( u.getDireccion() );
        return repository.save(existingUsuario);
    }
    
}
