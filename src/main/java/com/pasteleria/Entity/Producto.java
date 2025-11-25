package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false, length = 200)
    private String nombre;

    private String categoria;

    
    @Column(name = "precio_clp")
    private Integer precio_clp;

    @Column(length = 1000)
    private String descripcion;

    private String imagen;

    private Integer stock;

    private Boolean activo;

    // ===== Getters y Setters =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getPrecio_clp() {
        return precio_clp;
    }

    public void setPrecio_clp(Integer precio_clp) {
        this.precio_clp = precio_clp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Producto{" 
                + "id=" + id 
                + ", codigo=" + codigo 
                + ", nombre=" + nombre 
                + ", categoria=" + categoria 
                + ", precio_clp=" + precio_clp 
                + ", descripcion=" + descripcion 
                + ", imagen=" + imagen 
                + ", stock=" + stock 
                + ", activo=" + activo 
                + '}';
    }

}
