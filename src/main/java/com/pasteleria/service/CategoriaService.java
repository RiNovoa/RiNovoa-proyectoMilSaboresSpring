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

    // Crear una categoría
    public Categoria saveCategoria(Categoria c) {
        return repository.save(c);
    }

    // Crear varias categorías
    public List<Categoria> saveCategorias(List<Categoria> categorias) {
        return repository.saveAll(categorias);
    }

    // Listar todas
    public List<Categoria> getCategorias() {
        return repository.findAll();
    }

    // Buscar por id
    public Categoria getCategoriaById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Buscar por nombre
    public Categoria getCategoriaByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    // Eliminar
    public void deleteCategoria(Integer id) {
        repository.deleteById(id);
    }

    // Actualizar
    public Categoria updateCategoria(Integer id, Categoria c) {
        Categoria existingCategoria = repository.findById(id).orElse(null);
        if (existingCategoria == null) {
            return null;
        }

        existingCategoria.setNombre(c.getNombre());
        existingCategoria.setDescripcion(c.getDescripcion());

        return repository.save(existingCategoria);
    }

}
