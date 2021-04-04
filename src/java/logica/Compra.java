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
public class Compra {
    
    int id;
    Usuario usuario_id;
    Vuelo vuelo_id;
    List<Tiquete> tiquetes;

    public Compra() {
    }

    public Compra(int id, Usuario usuario_id, Vuelo vuelo_id) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.vuelo_id = vuelo_id;
    }

    public Compra(int id, Usuario usuario_id, Vuelo vuelo_id, List<Tiquete> tiquetes) {
        this.id = id;
        this.usuario_id = usuario_id;
        this.vuelo_id = vuelo_id;
        this.tiquetes = tiquetes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Vuelo getVuelo_id() {
        return vuelo_id;
    }

    public void setVuelo_id(Vuelo vuelo_id) {
        this.vuelo_id = vuelo_id;
    }

    public List<Tiquete> getTiquetes() {
        return tiquetes;
    }

    public void setTiquetes(List<Tiquete> tiquetes) {
        this.tiquetes = tiquetes;
    }
    
    
}
