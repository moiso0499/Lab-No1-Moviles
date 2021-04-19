/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.registroCompra;

import com.google.gson.Gson;
import datos.Dao_compra;
import datos.Dao_tiquete;
import java.util.List;
import logica.Compra;

/**
 *
 * @author Moi
 */
public class Controller {

    public String enviarListaCompraUsuario(String id) {
        Gson gson = new Gson();
        try {
            List<Compra> lista = Dao_compra.obtenerCompras_usuario(id);
            for (Compra c : lista) {
                c.setTiquetes(Dao_tiquete.obtenerListaTiquete_compra(c.getId()));
            }
            String json = "{\"codigo\": 1, \"lista\": " + gson.toJson(lista) + "}";
            return json;
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }

}
