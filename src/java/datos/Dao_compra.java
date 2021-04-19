/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Compra;

/**
 *
 * @author Moi
 */
public class Dao_compra extends Dao {
    
    public static void insertarCompra(Compra c) throws Exception {
        Connection connection = Conn.conectar();
        String call = INSERTAR;
        call = String.format(call, c.getUsuario_id().getId(),c.getVuelo_id().getId());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static List<Compra> obtenerListaCompra() throws Exception {
        Connection connection = Conn.conectar();
        List<Compra> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static List<Compra> obtenerCompras_vuelo(int id) throws Exception {
        Connection connection = Conn.conectar();
        List<Compra> result = new ArrayList<>();
        String call = COMPRAS_VUELO;
        call = String.format(call, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static List<Compra> obtenerCompras_usuario(String id) throws Exception {
        Connection connection = Conn.conectar();
        List<Compra> result = new ArrayList<>();
        String call = String.format(COMPRAS_USUARIO,id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static Compra obtenerCompra_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        Compra result = null;
        String call = String.format(OBTENER, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            result = rs_compra(rs);
        }
        return result;
    }
    
    public static void eliminarCompra_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static int cantidadActualCompras() throws Exception {
        Connection connection = Conn.conectar();
        CallableStatement cs = connection.prepareCall("{call cantidad_compras()}");
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }
    
    private static Compra rs_compra(ResultSet rs) {
        try {
            Compra c = new Compra();
            c.setId(rs.getInt(1));
            // ---
            String id_usuario = rs.getString(2);
            c.setUsuario_id(Dao_usuario.obtenerUsuario_id(id_usuario));
            int id_vuelo = rs.getInt(3);
            c.setVuelo_id(Dao_vuelo.obtenerVuelo_id(id_vuelo));
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_compra('%s',%s)}";
    private static final String LISTA = "{call mostrar_compras()}";
    private static final String COMPRAS_USUARIO = "{call compras_usuario('%s')}";
    private static final String COMPRAS_VUELO = "{call compras_vuelo(%s)}";
    private static final String OBTENER = "{call obtener_compra(%s)}";
    private static final String ELIMINAR = "{call eliminar_compra(%s)}";
}
