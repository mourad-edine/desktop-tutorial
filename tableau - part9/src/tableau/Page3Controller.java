/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class Page3Controller implements Initializable {
    @FXML
    private JFXButton ajout;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    private Button btn2;
    


    @FXML
    private JFXButton home;
    
    @FXML
    private Button retour;
    
    @FXML
    private JFXButton util;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    
}
