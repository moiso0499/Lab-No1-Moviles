/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;

/**
 *
 * @author Moi
 */
public class Usuario {
    String id;
    String nombre;
    String apellido;
    String clave;
    String correo;
    String direccion;
    boolean admin;
    List<Compra> compras;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String apellido, String clave, String correo, String direccion, boolean admin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.correo = correo;
        this.direccion = direccion;
        this.admin = admin;
    }

    public Usuario(String id, String nombre, String apellido, String clave, String correo, String direccion, boolean admin, List<Compra> compras) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.correo = correo;
        this.direccion = direccion;
        this.admin = admin;
        this.compras = compras;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
    
    
}
