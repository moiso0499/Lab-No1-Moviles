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
import logica.Avion;

/**
 *
 * @author Moi
 */
public class Dao_avion extends Dao {
    
    public static void insertarAvion(Avion a) throws Exception {
        Connection connection = Conn.conectar();
        String call = INSERTAR;
        call = String.format(call, a.getTipoAvion_id().getId(), a.getRuta_id().getId());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    } 
    
    
    public static List<Avion> obtenerListaAvion() throws Exception {
        Connection connection = Conn.conectar();
        List<Avion> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_avion(rs));
        }
        return result;
    }
    
   
    public static Avion obtenerAvion_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        Avion result = null;
        String call = OBTENER;
        call = String.format(call, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if(rs.next()){
            result = rs_avion(rs);
        }
        return result;
    }
    
    public static void eliminarAvion_id(int id) throws Exception{
        Connection connection = Conn.conectar();
        String call = ELIMINAR;
        call = String.format(call,id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }
    
    private static Avion rs_avion(ResultSet rs) {
        try{
            Avion a = new Avion();
            a.setId(rs.getInt(1));
            //--
            int id_tipoavion = rs.getInt(2);
            a.setTipoAvion_id(Dao_tipoavion.obtenerTipoavion_id(id_tipoavion));
            int id_ruta = rs.getInt(3);
            a.setRuta_id(Dao_ruta.obtenerRuta_id(id_ruta));
            //--
            return a;
        }
        catch(Exception ex){
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_avion(%s,%s)}";
    private static final String LISTA = "{call mostrar_aviones()}";
    private static final String OBTENER = "{call obtener_avion(%s)}";
    private static final String ELIMINAR = "{call eliminar_avion(%s)}";
}
