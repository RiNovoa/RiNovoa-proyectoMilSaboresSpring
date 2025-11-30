package com.pasteleria.Controller;

import com.pasteleria.Entity.Categoria;
import com.pasteleria.security.ApiKeyService;
import com.pasteleria.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Categorias", description = "CRUD de categorías de productos")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Autowired
    private ApiKeyService apiKeyService;

    // ================== SOLO ADMIN ==================

    // Crear una categoría
    @PostMapping
    public ResponseEntity<Categoria> addCategoria(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody Categoria c
    ) {
        apiKeyService.validate(apiKey);
        c.setId(null);
        Categoria creada = service.saveCategoria(c);
        return ResponseEntity.ok(creada);
    }

    // Crear varias categorías (lote)
    @PostMapping("/lote")
    public ResponseEntity<List<Categoria>> addCategorias(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody List<Categoria> categorias
    ) {
        apiKeyService.validate(apiKey);
        categorias.forEach(cat -> cat.setId(null));
        List<Categoria> creadas = service.saveCategorias(categorias);
        return ResponseEntity.ok(creadas);
    }

    // Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id
    ) {
        apiKeyService.validate(apiKey);
        service.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id,
            @RequestBody Categoria c
    ) {
        apiKeyService.validate(apiKey);
        Categoria actualizada = service.updateCategoria(id, c);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    // ================== RUTAS PÚBLICAS ==================

    // Listar todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(service.getCategorias());
    }

    // Buscar categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Integer id) {
        Categoria c = service.getCategoriaById(id);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    // Buscar categoría por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Categoria> getCategoriaByNombre(@PathVariable String nombre) {
        Categoria c = service.getCategoriaByNombre(nombre);
        return (c == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }
}
