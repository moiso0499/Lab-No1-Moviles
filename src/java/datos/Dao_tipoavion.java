/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import logica.Tipoavion;

/**
 *
 * @author Moi
 */
public class Dao_tipoavion extends Dao{

    public Dao_tipoavion() {
    }
    
    
    
    public void insertarTipoavion(Tipoavion t) throws Exception { //Sin probar aún
        
        cs = con.prepareCall("{call insertar_tipoavion(?,?,?,?)}");
        cs.setInt(1, t.getAnyo());
        cs.setString(2,t.getModelo());
        cs.setInt(3, t.getFilas());
        cs.setInt(4, t.getAsientos_fila());
        cs.executeUpdate();
        
    }
    
    public List<Tipoavion> obtenerListaTipoavion() throws Exception {
        List<Tipoavion> result = new ArrayList<>();
        cs = con.prepareCall("{call mostrar_tipoaviones()}");
        rs = cs.executeQuery();
        while(rs.next()){
            result.add(rs_tipoavion(rs));
        }
        return result;
    }
    
    
    private Tipoavion rs_tipoavion(ResultSet rs){
        try{
            Tipoavion t = new Tipoavion();
            t.setId(rs.getInt(1));
            t.setAnyo(rs.getInt(2));
            t.setModelo(rs.getString(3));
            t.setFilas(rs.getInt(4));
            t.setAsientos_fila(5);
            return t;
        }
        catch(Exception e){
            return null;
        }
    }
}
