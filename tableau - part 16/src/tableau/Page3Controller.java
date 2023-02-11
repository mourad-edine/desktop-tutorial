/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class Page3Controller implements Initializable {
    ///
    
    
    @FXML
    private Label nombreclient;

    @FXML
    private Label nombreproduit;

    @FXML
    private Label nombreuser;
    
    
    //////
    @FXML
    private JFXButton ajout;
    
    @FXML
    private AnchorPane root;
    
      @FXML
    private JFXButton Maison;
    


    @FXML
    private JFXButton home;
    
    @FXML
    private Button retour;
    
    @FXML
    private JFXButton util;

    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
            // TODO
          con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Page3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        client();
        user();
        produit();
    } 
    public void retour() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("page2.fxml"));
        
        Stage window = (Stage)retour.getScene().getWindow();
        window.setScene(new Scene(root));
        System.out.println("vous êtes de retour!");
        
        
        
    }
    @FXML
    public void Archives(ActionEvent event) {
        
       try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Tableau.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
    @FXML
    public void utilisateur(ActionEvent event) {
        
       try {
            Parent fxml = FXMLLoader.load(getClass().getResource("utilisateur.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
    @FXML
    public void ajoutproduit(ActionEvent event) {
        
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }

    }
     @FXML
    public void statistique(ActionEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("stat.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }
    }
    @FXML
    public void maison(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("page3.fxml"));
        
        Stage window = (Stage)Maison.getScene().getWindow();
        window.setScene(new Scene(root));
        System.out.println("vous êtes de retour!");
    }
    
    ////////////////////////////////////////////////////////////
    
    
    public void produit(){
    
       String sqlu = "SELECT COUNT(*) FROM listproduit";
            int Total = 0;
            try{
               statement  = con.prepareStatement(sqlu);
               result = statement.executeQuery();
            if(result.next()){
                 Total = result.getInt(1);
            }   
            
            nombreproduit.setText(Integer.toString(Total));
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public void user(){
    
       String sqlu = "SELECT COUNT(*) FROM utilisateur";
            int Total = 0;
            try{
               statement  = con.prepareStatement(sqlu);
               result = statement.executeQuery();
            if(result.next()){
                 Total = result.getInt(1);
            }   
            
            nombreuser.setText(Integer.toString(Total));
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    public void client(){
    
       String sqlu = "SELECT COUNT(*) FROM archivefacture";
            int Total = 0;
            try{
               statement  = con.prepareStatement(sqlu);
               result = statement.executeQuery();
            if(result.next()){
                 Total = result.getInt(1);
            }   
            
            nombreclient.setText(Integer.toString(Total));
            }catch(Exception e){
                e.printStackTrace();
            }
    }
    
    
}
