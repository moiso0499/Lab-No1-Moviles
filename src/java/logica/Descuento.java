/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Moi
 */
public class Descuento {
 
    int id;
    Vuelo vuelo_id;
    double porcentaje;

    public Descuento() {
    }

    public Descuento(int id, Vuelo vuelo_id, double porcentaje) {
        this.id = id;
        this.vuelo_id = vuelo_id;
        this.porcentaje = porcentaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vuelo getVuelo_id() {
        return vuelo_id;
    }

    public void setVuelo_id(Vuelo vuelo_id) {
        this.vuelo_id = vuelo_id;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
}
