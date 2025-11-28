package com.pasteleria.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name = "BOLETA")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; // <-- Integer, no int

    @Column(name = "fecha", nullable = false, length = 20)
    private String fecha;

    @Column(name = "total", nullable = false)
    private Integer total;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "medio_pago", length = 50)
    private String medio_pago;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    // 🔗 Relación MANY-TO-ONE con Usuario
    // Usa la misma columna id_usuario. Como ya tenemos idUsuario,
    // marcamos insertable/updatable en false para no duplicar.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    // 🔗 Relación ONE-TO-MANY con DetalleBoleta
    @OneToMany(mappedBy = "boleta", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DetalleBoleta> detalles;

    // ===== Constructor vacío requerido por JPA =====
    public Boleta() {
    }

    // ===== Getters y Setters =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        // opcionalmente podrías sincronizar idUsuario:
        // this.idUsuario = (usuario != null ? usuario.getId() : null);
    }

    public List<DetalleBoleta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleBoleta> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Boleta{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", medio_pago='" + medio_pago + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
