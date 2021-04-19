/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.compra;

import com.google.gson.Gson;
import datos.Dao_tiquete;
import datos.Dao_ubicacion;
import datos.Dao_vuelo;
import java.time.LocalDate;
import java.util.List;
import logica.Ruta;
import logica.Tiquete;
import logica.Ubicacion;
import logica.Vuelo;

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

    public String filtrarVuelo(String data, String date) {
        Gson gson = new Gson();
        try {
            Ruta ruta = gson.fromJson(data, Ruta.class);
            List<Vuelo> filtro = Dao_vuelo.obtenerListaVueloFiltrada(LocalDate.parse(date), ruta.getOrigen().getCodigo(), ruta.getDestino().getCodigo());
            String json = "{\"codigo\": 2, \"lista\": " + gson.toJson(filtro) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }

    public String enviarListaTiquetesVuelo(String id) {
        Gson gson = new Gson();
        try {
            List<Tiquete> lista = Dao_tiquete.obtenerListaTiquete_vuelo(Integer.parseInt(id));
            String json = "{\"codigo\": 3, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
}
