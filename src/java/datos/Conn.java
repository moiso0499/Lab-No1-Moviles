/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Moi
 */
public class Conn {
    
    public static Connection conectar() throws Exception {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String server = "localhost";
            String port = "3306";
            String user = "root";
            String password = "root";
            String database = "aerolinea";
            String URL_conexion="jdbc:mysql://"+ server+":"+port+"/"+database+"?useTimezone=true&serverTimezone=UTC&user="+user+"&password="+password+"&useSSL=false";
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
