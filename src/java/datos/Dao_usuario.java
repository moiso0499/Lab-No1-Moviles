/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Usuario;

/**
 *
 * @author Moi
 */
public class Dao_usuario extends Dao {
    
    public static void insertarUsuario(Usuario u) throws Exception {
        if(u.isAdmin()){
            insertarAdministrador(u);
        }
        else{
            insertarUsuarioNormal(u);
        }
    }
    
    private static void insertarUsuarioNormal(Usuario u) throws Exception {
        cs = con.prepareCall(INSERTAR);
        cs.setString(1, u.getId());
        cs.setString(2, u.getNombre());
        cs.setString(3, u.getApellido());
        cs.setString(4, u.getClave());
        cs.setString(5, u.getCorreo());
        cs.setString(6, u.getDireccion());
        cs.executeUpdate();
    }
    
    private static void insertarAdministrador(Usuario u) throws Exception {
        cs = con.prepareCall(INSERTAR_ADMIN);
        cs.setString(1, u.getId());
        cs.setString(2, u.getNombre());
        cs.setString(3, u.getApellido());
        cs.setString(4, u.getClave());
        cs.executeUpdate();
    }
    
    public static List<Usuario> obtenerListaUsuario() throws Exception {
        List<Usuario> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_usuario(rs));
        }
        return result;
    }
    
    public static Usuario obtenerUsuario_id(String id) throws Exception {
        Usuario result = null;
        cs = con.prepareCall("{call obtener_usuario('?')}");
        cs.setString(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_usuario(rs);
        }
        return result;
    }
    
    public static void eliminarUsuario_id(String id) throws Exception {
        cs = con.prepareCall(ELIMINAR);
        cs.setString(1, id);
        cs.executeUpdate();
    }
    
    private static Usuario rs_usuario(ResultSet rs) {
        try {
            Usuario u = new Usuario();
            u.setId(rs.getString(1));
            u.setNombre(rs.getString(2));
            u.setClave(rs.getString(3));
            u.setCorreo(rs.getString(4));
            u.setDireccion(rs.getString(5));
            u.setAdmin(rs.getBoolean(6));
            u.setApellido(rs.getString(7));
            return u;
        } catch (SQLException e) {
            return null;
        }
    }
    
    
    private static final String INSERTAR = "{call insertar_usuario(?,?,?,?,?,?)}";
    private static final String INSERTAR_ADMIN = "{call insertar_administrador(?,?,?,?)}";
    private static final String LISTA = "{call mostrar_usuarios()}";
    private static final String OBTENER = "{call obtener_usuario(?)}";
    private static final String ELIMINAR = "{call eliminar_usuario(?)}";
}
