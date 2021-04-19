/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.compra;

import com.google.gson.Gson;
import datos.Dao_compra;
import datos.Dao_tiquete;
import datos.Dao_ubicacion;
import datos.Dao_vuelo;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import logica.Compra;
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
    
    public String guardarCompra(String data, String tiquetes){
        Gson gson = new Gson();
        try {
            int id_compra = Dao_compra.cantidadActualCompras() + 1;
            // --
            Compra compra = gson.fromJson(data, Compra.class);
            Dao_compra.insertarCompra(compra);
            Tiquete[] mcArray = gson.fromJson(tiquetes, Tiquete[].class);
            List<Tiquete> lista = Arrays.asList(mcArray);
            for (Tiquete t : lista) {
                t.setCompra_id(compra);
                Dao_tiquete.insertarTiquete(t, id_compra);
            }
            
            return "{\"codigo\": 4}";
        } catch (Exception e) {
            return "{\"codigo\": 5}";
        }
    }
}
