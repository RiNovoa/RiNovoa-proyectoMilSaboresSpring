package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name="CUPON")
public class Cupon {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="codigo")
    private String codigo;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="porcentaje_descuento")
    private int porcentajeDescuento;
    
    @Column(name="fecha_inicio")
    private String fechaInicio;
    
    @Column(name="fecha_fin")
    private String fechaFin;
    
    @Column(name="activo")
    private Boolean activo;

    // Getters y Setters:

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }    

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }    

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }    

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }    

    @Override
    public String toString() {
        return "Cupon{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion 
                + ", porcentajeDescuento=" + porcentajeDescuento + ", fechaInicio=" + fechaInicio 
                + ", fechaFin=" + fechaFin + ", activo=" + activo + '}';
    }
    
}
