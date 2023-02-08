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
    private TextField TVA;

    @FXML
    private Button enregistre;

    @FXML
    private TextField kapoaka;

    @FXML
    private TextField kilo;

    @FXML
    private TextField nom_produit;

    @FXML
    private Button retour;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField sac;

    @FXML
    private TextField stock;

    @FXML
    private TextField unitaire;
    
    @FXML
    public void enregistrer(ActionEvent event) {
        
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String nomproduit1,prixkp1,prixkg1,prixsac1,tva1,stock1;
           
            
            nomproduit1 = nom_produit.getText();
            prixkp1 = kapoaka.getText();
            prixkg1 = kilo.getText();
            prixsac1 = sac.getText();
            int punitaire = Integer.parseInt(unitaire.getText());
            tva1 = TVA.getText();
            stock1 = stock.getText();
            try{
                String sql = "insert into listproduit(nom_produit,prixkp,prixkg,prixsac,punitaire,tva,stock) value(?,?,?,?,?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setString(1,nomproduit1);
                statement.setString(2,prixkp1);
                statement.setString(3,prixkg1);
                statement.setString(4,prixsac1);
                statement.setInt(5,punitaire);
                statement.setString(6,tva1);
                statement.setString(7,stock1);
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
