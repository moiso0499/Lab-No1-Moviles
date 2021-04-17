/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.ruta;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import datos.Dao_ruta;
import datos.Dao_ubicacion;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import logica.Ubicacion;
import logica.Ruta;
import org.json.simple.JSONObject;

/**
 *
 * @author Moi
 */
public class Controller {
    
    public String enviarListaUbicacion() {
        Gson gson = new Gson();
        try {
            List<Ubicacion> lista = Dao_ubicacion.obtenerListaUbicacion();
            String json = "{\"codigo\": 1, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
    
    public String enviarListaRuta(){
        Gson gson = new Gson();
        try {
            List<Ruta> lista = Dao_ruta.obtenerListaRuta();
            String json = "{\"codigo\": 2, \"lista\": "+gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
    
    public void agregarUbicacion(String data) {
        Gson gson = new Gson();
        try {
            Ubicacion ubicacion = gson.fromJson(data, Ubicacion.class);
            Dao_ubicacion.insertarUbicacion(ubicacion);
        } catch (Exception e) {
            
        }
    }
    
    public void agregarRuta(String data, String duracion) {
        Gson gson = new Gson();
        try {
            Ruta ruta = gson.fromJson(data, Ruta.class);
            ruta.setDuracion(LocalTime.parse(duracion));
            Dao_ruta.insertarRuta(ruta);
        } catch (Exception e) {
        }
    }
    
}
