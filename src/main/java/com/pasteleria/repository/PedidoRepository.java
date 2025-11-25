package com.pasteleria.repository;

import com.pasteleria.Entity.Pedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Cristóbal Pérez
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    
    public List<Pedido> findByIdUsuario(Integer idUsuario);
    
}
