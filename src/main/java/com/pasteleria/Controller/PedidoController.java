package com.pasteleria.Controller;

import com.pasteleria.Entity.Pedido;
import com.pasteleria.service.PedidoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristóbal Pérez
 */
@RestController
public class PedidoController {
    
    @Autowired
    private PedidoService service;
    
    @PostMapping("/addPedido")
    public Pedido addPedido(@RequestBody Pedido p){
        return service.savePedido(p);
    }
    
    @PostMapping("/addPedidos")
    public List<Pedido> addPedidos(@RequestBody List<Pedido> pedidos){
        return service.savePedidos(pedidos);
    }
    
    @GetMapping("/pedidos")
    public List<Pedido> findAllPedidos(){
        return service.getPedidos();
    }
    
    @GetMapping("/pedidoById/{id}")
    public Pedido findPedidoById(@PathVariable int id){
        return service.getPedidoById(id);
    }
    
    @GetMapping("/pedidosByUsuario/{idUsuario}")
    public List<Pedido> findPedidosByUsuario(@PathVariable Integer idUsuario){
        return service.getPedidosByIdUsuario(idUsuario);
    }
    
    @DeleteMapping("/deletePedido/{id}")
    public String deletePedido(@PathVariable int id){
        return service.deletePedido(id);
    }
    
    @PutMapping("/updatePedido")
    public Pedido updatePedido(@RequestBody Pedido p){
        return service.updatePedido(p);
    }
    
}
