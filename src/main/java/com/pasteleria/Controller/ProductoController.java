package com.pasteleria.Controller;

import com.pasteleria.Entity.Producto;
import com.pasteleria.service.ProductoService;
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
public class ProductoController {

    @Autowired
    private ProductoService service;

    @PostMapping("/addProducto")
    public Producto addProducto(@RequestBody Producto p) {
        return service.saveProducto(p);
    }

    @PostMapping("/addProductos")
    public List<Producto> addProductos(@RequestBody List<Producto> productos) {
        return service.saveProductos(productos);
    }

    @GetMapping("/productos")
    public List<Producto> findAllProductos() {
        return service.getProductos();
    }

    @GetMapping("/productoById/{id}")
    public Producto findProductoById(@PathVariable int id) {
        return service.getProductoById(id);
    }

    @GetMapping("/productoByCodigo/{codigo}")
    public Producto findProductoByCodigo(@PathVariable String codigo) {
        return service.getProductoByCodigo(codigo);
    }

    @DeleteMapping("/deleteProducto/{id}")
    public String deleteProducto(@PathVariable int id) {
        return service.deleteProducto(id);
    }

    @PutMapping("/updateProducto")
    public Producto updateProducto(@RequestBody Producto p) {
        return service.updateProducto(p);
    }

}
