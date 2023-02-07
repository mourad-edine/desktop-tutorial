/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class UtilisateurController implements Initializable {
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    private ObservableList<tableau> data;
    
    
    
    @FXML
    private JFXButton ajouter;

    @FXML
    private TableColumn<?, ?> colonne1;

    @FXML
    private TableColumn<?, ?> colonne2;

    @FXML
    private TableColumn<?, ?> colonne3;

    @FXML
    private TableColumn<?, ?> colonne4;

    @FXML
    private TableColumn<?, ?> colonne5;

    @FXML
    private TableColumn<?, ?> colonne6;
    @FXML
    private TableColumn<?, ?> colonne7;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<tableau> tablehierarche;

    
    @FXML
    public void pageAjouter(ActionEvent event) {
        
       try {
            Parent fxml = FXMLLoader.load(getClass().getResource("register.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data = FXCollections.observableArrayList();
        setcell();
        load();
        
    }
    private void setcell() {
         colonne1.setCellValueFactory(new PropertyValueFactory<>("id"));
         colonne2.setCellValueFactory(new PropertyValueFactory<>("nom"));
         colonne3.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         colonne4.setCellValueFactory(new PropertyValueFactory<>("adresse"));
         colonne5.setCellValueFactory(new PropertyValueFactory<>("email"));
         colonne6.setCellValueFactory(new PropertyValueFactory<>("passe"));
         colonne7.setCellValueFactory(new PropertyValueFactory<>("poste"));
         
    } 
    
    private void load(){
    
        try {
            statement = con.prepareStatement("SELECT * FROM utilisateur");
            result = statement.executeQuery();
            
            while(result.next()){
                data.add(new tableau(
                result.getInt("id"),
                result.getString("nom"),
                result.getString("prenom"),
                result.getString("adresse"),
                result.getString("email"),
                result.getString("passe"),
                result.getString("poste") 
                ));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablehierarche.setItems(data);
    }
    
    
    
}
