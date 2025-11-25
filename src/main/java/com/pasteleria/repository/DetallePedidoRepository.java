package com.pasteleria.repository;

import com.pasteleria.Entity.DetallePedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer>{
    
    public List<DetallePedido> findByIdPedido(Integer idPedido);
    
}
