package com.pasteleria.Controller;

import com.pasteleria.Entity.Producto;
import com.pasteleria.security.ApiKeyService;
import com.pasteleria.service.ProductoService;
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
@RequestMapping("/api/v1/productos")
@CrossOrigin(origins = "http://localhost:5173") // Front Vite
@Tag(name = "Productos", description = "Operaciones CRUD de productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Autowired
    private ApiKeyService apiKeyService;

    // SOLO ADMIN 

    // Crear un producto
    @PostMapping
    public ResponseEntity<Producto> addProducto(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody Producto p) {

        apiKeyService.validate(apiKey);
        p.setId(null);
        Producto creado = service.saveProducto(p);
        return ResponseEntity.ok(creado);
    }

    
    @PostMapping("/lote")
    public ResponseEntity<List<Producto>> addProductos(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @RequestBody List<Producto> productos) {

        apiKeyService.validate(apiKey);
        productos.forEach(prod -> prod.setId(null));
        List<Producto> creados = service.saveProductos(productos);
        return ResponseEntity.ok(creados);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id) {

        apiKeyService.validate(apiKey);
        service.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @RequestHeader(value = "X-API-KEY", required = false) String apiKey,
            @PathVariable Integer id,
            @RequestBody Producto p) {

        apiKeyService.validate(apiKey);
        p.setId(id);
        Producto actualizado = service.updateProducto(id, p);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // ================== RUTAS PÚBLICAS (TIENDA / CLIENTE) ==================

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Producto>> findAllProductos() {
        List<Producto> lista = service.getProductos();
        return ResponseEntity.ok(lista);
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Producto> findProductoById(@PathVariable Integer id) {
        Producto p = service.getProductoById(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    // Buscar por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Producto> findProductoByCodigo(@PathVariable String codigo) {
        Producto p = service.getProductoByCodigo(codigo);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }
}
