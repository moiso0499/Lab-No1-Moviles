/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

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
        
        cs = con.prepareCall("{call insertar_avion(?,?)}");
        cs.setInt(1, a.getTipoAvion_id().getId());
        cs.setInt(2, a.getRuta_id().getId());
        cs.executeUpdate();
    } 
    
    public static List<Avion> obtenerListaAvion() throws Exception {
        List<Avion> result = new ArrayList<>();
        cs = con.prepareCall("{call mostrar_aviones()}");
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_avion(rs));
        }
        return result;
    }
    
    public static Avion obtenerAvion_id(int id) throws Exception {
        Avion result = null;
        cs = con.prepareCall("{call obtener_avion(?)}");
        cs.setInt(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_avion(rs);
        }
        return result;
    }
    
    public static void eliminarAvion_id(int id) throws Exception{
        cs = con.prepareCall("{call eliminar_avion(?)}");
        cs.setInt(1, id);
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
}
