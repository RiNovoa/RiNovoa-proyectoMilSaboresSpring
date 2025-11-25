package com.pasteleria.service;

import com.pasteleria.Entity.Pedido;
import com.pasteleria.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository repository;
    
    //CRUD:
    public Pedido savePedido(Pedido p){
        return repository.save(p);
    }
    
    public List<Pedido> savePedidos(List<Pedido> pedidos){
        return repository.saveAll(pedidos);
    }
    
    public List<Pedido> getPedidos(){
        return repository.findAll();
    }
    
    public Pedido getPedidoById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public List<Pedido> getPedidosByIdUsuario(Integer idUsuario){
        return repository.findByIdUsuario(idUsuario);
    }
    
    public String deletePedido(int id){
        repository.deleteById(id);
        return "Pedido eliminado!! " + id;
    }
    
    public Pedido updatePedido(Pedido p){
        Pedido existingPedido = repository.findById(p.getId()).orElse(null);
        existingPedido.setFecha( p.getFecha() );
        existingPedido.setTotal( p.getTotal() );
        existingPedido.setEstado( p.getEstado() );
        existingPedido.setMedio_pago( p.getMedio_pago() );
        existingPedido.setIdUsuario( p.getIdUsuario() );
        return repository.save(existingPedido);
    }
    
}
