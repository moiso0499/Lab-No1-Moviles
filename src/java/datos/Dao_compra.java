/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Compra;

/**
 *
 * @author Moi
 */
public class Dao_compra extends Dao {
    
    public static void insertarCompra(Compra c) throws Exception {
        cs = con.prepareCall(INSERTAR);
        cs.setString(1, c.getUsuario_id().getId());
        cs.setInt(2, c.getVuelo_id().getId());
        cs.executeUpdate();
    }
    
    public static List<Compra> obtenerListaCompra() throws Exception {
        List<Compra> result = new ArrayList<>();
        cs = con.prepareCall(LISTA);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static List<Compra> obtenerCompras_vuelo(int id) throws Exception {
        List<Compra> result = new ArrayList<>();
        cs = con.prepareCall(COMPRAS_VUELO);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static List<Compra> obtenerCompras_usuario(String id) throws Exception {
        List<Compra> result = new ArrayList<>();
        cs = con.prepareCall(COMPRAS_USUARIO);
        cs.setString(1, id);
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_compra(rs));
        }
        return result;
    }
    
    public static Compra obtenerCompra_id(int id) throws Exception {
        Compra result = null;
        cs = con.prepareCall(OBTENER);
        cs.setInt(1, id);
        rs = cs.executeQuery();
        if(rs.next()){
            result = rs_compra(rs);
        }
        return result;
    }
    
    public static void eliminarCompra_id(int id) throws Exception {
        cs  = con.prepareCall(ELIMINAR);
        cs.setInt(1, id);
        cs.executeUpdate();
    }
    
    private static Compra rs_compra(ResultSet rs) {
        try {
            Compra c = new Compra();
            c.setId(rs.getInt(1));
            // ---
            String id_usuario = rs.getString(2);
            c.setUsuario_id(Dao_usuario.obtenerUsuario_id(id_usuario));
            int id_vuelo = rs.getInt(3);
            c.setVuelo_id(Dao_vuelo.obtenerVuelo_id(id_vuelo));
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    private static final String INSERTAR = "{call insertar_compra(?,?)}";
    private static final String LISTA = "{call mostrar_compras()}";
    private static final String COMPRAS_USUARIO = "{call compras_usuario(?)}";
    private static final String COMPRAS_VUELO = "{call compras_vuelo(?)}";
    private static final String OBTENER = "{call obtener_compra(?)}";
    private static final String ELIMINAR = "{call eliminar_compra(?)}";
}
