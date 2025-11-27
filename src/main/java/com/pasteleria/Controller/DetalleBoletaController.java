package com.pasteleria.Controller;

import com.pasteleria.Entity.DetalleBoleta;
import com.pasteleria.service.DetalleBoletaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Cristóbal Pérez
 */
@RestController
@RequestMapping("/api/v1/detalle-boleta")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Detalle Boleta", description = "Gestión de ítems de boletas (antes detalles de pedido)")
public class DetalleBoletaController {

    @Autowired
    private DetalleBoletaService service;

    @PostMapping
    public ResponseEntity<DetalleBoleta> addDetalleBoleta(@RequestBody DetalleBoleta d) {
        // Aseguramos INSERT
        d.setId(null);
        DetalleBoleta creado = service.saveDetalleBoleta(d);
        return ResponseEntity.ok(creado);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<DetalleBoleta>> addDetallesBoleta(@RequestBody List<DetalleBoleta> detalles) {
        // Aseguramos INSERT para todos
        detalles.forEach(det -> det.setId(null));
        List<DetalleBoleta> creados = service.saveDetallesBoleta(detalles);
        return ResponseEntity.ok(creados);
    }

    @GetMapping
    public ResponseEntity<List<DetalleBoleta>> findAllDetalles() {
        return ResponseEntity.ok(service.getDetallesBoleta());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleBoleta> findDetalleById(@PathVariable Integer id) {
        DetalleBoleta d = service.getDetalleBoletaById(id);
        if (d == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(d);
    }

    @GetMapping("/boleta/{idBoleta}")
    public ResponseEntity<List<DetalleBoleta>> findDetallesByBoleta(@PathVariable Integer idBoleta) {
        return ResponseEntity.ok(service.getDetallesByBoleta(idBoleta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleBoleta(@PathVariable Integer id) {
        service.deleteDetalleBoleta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleBoleta> updateDetalleBoleta(
            @PathVariable Integer id,
            @RequestBody DetalleBoleta d
    ) {
        DetalleBoleta updated = service.updateDetalleBoleta(id, d);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }
}
