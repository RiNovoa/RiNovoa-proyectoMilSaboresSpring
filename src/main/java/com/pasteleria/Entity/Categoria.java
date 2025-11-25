package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name="CATEGORIA")
public class Categoria {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="descripcion")
    private String descripcion;

    // Getters y Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    @Override
    public String toString() {
        return "Categoria{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + '}';
    }
    
}
