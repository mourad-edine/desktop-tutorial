package dba;


import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mourad";
            String user = "root";
            String password = "1234";
            
            con = DriverManager.getConnection(url,user,password);
        } catch (Exception ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
      
    }
    public static ObservableList<testy.facture> getDatausers() throws SQLException{
        Connection con = pmartConnection();
        ObservableList<testy.facture> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = con.prepareStatement("select * from temporary");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
               list.add(new testy.facture(rs.getInt("quantite"), rs.getString("designation"), rs.getInt("Pu"), rs.getInt("Pt")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    
}
