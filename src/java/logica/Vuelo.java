/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Moi
 */
public class Vuelo {
    int id;
    LocalDate fecha;
    LocalTime hora_salida;
    double precio;
    Avion avion_id;
    List<Descuento> descuentos;

    public Vuelo() {
    }

    public Vuelo(int id, LocalDate fecha, LocalTime hora_salida, double precio, Avion avion_id) {
        this.id = id;
        this.fecha = fecha;
        this.hora_salida = hora_salida;
        this.precio = precio;
        this.avion_id = avion_id;
    }

    public Vuelo(int id, LocalDate fecha, LocalTime hora_salida, double precio, Avion avion_id, List<Descuento> descuentos) {
        this.id = id;
        this.fecha = fecha;
        this.hora_salida = hora_salida;
        this.precio = precio;
        this.avion_id = avion_id;
        this.descuentos = descuentos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(LocalTime hora_salida) {
        this.hora_salida = hora_salida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Avion getAvion_id() {
        return avion_id;
    }

    public void setAvion_id(Avion avion_id) {
        this.avion_id = avion_id;
    }

    public List<Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(List<Descuento> descuentos) {
        this.descuentos = descuentos;
    }
    
}
