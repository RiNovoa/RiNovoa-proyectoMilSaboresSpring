package com.pasteleria.repository;

import com.pasteleria.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    public Usuario findByEmail(String email);
    
}
