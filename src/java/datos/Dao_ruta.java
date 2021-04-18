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
import logica.Ruta;

/**
 *
 * @author Moi
 */
public class Dao_ruta extends Dao {

    public static void insertarRuta(Ruta r) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(INSERTAR, r.getDuracion(), r.getOrigen().getCodigo(), r.getDestino().getCodigo());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }

    public static List<Ruta> obtenerListaRuta() throws Exception {
        Connection connection = Conn.conectar();
        List<Ruta> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            result.add(rs_ruta(rs));
        }
        return result;
    }

    public static Ruta obtenerRuta_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        Ruta result = null;
        String call = String.format(OBTENER, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if (rs.next()) {
            result = rs_ruta(rs);
        }
        return result;
    }

    public static void eliminarRuta_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }

    public static void editarRuta(Ruta r) throws Exception {
        Connection connection = Conn.conectar();
        String call = EDITAR;
        call = String.format(call, r.getId(), r.getDuracion(), r.getOrigen().getCodigo(), r.getDestino().getCodigo());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }

    private static Ruta rs_ruta(ResultSet rs) {
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

    private static final String INSERTAR = "{call insertar_ruta('%s','%s','%s')}";
    private static final String LISTA = "{call mostrar_rutas()}";
    private static final String OBTENER = "{call obtener_ruta(%s)}";
    private static final String ELIMINAR = "{call eliminar_ruta(%s)}";
    private static final String EDITAR = "{call editar_ruta(%s,'%s','%s','%s')}";
}
