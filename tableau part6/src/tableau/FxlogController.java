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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;


/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class FxlogController implements Initializable {
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    
    
     @FXML
    private ComboBox<String> genre;
    
    @FXML
    private Button btn12;
    
    @FXML
    private ComboBox<String> name;
    
    
    @FXML
    private ComboBox<String> qualit;
    
    
    ObservableList<String> list = FXCollections.observableArrayList("kilo","kapoaka","En sac");

    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        list();
         try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         load();
         
    
    }

    @FXML
    public void load() {
        ObservableList<String> james= FXCollections.observableArrayList("monsieur","madame");
        genre.setItems(james);
        
        ObservableList<String> data= FXCollections.observableArrayList();
        try {
            statement = con.prepareStatement("SELECT * FROM listproduit");
            result = statement.executeQuery();
            
            while(result.next()){
                
                data.add(
                       result.getString("nom_produit")    
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        name.setItems(null);
        name.setItems(data);

    }
    public void list(){
       qualit.setItems(list);
       
    }
    
}
