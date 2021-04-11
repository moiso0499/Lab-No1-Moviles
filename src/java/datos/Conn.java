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
    
    private static Connection con = null;

    private static void conectar() {
        try {
            // Se debe cambiar a futuro para ocultar las credenciales
            con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/aerolinea?useTimezone=true&serverTimezone=UTC&user=root&password=root&useSSL=false");
        } catch (SQLException e) {
        }
    }
    
    public static Connection getCon(){
        if(con == null){
            conectar();
        }
        return con;
    }
}
