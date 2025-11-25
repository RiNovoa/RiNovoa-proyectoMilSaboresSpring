package com.pasteleria.Controller;

import com.pasteleria.Entity.DetallePedido;
import com.pasteleria.service.DetallePedidoService;
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
public class DetallePedidoController {
    
    @Autowired
    private DetallePedidoService service;
    
    @PostMapping("/addDetallePedido")
    public DetallePedido addDetallePedido(@RequestBody DetallePedido d){
        return service.saveDetallePedido(d);
    }
    
    @PostMapping("/addDetallesPedido")
    public List<DetallePedido> addDetallesPedido(@RequestBody List<DetallePedido> detalles){
        return service.saveDetallesPedido(detalles);
    }
    
    @GetMapping("/detallesPedido")
    public List<DetallePedido> findAllDetalles(){
        return service.getDetallesPedido();
    }
    
    @GetMapping("/detallePedidoById/{id}")
    public DetallePedido findDetalleById(@PathVariable int id){
        return service.getDetallePedidoById(id);
    }
    
    @GetMapping("/detallesByPedido/{idPedido}")
    public List<DetallePedido> findDetallesByPedido(@PathVariable Integer idPedido){
        return service.getDetallesByIdPedido(idPedido);
    }
    
    @DeleteMapping("/deleteDetallePedido/{id}")
    public String deleteDetallePedido(@PathVariable int id){
        return service.deleteDetallePedido(id);
    }
    
    @PutMapping("/updateDetallePedido")
    public DetallePedido updateDetallePedido(@RequestBody DetallePedido d){
        return service.updateDetallePedido(d);
    }
    
}
