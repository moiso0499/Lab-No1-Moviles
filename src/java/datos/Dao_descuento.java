/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

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
        cs = con.prepareCall(INSERTAR);
        cs.setInt(1, d.getVuelo_id().getId());
        cs.setDouble(2, d.getPorcentaje());
        cs.executeUpdate();
    }
    
    public static List<Descuento> obtenerListaDescuento() throws Exception {
        List<Descuento> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_descuento(rs));
        }
        return result;
    }
    
    public static List<Descuento> obtenerListaDescuento_vuelo(int id) throws Exception {
        List<Descuento> result = new ArrayList<>();
        cs = con.prepareCall(DESCUENTOS_VUELO);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_descuento(rs));
        }
        return result;
    }
    
    public static Descuento obtenerDescuento_id(int id) throws Exception {
        Descuento result = null;
        cs = con.prepareCall(OBTENER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_descuento(rs);
        }
        return result;
    }
    
    public static void eliminarDescuento_id(int id) throws Exception {
        cs = con.prepareCall(ELIMINAR);
        cs.setInt(id, id);
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
    
    
    private static final String INSERTAR = "{call insertar_descuento(?,?)}";
    private static final String LISTA = "{call mostrar_descuentos()}";
    private static final String DESCUENTOS_VUELO = "{call descuentos_vuelo(?)}";
    private static final String OBTENER = "{call obtener_descuento(?)}";
    private static final String ELIMINAR = "{call eliminar_descuento(?)}";
}
