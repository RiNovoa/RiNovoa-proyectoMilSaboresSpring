package com.pasteleria.service;

import com.pasteleria.Entity.DetallePedido;
import com.pasteleria.repository.DetallePedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */

@Service
public class DetallePedidoService {
    
    @Autowired
    private DetallePedidoRepository repository;
    
    //CRUD:
    public DetallePedido saveDetallePedido(DetallePedido d){
        return repository.save(d);
    }
    
    public List<DetallePedido> saveDetallesPedido(List<DetallePedido> detalles){
        return repository.saveAll(detalles);
    }
    
    public List<DetallePedido> getDetallesPedido(){
        return repository.findAll();
    }
    
    public DetallePedido getDetallePedidoById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public List<DetallePedido> getDetallesByIdPedido(Integer idPedido){
        return repository.findByIdPedido(idPedido);
    }
    
    public String deleteDetallePedido(int id){
        repository.deleteById(id);
        return "DetallePedido eliminado!! " + id;
    }
    
    public DetallePedido updateDetallePedido(DetallePedido d){
        DetallePedido existingDetalle = repository.findById(d.getId()).orElse(null);
        existingDetalle.setIdPedido( d.getIdPedido() );
        existingDetalle.setIdProducto( d.getIdProducto() );
        existingDetalle.setCantidad( d.getCantidad() );
        existingDetalle.setPrecioUnitario( d.getPrecioUnitario() );
        existingDetalle.setSubtotal( d.getSubtotal() );
        return repository.save(existingDetalle);
    }
    
}
