/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.login;

import com.google.gson.Gson;
import datos.Dao_usuario;
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
            Usuario usuario_db = Dao_usuario.obtenerUsuario_id(usuario.getId());
            if (usuario_db != null && usuario.getId().equals(usuario_db.getId()) && usuario.getClave().equals(usuario_db.getClave())){
                return gson.toJson(usuario_db);
            }
            else{
                return gson.toJson(null);
            }
        } catch (Exception e) {
            return gson.toJson(null);
        }
    }
}
