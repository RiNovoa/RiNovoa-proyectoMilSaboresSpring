package com.pasteleria.service;

import com.pasteleria.Entity.Cupon;
import com.pasteleria.repository.CuponRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */

@Service
public class CuponService {
    
    @Autowired
    private CuponRepository repository;
    
    //CRUD:
    public Cupon saveCupon(Cupon c){
        return repository.save(c);
    }
    
    public List<Cupon> saveCupones(List<Cupon> cupones){
        return repository.saveAll(cupones);
    }
    
    public List<Cupon> getCupones(){
        return repository.findAll();
    }
    
    public Cupon getCuponById(int id){
        return repository.findById(id).orElse(null);
    }
    
    public Cupon getCuponByCodigo(String codigo){
        return repository.findByCodigo(codigo);
    }
    
    public String deleteCupon(int id){
        repository.deleteById(id);
        return "Cupon eliminado!! " + id;
    }
    
    public Cupon updateCupon(Cupon c){
        Cupon existingCupon = repository.findById(c.getId()).orElse(null);
        existingCupon.setCodigo( c.getCodigo() );
        existingCupon.setDescripcion( c.getDescripcion() );
        existingCupon.setPorcentajeDescuento( c.getPorcentajeDescuento() );
        existingCupon.setFechaInicio( c.getFechaInicio() );
        existingCupon.setFechaFin( c.getFechaFin() );
        existingCupon.setActivo( c.getActivo() );
        return repository.save(existingCupon);
    }
    
}
