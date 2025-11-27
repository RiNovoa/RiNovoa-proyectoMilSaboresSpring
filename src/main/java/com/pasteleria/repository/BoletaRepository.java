package com.pasteleria.repository;

import com.pasteleria.Entity.Boleta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface BoletaRepository extends JpaRepository<Boleta, Integer> {

    List<Boleta> findByIdUsuario(Integer idUsuario);

}
