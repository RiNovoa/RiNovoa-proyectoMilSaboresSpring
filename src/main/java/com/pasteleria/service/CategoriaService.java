package com.pasteleria.service;

import com.pasteleria.Entity.Categoria;
import com.pasteleria.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repository;
    
    //CRUD:
    public Categoria saveCategoria(Categoria c){
        return repository.save(c);
    }
    
    public List<Categoria> saveCategorias(List<Categoria> categorias){
        return repository.saveAll(categorias);
    }
    
    public List<Categoria> getCategorias(){
        return repository.findAll();
    }
    
    public Categoria getCategoriaById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public Categoria getCategoriaByNombre(String nombre){
        return repository.findByNombre(nombre);
    }
    
    public String deleteCategoria(int id){
        repository.deleteById(id);
        return "Categoria eliminada!! " + id;
    }
    
    public Categoria updateCategoria(Categoria c){
        Categoria existingCategoria = repository.findById(c.getId()).orElse(null);
        existingCategoria.setNombre( c.getNombre() );
        existingCategoria.setDescripcion( c.getDescripcion() );
        return repository.save(existingCategoria);
    }
    
}
