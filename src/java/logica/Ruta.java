/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Moi
 */
public class Ruta {
    int id;
    LocalTime duracion;
    Ubicacion origen;
    Ubicacion destino;
    List<Avion> aviones;
    
    public Ruta() {
    }

    public Ruta(int id, LocalTime duracion, Ubicacion origen, Ubicacion destino) {
        this.id = id;
        this.duracion = duracion;
        this.origen = origen;
        this.destino = destino;
    }

    public Ruta(int id, LocalTime duracion, Ubicacion origen, Ubicacion destino, List<Avion> aviones) {
        this.id = id;
        this.duracion = duracion;
        this.origen = origen;
        this.destino = destino;
        this.aviones = aviones;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public Ubicacion getOrigen() {
        return origen;
    }

    public void setOrigen(Ubicacion origen) {
        this.origen = origen;
    }

    public Ubicacion getDestino() {
        return destino;
    }

    public void setDestino(Ubicacion destino) {
        this.destino = destino;
    }

    public List<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(List<Avion> aviones) {
        this.aviones = aviones;
    }
    
    
}
