/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
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

    public Dao_usuario() {
    }
    
    
    
    public static void insertarUsuario(Usuario u) throws Exception {
        if(u.isAdmin()){
            insertarAdministrador(u);
        }
        else{
            insertarUsuarioNormal(u);
        }
    }
    
    private static void insertarUsuarioNormal(Usuario u) throws Exception {
        Connection connection = Conn.conectar();
        String call = INSERTAR;
        call = String.format(call, u.getId(),u.getNombre(),u.getApellido(),u.getClave(),u.getCorreo(),u.getDireccion());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    private static void insertarAdministrador(Usuario u) throws Exception {
        Connection connection = Conn.conectar();
        String call = INSERTAR_ADMIN;
        call = String.format(call, u.getId(),u.getNombre(),u.getApellido(),u.getClave());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static List<Usuario> obtenerListaUsuario() throws Exception {
        Connection connection = Conn.conectar();
        List<Usuario> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_usuario(rs));
        }
        return result;
    }
    
    public static Usuario obtenerUsuario_id(String id) throws Exception {
        Connection connection = Conn.conectar();
        Usuario result = null;
        String call = OBTENER;
        call = String.format(call,id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            result = rs_usuario(rs);
        }
        return result;
    }
    
    public static void eliminarUsuario_id(String id) throws Exception {
        Connection connection = Conn.conectar();
        String call = ELIMINAR;
        call = String.format(call, id);
        CallableStatement cs = connection.prepareCall(call);
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
    
    
    private static final String INSERTAR = "{call insertar_usuario('%s','%s','%s','%s','%s','%s')}";
    private static final String INSERTAR_ADMIN = "{call insertar_administrador('%s','%s','%s','%s')}";
    private static final String LISTA = "{call mostrar_usuarios()}";
    private static final String OBTENER = "{call obtener_usuario('%s')}";
    private static final String ELIMINAR = "{call eliminar_usuario('%s')}";
}
