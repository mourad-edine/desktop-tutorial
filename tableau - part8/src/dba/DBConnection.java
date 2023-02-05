package dba;


import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Manouher
 */
public class DBConnection{
    
    public static Connection pmartConnection() throws SQLException{
      Connection con  = null;
      
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mourad?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "1234";
            
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
      
    }
    
}
