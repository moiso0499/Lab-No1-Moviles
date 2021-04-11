/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.login;

import com.google.gson.Gson;
import logica.Usuario;

/**
 *
 * @author Moi
 */
public class Controller {
    
    public String existeUsuario(String json){
        Gson gson = new Gson();
        try {
            Usuario usuario = gson.fromJson(json, Usuario.class);
            Usuario usuario_db = datos.Dao_usuario.obtenerUsuario_id(usuario.getId());
            if (usuario.getId().equals(usuario_db.getId()) && usuario.getClave().equals(usuario_db.getClave())){
                return gson.toJson(true);
            }
            else{
                return gson.toJson(false);
            }
        } catch (Exception e) {
            return gson.toJson(false);
        }
    }
}
