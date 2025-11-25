package com.pasteleria.Entity;

import jakarta.persistence.*;

/**
 *
 * @author Cristóbal Pérez
 */
@Entity
@Table(name="USUARIO")
public class Usuario {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellido")
    private String apellido;
    
    @Column(name="email", nullable=false, length=200)
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name="direccion")
    private String direccion;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido 
                + ", email=" + email + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
}
