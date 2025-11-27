package com.pasteleria.service;

import com.pasteleria.Entity.Boleta;
import com.pasteleria.Entity.DetalleBoleta;
import com.pasteleria.Entity.Producto;
import com.pasteleria.Entity.Usuario;
import com.pasteleria.Entity.RolUsuario;
import com.pasteleria.dto.CheckoutItemDTO;
import com.pasteleria.dto.CheckoutRequest;      
import com.pasteleria.repository.BoletaRepository;
import com.pasteleria.repository.DetalleBoletaRepository;
import com.pasteleria.repository.ProductoRepository;
import com.pasteleria.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Cristóbal Pérez
 */
@Service
public class BoletaService {

    @Autowired
    private BoletaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    // Crear una boleta normal (si la quieres usar aparte del checkout)
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

    // ===================== CHECKOUT SIMPLE =====================
    // Crea la boleta, los detalles y descuenta stock en UNA sola transacción
    @Transactional
    public Boleta procesarCheckout(CheckoutRequest req) {

        // 1) Buscar usuario
        Usuario u = usuarioRepository.findById(req.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Opcional: solo permitir CLIENTE
        if (u.getRol() != RolUsuario.CLIENTE) {
            throw new RuntimeException("Solo los clientes pueden realizar compras");
        }

        // 2) Crear boleta base (sin total todavía)
        Boleta boleta = new Boleta();
        boleta.setFecha(LocalDate.now().toString());
        boleta.setEstado("pagado");
        boleta.setMedio_pago(req.getMedioPago());
        boleta.setIdUsuario(u.getId());
        boleta.setTotal(0);

        boleta = repository.save(boleta); // para obtener el ID

        int total = 0;

        // 3) Recorrer los ítems del carrito
        for (CheckoutItemDTO item : req.getItems()) {

            Producto producto = productoRepository.findById(item.getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getIdProducto()));

            // Validar stock
            if (producto.getStock() == null || producto.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para producto: " + producto.getNombre());
            }

            // Descontar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);

            // Precio unitario desde la BD (más seguro que lo mande el front)
            int precioUnitario = producto.getPrecio_clp();
            int subtotal = precioUnitario * item.getCantidad();

            // Crear detalle de boleta
            DetalleBoleta det = new DetalleBoleta();
            det.setIdBoleta(boleta.getId());
            det.setIdProducto(producto.getId());
            det.setCantidad(item.getCantidad());
            det.setPrecioUnitario(precioUnitario);
            det.setSubtotal(subtotal);

            detalleBoletaRepository.save(det);

            total += subtotal;
        }

        // 4) Actualizar total en la boleta
        boleta.setTotal(total);
        return repository.save(boleta);
    }
}
