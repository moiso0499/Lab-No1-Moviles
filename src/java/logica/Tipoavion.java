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
public class Tipoavion {
    int id;
    int anyo;
    String modelo;
    int filas;
    int asientos_fila;
    List<Avion> aviones;

    public Tipoavion() {
    }

    public Tipoavion(int id, int anyo, String modelo, int filas, int asientos_fila) {
        this.id = id;
        this.anyo = anyo;
        this.modelo = modelo;
        this.filas = filas;
        this.asientos_fila = asientos_fila;
    }

    public Tipoavion(int id, int anyo, String modelo, int filas, int asientos_fila, List<Avion> aviones) {
        this.id = id;
        this.anyo = anyo;
        this.modelo = modelo;
        this.filas = filas;
        this.asientos_fila = asientos_fila;
        this.aviones = aviones;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getAsientos_fila() {
        return asientos_fila;
    }

    public void setAsientos_fila(int asientos_fila) {
        this.asientos_fila = asientos_fila;
    }

    public List<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(List<Avion> aviones) {
        this.aviones = aviones;
    }
    
    
}
