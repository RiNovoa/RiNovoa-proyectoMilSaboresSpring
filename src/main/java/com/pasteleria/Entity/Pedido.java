package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name="PEDIDO")
public class Pedido {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="fecha")
    private String fecha;
    
    @Column(name="total")
    private int total;
    
    @Column(name="estado")
    private String estado;
    
    @Column(name="medio_pago")
    private String medio_pago;
    
    @Column(name="id_usuario")
    private Integer idUsuario;

    // Getters y Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }    

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
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

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", fecha=" + fecha + ", total=" + total 
                + ", estado=" + estado + ", medio_pago=" + medio_pago 
                + ", idUsuario=" + idUsuario + '}';
    }
    
}
