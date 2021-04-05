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
public class Tiquete {
    Compra compra_id;
    int asiento;
    Vuelo vuelo_id;

    public Tiquete() {
    }

    public Tiquete(Compra compra_id, int asiento) {
        this.compra_id = compra_id;
        this.asiento = asiento;
    }

    public Tiquete(Compra compra_id, int asiento, Vuelo vuelo_id) {
        this.compra_id = compra_id;
        this.asiento = asiento;
        this.vuelo_id = vuelo_id;
    }

    public Vuelo getVuelo_id() {
        return vuelo_id;
    }

    public void setVuelo_id(Vuelo vuelo_id) {
        this.vuelo_id = vuelo_id;
    }
    
    public Compra getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(Compra compra_id) {
        this.compra_id = compra_id;
    }

    public int getAsiento() {
        return asiento;
    }

    public void setAsiento(int asiento) {
        this.asiento = asiento;
    }
    
    
}
