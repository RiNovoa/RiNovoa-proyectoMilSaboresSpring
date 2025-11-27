package com.pasteleria.Controller;

import com.pasteleria.Entity.Categoria;
import com.pasteleria.service.CategoriaService;
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
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Categorias", description = "CRUD de categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    // Crear una categoría
    @PostMapping
    public ResponseEntity<Categoria> addCategoria(@RequestBody Categoria c) {
        // Forzamos INSERT (ignoramos id que venga en el JSON)
        c.setId(null);
        Categoria creada = service.saveCategoria(c);
        return ResponseEntity.ok(creada);
    }

    // Crear varias categorías (carga inicial)
    @PostMapping("/lote")
    public ResponseEntity<List<Categoria>> addCategorias(@RequestBody List<Categoria> categorias) {
        categorias.forEach(cat -> cat.setId(null));
        List<Categoria> creadas = service.saveCategorias(categorias);
        return ResponseEntity.ok(creadas);
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Categoria>> findAllCategorias() {
        return ResponseEntity.ok(service.getCategorias());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
        Categoria c = service.getCategoriaById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    // Buscar por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> findCategoriaByNombre(@PathVariable String nombre) {
        Categoria c = service.getCategoriaByNombre(nombre);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Integer id) {
        service.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(
            @PathVariable Integer id,
            @RequestBody Categoria c
    ) {
        Categoria actualizada = service.updateCategoria(id, c);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }
}
