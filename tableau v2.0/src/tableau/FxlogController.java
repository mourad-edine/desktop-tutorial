/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class FxlogController  {
    
    private Connection con;
    private ResultSet result;
    private PreparedStatement statement;
    @FXML
    private Button bouton1;

    @FXML
    private Button close;

    @FXML
    private DatePicker datex;

    @FXML
    private TextField email;

    @FXML
    private ImageView imageview;

    @FXML
    private Hyperlink lien;

    @FXML
    private PasswordField motpasse;

    @FXML
    private TextField nom;
    
    
    
    
    
    

    public void hendlelien() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("page1.fxml"));
        
        Stage window = (Stage)lien.getScene().getWindow();
        window.setScene(new Scene(root));
        System.out.println("vous avez bien changé de page !");
    }

    @FXML
    void handleimage(MouseEvent event) {

    }

    @FXML
    void handlemot(ActionEvent event) {

    }

    @FXML
    void handlenom(ActionEvent event) {

    }
    

    public void inserer(ActionEvent event){
      
            try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(TableauController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String nom1,adresse1,passe1;
            
            nom1 = nom.getText();
            adresse1 = email.getText();
            passe1 = motpasse.getText();
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

   /* public void handlebouton1(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("page2.fxml"));
         Scene scene = new Scene(root);
         Stage stage = new Stage();
         Stage.setScene(scene);
         Stage.show();
         Stage.setTitle("le vent du sud");
       
    }*/
    public void exit(ActionEvent event){
        System.exit(0);
    
    }
}
