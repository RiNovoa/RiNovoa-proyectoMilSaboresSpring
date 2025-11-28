package com.pasteleria.repository;

import com.pasteleria.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    Producto findByCodigo(String codigo);

}
