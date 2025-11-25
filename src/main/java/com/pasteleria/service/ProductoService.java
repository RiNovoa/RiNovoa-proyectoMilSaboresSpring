package com.pasteleria.service;

import com.pasteleria.Entity.Producto;
import com.pasteleria.repository.ProductoRepository;
import java.util.List;
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

    // CRUD:

    public Producto saveProducto(Producto p) {
        return repository.save(p);
    }

    public List<Producto> saveProductos(List<Producto> productos) {
        return repository.saveAll(productos);
    }

    public List<Producto> getProductos() {
        return repository.findAll();
    }

    public Producto getProductoById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Producto getProductoByCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }

    public String deleteProducto(int id) {
        repository.deleteById(id);
        return "Producto eliminado!! " + id;
    }

    public Producto updateProducto(Producto p) {
        Producto existing = repository.findById(p.getId()).orElse(null);

        if (existing == null) {
            return null;
        }

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
