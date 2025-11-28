package com.pasteleria.repository;

import com.pasteleria.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByNombre(String nombre);

}
