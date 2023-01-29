package tableau;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class AdminController {
    
    
    @FXML
    private Button btn1;
    
    @FXML
    private TextField nom;

    @FXML
    private PasswordField passe;
    
    
    //base de donnée
    
    
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    
    

    /*public void handlelien2() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        
        Stage window = (Stage)lien2.getScene().getWindow();
        window.setScene(new Scene(root));
        System.out.println("vous avez bien changé de page !");
    }*/
    
    public void login(ActionEvent event){
        try {
            try {
            // TODO
            Class.forName("com.mysql.jdbc.Driver");
            connect = dba.DBConnection.pmartConnection();

            } catch (SQLException ex) {
                Logger.getLogger(TableauController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql = "SELECT * FROM admin WHERE nom = ? and passe = ?";
            statement  = connect.prepareStatement(sql);
            statement.setString(1,nom.getText());
            statement.setString(2,passe.getText());
            result = statement.executeQuery();
            
            if(result.next()){
                btn1.getScene().getWindow().hide();
                 Parent root = FXMLLoader.load(getClass().getResource("page3.fxml"));
                 Scene scene = new Scene(root);
                 Stage stage = new Stage();
                 stage.setScene(scene);
                 stage.show();
                 stage.setTitle("le vent du sud");
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("mon alerte");
                 alert.setContentText("bienvenue mr"+" "+nom.getText());
                 alert.showAndWait();
                
       
            }
            else{
                 Alert alert = new Alert(AlertType.INFORMATION);
                 alert.setTitle("mon alerte");
                 alert.setContentText("verifier votre mot de passe ou votre nom !");
                 alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("mon alerte");
            alert.setContentText("problème sql");
            alert.showAndWait();
            
            
        }
          
    }

}
