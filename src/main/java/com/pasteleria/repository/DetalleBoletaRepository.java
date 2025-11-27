package com.pasteleria.repository;

import com.pasteleria.Entity.DetalleBoleta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Integer> {

    List<DetalleBoleta> findByIdBoleta(Integer idBoleta);

}
