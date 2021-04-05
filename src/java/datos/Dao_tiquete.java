/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

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
        cs = con.prepareCall(INSERTAR);
        cs.setInt(1, t.getCompra_id().getId());
        cs.setInt(2,t.getAsiento());
        cs.setInt(3, t.getVuelo_id().getId());
        cs.executeUpdate();
    }
    
    public static List<Tiquete> obtenerListaTiquete_compra(int id) throws Exception {
        List<Tiquete> result = new ArrayList<>();
        cs = con.prepareCall(TIQUETES_COMPRA);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tiquete(rs));
        }
        return result;
    }
    
     public static List<Tiquete> obtenerListaTiquete_vuelo(int id) throws Exception {
        List<Tiquete> result = new ArrayList<>();
        cs = con.prepareCall(TIQUETES_VUELO);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tiquete(rs));
        }
        return result;
    }
     
     public static void eliminarTiquetes_compra(int id) throws Exception {
         cs = con.prepareCall(ELIMINAR);
         cs.setInt(1,id);
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
    
    private static final String INSERTAR = "{call insertar_tiquete(?,?,?)}";
    private static final String TIQUETES_COMPRA = "{call tiquetes_compra(?)}";
    private static final String TIQUETES_VUELO = "{call tiquetes_vuelo(?)}";
    private static final String ELIMINAR = "{call eliminar_tiquetes_compra(?)}";
}
