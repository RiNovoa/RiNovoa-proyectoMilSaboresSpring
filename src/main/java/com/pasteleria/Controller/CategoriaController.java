package com.pasteleria.Controller;

import com.pasteleria.Entity.Categoria;
import com.pasteleria.service.CategoriaService;
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
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;
    
    @PostMapping("/addCategoria")
    public Categoria addCategoria(@RequestBody Categoria c){
        return service.saveCategoria(c);
    }
    
    @PostMapping("/addCategorias")
    public List<Categoria> addCategorias(@RequestBody List<Categoria> categorias){
        return service.saveCategorias(categorias);
    }
    
    @GetMapping("/categorias")
    public List<Categoria> findAllCategorias(){
        return service.getCategorias();
    }
    
    @GetMapping("/categoriaById/{id}")
    public Categoria findCategoriaById(@PathVariable int id){
        return service.getCategoriaById(id);
    }
    
    @GetMapping("/categoriaByNombre/{nombre}")
    public Categoria findCategoriaByNombre(@PathVariable String nombre){
        return service.getCategoriaByNombre(nombre);
    }
    
    @DeleteMapping("/deleteCategoria/{id}")
    public String deleteCategoria(@PathVariable int id){
        return service.deleteCategoria(id);
    }
    
    @PutMapping("/updateCategoria")
    public Categoria updateCategoria(@RequestBody Categoria c){
        return service.updateCategoria(c);
    }
    
}
