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
public class RegisterController_1 implements Initializable {
    
    private PreparedStatement statement;
    private Connection con;
    
      @FXML
    private TextField adresse;

    @FXML
    private TextField email;

    @FXML
    private Button enregistre;

    @FXML
    private TextField nom;

    @FXML
    private PasswordField passe;

    @FXML
    private TextField poste;

    @FXML
    private TextField prenom;
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
               Logger.getLogger(RegisterController_1.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String nom1,prenom1,adresse1,email1,passe1,poste1;
            
            nom1 = nom.getText();
            prenom1 = prenom.getText();
            adresse1 = adresse.getText();
            email1 = email.getText();
            passe1= passe.getText();
            poste1 = poste.getText();
            
            try{
                String sql = "insert into utilisateur(nom,prenom,adresse,email,passe,poste) value(?,?,?,?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setString(1,nom1);
                statement.setString(2,prenom1);
                statement.setString(3,adresse1);
                statement.setString(4,email1);
                statement.setString(5,passe1);
                statement.setString(6,poste1);
                
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
            Parent fxml = FXMLLoader.load(getClass().getResource("utilisateur.fxml"));
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
