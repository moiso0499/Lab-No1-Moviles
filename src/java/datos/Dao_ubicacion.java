/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Ubicacion;

/**
 *
 * @author Moi
 */
public class Dao_ubicacion extends Dao {
    public static void insertarUbicacion(Ubicacion u) throws Exception {
        cs = con.prepareCall(INSERTAR);
        cs.setString(1,u.getCodigo());
        cs.setString(2,u.getDescripcion());
        cs.executeUpdate();
    }
    
    public static List<Ubicacion> obtenerListaUbicacion() throws Exception {
        List<Ubicacion> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_ubicacion(rs));
        }
        return result;
    }
    
    public static Ubicacion obtenerUbicacion_codigo(String codigo) throws Exception {
        Ubicacion result = null;
        cs = con.prepareCall(OBTENER);
        cs.setString(1,codigo);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_ubicacion(rs);
        }
        return result;
    }
    
    public static void eliminarUbicacion_codigo(String codigo) throws Exception {
        cs = con.prepareCall(ELIMINAR);
        cs.setString(1,codigo);
        cs.executeUpdate();
    }
    
    private static Ubicacion rs_ubicacion(ResultSet rs) {
        try{
            Ubicacion u = new Ubicacion();
            u.setCodigo(rs.getString(1));
            u.setDescripcion(rs.getString(2));
            return u;
        }
        catch(SQLException e){
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_ubicacion(?,?)}";
    private static final String LISTA = "{call mostrar_ubicaciones()}";
    private static final String OBTENER = "{call obtener_ubicacion(?)}";
    private static final String ELIMINAR = "{call eliminar_ubicacion(?)}";
}
