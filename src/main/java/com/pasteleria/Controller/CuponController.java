package com.pasteleria.Controller;

import com.pasteleria.Entity.Cupon;
import com.pasteleria.service.CuponService;
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
public class CuponController {
    
    @Autowired
    private CuponService service;
    
    @PostMapping("/addCupon")
    public Cupon addCupon(@RequestBody Cupon c){
        return service.saveCupon(c);
    }
    
    @PostMapping("/addCupones")
    public List<Cupon> addCupones(@RequestBody List<Cupon> cupones){
        return service.saveCupones(cupones);
    }
    
    @GetMapping("/cupones")
    public List<Cupon> findAllCupones(){
        return service.getCupones();
    }
    
    @GetMapping("/cuponById/{id}")
    public Cupon findCuponById(@PathVariable int id){
        return service.getCuponById(id);
    }
    
    @GetMapping("/cuponByCodigo/{codigo}")
    public Cupon findCuponByCodigo(@PathVariable String codigo){
        return service.getCuponByCodigo(codigo);
    }
    
    @DeleteMapping("/deleteCupon/{id}")
    public String deleteCupon(@PathVariable int id){
        return service.deleteCupon(id);
    }
    
    @PutMapping("/updateCupon")
    public Cupon updateCupon(@RequestBody Cupon c){
        return service.updateCupon(c);
    }
    
}
