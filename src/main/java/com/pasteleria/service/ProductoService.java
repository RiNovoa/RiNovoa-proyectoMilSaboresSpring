package com.pasteleria.service;

import com.pasteleria.Entity.Producto;
import com.pasteleria.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    // Crear uno
    public Producto saveProducto(Producto p) {
        return repository.save(p);
    }

    // Crear varios
    public List<Producto> saveProductos(List<Producto> productos) {
        return repository.saveAll(productos);
    }

    // Listar todos
    public List<Producto> getProductos() {
        return repository.findAll();
    }

    // Buscar por id
    public Producto getProductoById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Buscar por código
    public Producto getProductoByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    // Eliminar
    public void deleteProducto(Integer id) {
        repository.deleteById(id);
    }

    // Actualizar
    public Producto updateProducto(Integer id, Producto p) {
        Optional<Producto> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }

        Producto existing = opt.get();
        existing.setCodigo(p.getCodigo());
        existing.setNombre(p.getNombre());
        existing.setCategoria(p.getCategoria());   
        existing.setPrecio_clp(p.getPrecio_clp());
        existing.setDescripcion(p.getDescripcion());
        existing.setImagen(p.getImagen());
        existing.setStock(p.getStock());
        existing.setActivo(p.getActivo());

        return repository.save(existing);
    }

}
