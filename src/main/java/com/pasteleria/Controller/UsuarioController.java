package com.pasteleria.Controller;

import com.pasteleria.Entity.Usuario;
import com.pasteleria.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristóbal Pérez
 */
@RestController
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;
    
    @PostMapping("/addUsuario")
    public Usuario addUsuario(@RequestBody Usuario u){
        return service.saveUsuario(u);
    }
    
    @PostMapping("/addUsuarios")
    public List<Usuario> addUsuarios(@RequestBody List<Usuario> usuarios){
        return service.saveUsuarios(usuarios);
    }
    
    @GetMapping("/usuarios")
    public List<Usuario> findAllUsuarios(){
        return service.getUsuarios();
    }
    
    @GetMapping("/usuarioById/{id}")
    public Usuario findUsuarioById(@PathVariable int id){
        return service.getUsuarioById(id);
    }
    
    @GetMapping("/usuarioByEmail/{email}")
    public Usuario findUsuarioByEmail(@PathVariable String email){
        return service.getUsuarioByEmail(email);
    }
    
    @DeleteMapping("/deleteUsuario/{id}")
    public String deleteUsuario(@PathVariable int id){
        return service.deleteUsuario(id);
    }
    
    @PutMapping("/updateUsuario")
    public Usuario updateUsuario(@RequestBody Usuario u){
        return service.updateUsuario(u);
    }
    
}
