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
import logica.Descuento;

/**
 *
 * @author Moi
 */
public class Dao_descuento extends Dao {
    
    public static void insertarDescuento(Descuento d) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(INSERTAR, d.getVuelo_id().getId(),d.getPorcentaje());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static List<Descuento> obtenerListaDescuento() throws Exception {
        Connection connection = Conn.conectar();
        List<Descuento> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_descuento(rs));
        }
        return result;
    }
    
    public static List<Descuento> obtenerListaDescuento_vuelo(int id) throws Exception {
        Connection connection = Conn.conectar();
        List<Descuento> result = new ArrayList<>();
        String call = String.format(DESCUENTOS_VUELO, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_descuento(rs));
        }
        return result;
    }
    
    public static Descuento obtenerDescuento_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        Descuento result = null;
        String call = String.format(OBTENER, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            result = rs_descuento(rs);
        }
        return result;
    }
    
    public static void eliminarDescuento_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    private static Descuento rs_descuento(ResultSet rs){
        try {
            Descuento d = new Descuento();
            d.setId(rs.getInt(1));
            //
            int id_vuelo = rs.getInt(2);
            d.setVuelo_id(Dao_vuelo.obtenerVuelo_id(id_vuelo));
            //
            d.setPorcentaje(rs.getDouble(3));
            return d;
        } catch (Exception e) {
            return null;
        }
    }
    
    
    private static final String INSERTAR = "{call insertar_descuento(%s,%s)}";
    private static final String LISTA = "{call mostrar_descuentos()}";
    private static final String DESCUENTOS_VUELO = "{call descuentos_vuelo(%S)}";
    private static final String OBTENER = "{call obtener_descuento(%s)}";
    private static final String ELIMINAR = "{call eliminar_descuento(%s)}";
}
