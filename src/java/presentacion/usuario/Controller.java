/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.usuario;

import com.google.gson.Gson;
import datos.Dao_usuario;
import logica.Usuario;

/**
 *
 * @author Moi
 */
public class Controller {

    public String ingresarUsuario(String data) {
        Gson gson = new Gson();
        try {
            Usuario usuario = gson.fromJson(data, Usuario.class);
            Dao_usuario.insertarUsuario(usuario);
            Usuario registrado = Dao_usuario.obtenerUsuario_id(usuario.getId());
            return "{\"codigo\": 1, \"usuario\": " + gson.toJson(registrado) + "}";
        } catch (Exception e) {
            return "{\"codigo\": 2}";
        }
    }
}
