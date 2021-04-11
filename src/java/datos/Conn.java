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
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/aerolinea?user=root&password=root&useSSL=false");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
