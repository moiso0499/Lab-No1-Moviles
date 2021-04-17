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
import logica.Tipoavion;

/**
 *
 * @author Moi
 */
public class Dao_tipoavion extends Dao{

    
    public static void insertarTipoavion(Tipoavion t) throws Exception { //NP
        Connection connection = Conn.conectar();
        String call = String.format(INSERTAR, t.getAnyo(),t.getModelo(),t.getFilas(),t.getAsientos_fila());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    public static List<Tipoavion> obtenerListaTipoavion() throws Exception { //NP
        Connection connection = Conn.conectar();
        List<Tipoavion> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tipoavion(rs));
        }
        return result;
    }
    
    public static Tipoavion obtenerTipoavion_id(int id) throws Exception { //NP
        Connection connection = Conn.conectar();
        Tipoavion result = null;
        String call = String.format(OBTENER, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            result = rs_tipoavion(rs);
        }
        return result;
    }
    
    public static void eliminarTipoavion_id(int id) throws Exception { //NP
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    private static Tipoavion rs_tipoavion(ResultSet rs){ //NP
        try{
            Tipoavion t = new Tipoavion();
            t.setId(rs.getInt(1));
            t.setAnyo(rs.getInt(2));
            t.setModelo(rs.getString(3));
            t.setFilas(rs.getInt(4));
            t.setAsientos_fila(rs.getInt(5));
            return t;
        }
        catch(SQLException e){
            return null;
        }
    }
    
        private static final String INSERTAR = "{call insertar_tipoavion(%s,'%s',%s,%s)}";
        private static final String LISTA = "{call mostrar_tipoaviones()}";
        private static final String OBTENER = "{call obtener_tipoavion(%s)}";
        private static final String ELIMINAR = "{call eliminar_tipoavion(%s)}";
}
