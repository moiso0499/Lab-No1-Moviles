/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import logica.Ruta;

/**
 *
 * @author Moi
 */
public class Dao_ruta extends Dao {
    
    public static void insertarRuta(Ruta r) throws Exception {
        
        cs = con.prepareCall(INSERTAR);
        cs.setTime(1, Time.valueOf(r.getDuracion())); //Parsea de java a sql
        cs.setString(2,r.getOrigen().getCodigo());
        cs.setString(2, r.getDestino().getCodigo());
        cs.executeUpdate();
    }
    
    public static List<Ruta> obtenerListaRuta() throws Exception {
        List<Ruta> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_ruta(rs));
        }
        return result;
    }
    
    public static Ruta obtenerRuta_id(int id) throws Exception {
        Ruta result = null;
        cs = con.prepareCall(OBTENER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_ruta(rs);
        }
        return result;
    }
    
    public static void eliminarRuta_id(int id) throws Exception {
        cs = con.prepareCall(ELIMINAR);
        cs.setInt(1,id);
        cs.executeUpdate();
    }
        
    private static Ruta rs_ruta(ResultSet rs){
        try {
            Ruta r = new Ruta();
            r.setId(rs.getInt(1));
            r.setDuracion(rs.getTime(2).toLocalTime());
            //--
            String cod_origen = rs.getString(3);
            r.setOrigen(Dao_ubicacion.obtenerUbicacion_codigo(cod_origen));
            String cod_destino = rs.getString(4);
            r.setDestino(Dao_ubicacion.obtenerUbicacion_codigo(cod_destino));
            return r;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_ruta(?,?,?)}";
    private static final String LISTA = "{call mostrar_rutas()}";
    private static final String OBTENER = "{call obtener_ruta(?)}";
    private static final String ELIMINAR = "{call eliminar_ruta(?)}";
}
