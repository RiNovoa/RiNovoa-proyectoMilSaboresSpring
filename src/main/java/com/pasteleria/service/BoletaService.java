package com.pasteleria.service;

import com.pasteleria.Entity.Boleta;
import com.pasteleria.repository.BoletaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */
@Service
public class BoletaService {

    @Autowired
    private BoletaRepository repository;

    // Crear una boleta
    public Boleta saveBoleta(Boleta b) {
        return repository.save(b);
    }

    // Crear varias boletas
    public List<Boleta> saveBoletas(List<Boleta> boletas) {
        return repository.saveAll(boletas);
    }

    // Listar todas
    public List<Boleta> getBoletas() {
        return repository.findAll();
    }

    // Buscar por id
    public Boleta getBoletaById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Buscar por usuario
    public List<Boleta> getBoletasByIdUsuario(Integer idUsuario) {
        return repository.findByIdUsuario(idUsuario);
    }

    // Eliminar
    public void deleteBoleta(Integer id) {
        repository.deleteById(id);
    }

    // Actualizar
    public Boleta updateBoleta(Integer id, Boleta b) {
        Boleta existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setFecha(b.getFecha());
        existing.setTotal(b.getTotal());
        existing.setEstado(b.getEstado());
        existing.setMedio_pago(b.getMedio_pago());
        existing.setIdUsuario(b.getIdUsuario());

        return repository.save(existing);
    }

}
