/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
        Connection connection = Conn.conectar();
        String call = String.format(INSERTAR, v.getFecha(), v.getHora_salida(), v.getPrecio(), v.getAvion_id().getId());
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }

    public static List<Vuelo> obtenerListaVuelo() throws Exception {
        Connection connection = Conn.conectar();
        List<Vuelo> result = new ArrayList<>();
        CallableStatement cs = connection.prepareCall(LISTA);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            result.add(rs_vuelo(rs));
        }
        return result;
    }

    public static List<Vuelo> obtenerListaVueloFiltrada(LocalDate fecha, String origen, String destino) throws Exception {
        Connection connection = Conn.conectar();
        List<Vuelo> result = new ArrayList<>();
        String call = FILTRO;
        call = String.format(call, fecha, origen, destino);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            result.add(rs_vuelo(rs));
        }
        return result;
    }

    public static Vuelo obtenerVuelo_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        Vuelo result = null;
        String call = String.format(OBTENER, id);
        CallableStatement cs = connection.prepareCall(call);
        ResultSet rs = cs.executeQuery();
        if (rs.next()) {
            result = rs_vuelo(rs);
        }
        return result;
    }

    public static void eliminarVuelo_id(int id) throws Exception {
        Connection connection = Conn.conectar();
        String call = String.format(ELIMINAR, id);
        CallableStatement cs = connection.prepareCall(call);
        cs.executeUpdate();
    }

    private static Vuelo rs_vuelo(ResultSet rs) {
        try {
            Vuelo v = new Vuelo();
            v.setId(rs.getInt(1));
            v.setFecha(rs.getDate(2).toLocalDate());
            v.setHora_salida(rs.getTime(3).toLocalTime());
            v.setPrecio(rs.getDouble(4));
            //
            int id_avion = rs.getInt(5);
            v.setAvion_id(Dao_avion.obtenerAvion_id(id_avion));
            return v;
        } catch (Exception e) {
            return null;
        }
    }


    private static final String INSERTAR = "{call insertar_vuelo('%s','%s',%s,%s)}";
    private static final String LISTA = "{call mostrar_vuelos()}";
    private static final String OBTENER = "{call obtener_vuelo(%s)}";
    private static final String ELIMINAR = "{call eliminar_vuelo(%s)}";
    private static final String FILTRO = "{call mostrar_vuelos_filtro('%s','%s','%s')}";
}
