/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class StatController implements Initializable {
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    @FXML
    private BarChart<?, ?> charcode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        chart();
    }  
    
    public void chart(){
        String chartsql = "SELECT date,SUM(ptotal) FROM archivefacture GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 8";
        try {
            // TODO
          con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            XYChart.Series chartdata = new XYChart.Series();
            statement = con.prepareStatement(chartsql);
            result = statement.executeQuery();
            while(result.next()){
                
                chartdata.getData().add(new XYChart.Data(
                       result.getString(1), 
                       result.getInt(2))   
                );
            }
            
            charcode.getData().add(chartdata);
        
        }catch(Exception e){
             e.printStackTrace();
        
        }
    }

    
    
}
