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
import logica.Tiquete;
/**
 *
 * @author Moi
 */
public class Dao_tiquete extends Dao {
    
    public static void insertarTiquete(Tiquete t) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(INSERTAR, t.getCompra_id().getId(),t.getAsiento(),t.getVuelo_id().getId());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static List<Tiquete> obtenerListaTiquete_compra(int id) throws Exception {
        Connection connection = Conn.conectar();
        List<Tiquete> result = new ArrayList<>();
        String call = String.format(TIQUETES_COMPRA, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tiquete(rs));
        }
        return result;
    }
    
     public static List<Tiquete> obtenerListaTiquete_vuelo(int id) throws Exception {
        Connection connection = Conn.conectar();
         List<Tiquete> result = new ArrayList<>();
        String call = String.format(TIQUETES_VUELO, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tiquete(rs));
        }
        return result;
    }
     
     public static void eliminarTiquetes_compra(int id) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    private static Tiquete rs_tiquete(ResultSet rs) {
        try {
            Tiquete t = new Tiquete();
            // --
            int id_compra = rs.getInt(1);
            t.setCompra_id(Dao_compra.obtenerCompra_id(id_compra));
            // --
            t.setAsiento(rs.getInt(2));
            // --
            int id_vuelo = rs.getInt(3);
            t.setVuelo_id(Dao_vuelo.obtenerVuelo_id(id_vuelo));
            return t;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_tiquete(%s,%s,%s)}";
    private static final String TIQUETES_COMPRA = "{call tiquetes_compra(%s)}";
    private static final String TIQUETES_VUELO = "{call tiquetes_vuelo(%s)}";
    private static final String ELIMINAR = "{call eliminar_tiquetes_compra(%s)}";
}
