package com.pasteleria.service;

import com.pasteleria.Entity.DetalleBoleta;
import com.pasteleria.repository.DetalleBoletaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristóbal Pérez
 */
@Service
public class DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository repository;

    public DetalleBoleta saveDetalleBoleta(DetalleBoleta d) {
        return repository.save(d);
    }

    public List<DetalleBoleta> saveDetallesBoleta(List<DetalleBoleta> detalles) {
        return repository.saveAll(detalles);
    }

    public List<DetalleBoleta> getDetallesBoleta() {
        return repository.findAll();
    }

    public DetalleBoleta getDetalleBoletaById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<DetalleBoleta> getDetallesByBoleta(Integer idBoleta) {
        return repository.findByIdBoleta(idBoleta);
    }

    public void deleteDetalleBoleta(Integer id) {
        repository.deleteById(id);
    }

    public DetalleBoleta updateDetalleBoleta(Integer id, DetalleBoleta d) {
        DetalleBoleta existing = repository.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }

        existing.setIdBoleta(d.getIdBoleta());
        existing.setIdProducto(d.getIdProducto());
        existing.setCantidad(d.getCantidad());
        existing.setPrecioUnitario(d.getPrecioUnitario());
        existing.setSubtotal(d.getSubtotal());

        return repository.save(existing);
    }
}
