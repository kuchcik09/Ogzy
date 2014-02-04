/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.database.SqlConnection;
/**
 *
 * @author zzzmkp01
 */
public class Test {
    private Statement stat = null;
    public void test(){

         try {
           Connection conn = SqlConnection.getInstance().getSqlConnection();
           stat = conn.createStatement();
           stat.execute("CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255))");
            PreparedStatement prepStmt = conn.prepareStatement(
                    "insert into test values (NULL, ?);");
            prepStmt.setString(1, "text");
            prepStmt.execute();
            
            prepStmt = conn.prepareStatement(
                    "insert into test values (NULL, ?);");
            prepStmt.setString(1, "text2");
            prepStmt.execute();
            System.out.println("Poszlo to pozytywnie");
            
            
         } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }
   
    public String get_test() {
        try {
           Connection conn = SqlConnection.getInstance().getSqlConnection();
           stat = conn.createStatement();
           ResultSet result = stat.executeQuery("SELECT * FROM test WHERE id='1'");
           String imie = result.getString("imie");  
        return imie;
             } catch (SQLException e) {
             e.printStackTrace();
             return "dupa dupa dupa";
        }
    }
}
