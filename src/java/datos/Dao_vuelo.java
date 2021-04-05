/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Vuelo;

/**
 *
 * @author Moi
 */
public class Dao_vuelo extends Dao {
    
    public static void insertarVuelo(Vuelo v) throws Exception {
        cs = con.prepareCall(INSERTAR);
        cs.setDate(1, Date.valueOf(v.getFecha()));
        cs.setTime(2, Time.valueOf(v.getHora_salida()));
        cs.setDouble(3, v.getPrecio());
        cs.setInt(4, v.getAvion_id().getId());
        cs.executeUpdate();
    }
    
    public static List<Vuelo> obtenerListaVuelo() throws Exception {
        List<Vuelo> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_vuelo(rs));
        }
        return result;
    }
    
    public static Vuelo obtenerVuelo_id(int id) throws Exception {
        Vuelo result = null;
        cs = con.prepareCall(OBTENER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_vuelo(rs);
        }
        return result;
    }
    
    public static void eliminarVuelo_id(int id) throws Exception {
        cs = con.prepareCall(ELIMINAR);
        cs.setInt(1,id);
        cs.executeUpdate();
    }
    
    private static Vuelo rs_vuelo(ResultSet rs) {
        try{
            Vuelo v = new Vuelo();
            v.setId(rs.getInt(1));
            v.setFecha(rs.getDate(2).toLocalDate());
            v.setHora_salida(rs.getTime(3).toLocalTime());
            v.setPrecio(rs.getDouble(4));
            //
            int id_avion = rs.getInt(5);
            v.setAvion_id(Dao_avion.obtenerAvion_id(id_avion));
            return v;
        }
        catch(Exception e){
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_vuelo(?,?,?,?)}";
    private static final String LISTA = "{call mostrar_vuelos()}";
    private static final String OBTENER = "{call obtener_vuelo(?)}";
    private static final String ELIMINAR = "{call eliminar_vuelo(?)}";
}
