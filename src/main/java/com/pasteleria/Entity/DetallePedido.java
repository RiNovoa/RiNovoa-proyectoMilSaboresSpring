package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name="DETALLE_PEDIDO")
public class DetallePedido {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="id_pedido")
    private Integer idPedido;
    
    @Column(name="id_producto")
    private Integer idProducto;
    
    @Column(name="cantidad")
    private int cantidad;
    
    @Column(name="precio_unitario")
    private int precioUnitario;
    
    @Column(name="subtotal")
    private int subtotal;

    // Getters y Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }    

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }    

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }    

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }    

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }    

    @Override
    public String toString() {
        return "DetallePedido{" + "id=" + id + ", idPedido=" + idPedido 
                + ", idProducto=" + idProducto + ", cantidad=" + cantidad 
                + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }
    
}
