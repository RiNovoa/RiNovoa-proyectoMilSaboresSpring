package com.pasteleria.Controller;

import com.pasteleria.Entity.Boleta;
import com.pasteleria.dto.CheckoutRequest;
import com.pasteleria.service.BoletaService;
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
@RequestMapping("/api/v1/boletas")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "Boletas", description = "Gestión de boletas (antes pedidos)")
public class BoletaController {

    @Autowired
    private BoletaService service;

    // Crear una boleta (uso general)
    @PostMapping
    public ResponseEntity<Boleta> addBoleta(@RequestBody Boleta b) {
        b.setId(null);
        Boleta creada = service.saveBoleta(b);
        return ResponseEntity.ok(creada);
    }

    // Crear varias boletas (por si haces carga masiva / pruebas)
    @PostMapping("/lote")
    public ResponseEntity<List<Boleta>> addBoletas(@RequestBody List<Boleta> boletas) {
        boletas.forEach(boleta -> boleta.setId(null));
        List<Boleta> creadas = service.saveBoletas(boletas);
        return ResponseEntity.ok(creadas);
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Boleta>> findAllBoletas() {
        return ResponseEntity.ok(service.getBoletas());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Boleta> findBoletaById(@PathVariable Integer id) {
        Boleta b = service.getBoletaById(id);
        if (b == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(b);
    }

    // Buscar boletas por id de usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Boleta>> findBoletasByUsuario(@PathVariable Integer idUsuario) {
        List<Boleta> lista = service.getBoletasByIdUsuario(idUsuario);
        return ResponseEntity.ok(lista);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoleta(@PathVariable Integer id) {
        service.deleteBoleta(id);
        return ResponseEntity.noContent().build();
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Boleta> updateBoleta(
            @PathVariable Integer id,
            @RequestBody Boleta b
    ) {
        Boleta actualizada = service.updateBoleta(id, b);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }


    // Se llama desde el frontend cuando el pago (PayPal) es exitoso.
    @PostMapping("/checkout")
    public ResponseEntity<Boleta> checkout(@RequestBody CheckoutRequest req) {
        Boleta boleta = service.procesarCheckout(req);
        return ResponseEntity.ok(boleta);
    }
}
