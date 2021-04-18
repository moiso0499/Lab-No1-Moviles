/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.tipoavion;

import com.google.gson.Gson;
import datos.Dao_avion;
import datos.Dao_ruta;
import datos.Dao_tipoavion;
import java.util.List;
import logica.Avion;
import logica.Ruta;
import logica.Tipoavion;

/**
 *
 * @author Moi
 */
public class Controller {

    public String enviarListaTipoAvion() {
        Gson gson = new Gson();
        try {
            List<Tipoavion> lista = Dao_tipoavion.obtenerListaTipoavion();
            String json = "{\"codigo\": 1, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }

    public String enviarListaRuta() {
        Gson gson = new Gson();
        try {
            List<Ruta> lista = Dao_ruta.obtenerListaRuta();
            String json = "{\"codigo\": 2, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }

    public String enviarListaAvion() {
        Gson gson = new Gson();
        try {
            List<Avion> lista = Dao_avion.obtenerListaAvion();
            String json = "{\"codigo\": 3, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }

    public void agregarAvion(String data) {
        Gson gson = new Gson();
        try {
            Avion avion = gson.fromJson(data, Avion.class);
            Dao_avion.insertarAvion(avion);
        } catch (Exception e) {
        }
    }

    public void agregarTipoavion(String data) {
        Gson gson = new Gson();
        try {
            Tipoavion tipoavion = gson.fromJson(data, Tipoavion.class);
            Dao_tipoavion.insertarTipoavion(tipoavion);
        } catch (Exception e) {
        }
    }

    public void eliminarAvion(String data) {
        Gson gson = new Gson();
        try {
            Avion avion = gson.fromJson(data, Avion.class);
            Dao_avion.eliminarAvion_id(avion.getId());
        } catch (Exception e) {
        }
    }
    
    public void editarAvion(String data){
        Gson gson = new Gson();
        try {
            Avion avion = gson.fromJson(data, Avion.class);
            Dao_avion.editarAvion(avion);
        } catch (Exception e) {
        }
    }

    public void eliminarTipoavion(String data) {
        Gson gson = new Gson();
        try {
            Tipoavion tipoavion = gson.fromJson(data, Tipoavion.class);
            Dao_tipoavion.eliminarTipoavion_id(tipoavion.getId());
        } catch (Exception e) {
        }
    }

}
