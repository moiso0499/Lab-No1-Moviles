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
public class Avion {
    int id;
    Tipoavion tipoAvion_id;
    Ruta ruta_id;
    List<Vuelo> vuelos;

    public Avion() {
    }

    public Avion(int id, Tipoavion tipoAvion_id, Ruta ruta_id) {
        this.id = id;
        this.tipoAvion_id = tipoAvion_id;
        this.ruta_id = ruta_id;
    }

    public Avion(int id, Tipoavion tipoAvion_id, Ruta ruta_id, List<Vuelo> vuelos) {
        this.id = id;
        this.tipoAvion_id = tipoAvion_id;
        this.ruta_id = ruta_id;
        this.vuelos = vuelos;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipoavion getTipoAvion_id() {
        return tipoAvion_id;
    }

    public void setTipoAvion_id(Tipoavion tipoAvion_id) {
        this.tipoAvion_id = tipoAvion_id;
    }

    public Ruta getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(Ruta ruta_id) {
        this.ruta_id = ruta_id;
    }

    public List<Vuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<Vuelo> vuelos) {
        this.vuelos = vuelos;
    }
    
    
}
