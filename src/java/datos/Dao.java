/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Moi
 */
public class Dao {
    static protected CallableStatement cs;
    static protected Statement st;
    static protected Connection con;
    static protected ResultSet rs;

    public Dao() {
        con = Conn.getCon();
    }

}
