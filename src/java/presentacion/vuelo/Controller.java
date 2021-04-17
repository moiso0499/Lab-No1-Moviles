/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.vuelo;

import com.google.gson.Gson;
import datos.Dao_avion;
import datos.Dao_vuelo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import logica.Avion;
import logica.Vuelo;

/**
 *
 * @author Moi
 */
public class Controller {

    public void agregarVuelo(String data, String fecha, String hora){
        Gson gson = new Gson();
        try {
            Vuelo vuelo = gson.fromJson(data, Vuelo.class);
            vuelo.setFecha(LocalDate.parse(fecha));
            vuelo.setHora_salida(LocalTime.parse(hora));
            Dao_vuelo.insertarVuelo(vuelo);
        } catch (Exception e) {
        }
    }
    
    public String enviarListaAvion() {
        Gson gson = new Gson();
        try {
            List<Avion> lista = Dao_avion.obtenerListaAvion();
            String json = "{\"codigo\": 1, \"lista\": "+gson.toJson(lista)+"}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
    
    public String enviarListaVuelo(){
        Gson gson = new Gson();
        try {
            List<Vuelo> lista = Dao_vuelo.obtenerListaVuelo();
            String json = "{\"codigo\": 2, \"lista\": "+gson.toJson(lista)+"}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
}
