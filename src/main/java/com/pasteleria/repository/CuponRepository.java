package com.pasteleria.repository;

import com.pasteleria.Entity.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface CuponRepository extends JpaRepository<Cupon, Integer>{
    
    public Cupon findByCodigo(String codigo);
    
}
