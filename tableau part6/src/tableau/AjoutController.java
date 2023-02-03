/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class AjoutController implements Initializable {
    
    private PreparedStatement statement;
    private Connection con;
    
     @FXML
    private PasswordField passe;
    
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private Button enregistre;
    @FXML
    private Button retour;
    
    @FXML
    private AnchorPane root;
    
    @FXML
    public void enregistrer(ActionEvent event) {
        
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String nom1,adresse1,passe1;
            
            nom1 = nom.getText();
            adresse1 = email.getText();
            passe1 = passe.getText();
            try{
                String sql = "insert into utilisateur(nom,email,passe) value(?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setString(1,nom1);
                statement.setString(2,adresse1);
                statement.setString(3,passe1);
                int result = statement.executeUpdate();
                
                
                if(result==1){
                    
                    JOptionPane.showMessageDialog(null,"enregistré avec succes");
                }else{
                    JOptionPane.showMessageDialog(null,"echec !");
                }
            
            }catch(SQLException e){
                e.printStackTrace();
            
            
            }

    }
    
    @FXML
    public void retourPage(ActionEvent event) {
        
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }

    }

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
