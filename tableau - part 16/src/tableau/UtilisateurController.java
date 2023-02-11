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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
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
    private TableColumn<tableau,Integer> colonne1;

    @FXML
    private TableColumn<tableau,String> colonne2;

    @FXML
    private TableColumn<tableau,String> colonne3;

    @FXML
    private TableColumn<tableau,String> colonne4;

    @FXML
    private TableColumn<tableau,String> colonne5;

    @FXML
    private TableColumn<tableau,String> colonne6;
    @FXML
    private TableColumn<tableau,String> colonne7;

    @FXML
    private AnchorPane root;
    
    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;
    
    int index = -1;

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
         colonne1.setCellValueFactory(new PropertyValueFactory<tableau,Integer>("id"));
         colonne2.setCellValueFactory(new PropertyValueFactory<tableau,String>("nom"));
         colonne3.setCellValueFactory(new PropertyValueFactory<tableau,String>("prenom"));
         colonne4.setCellValueFactory(new PropertyValueFactory<tableau,String>("adresse"));
         colonne5.setCellValueFactory(new PropertyValueFactory<tableau,String>("email"));
         colonne6.setCellValueFactory(new PropertyValueFactory<tableau,String>("passe"));
         colonne7.setCellValueFactory(new PropertyValueFactory<tableau,String>("poste"));
         
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
    @FXML
    public void suprimere(MouseEvent event) {
        index = tablehierarche.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txtid.setText(colonne1.getCellData(index).toString());
    txtname.setText(colonne2.getCellData(index).toString());

    }
    
    @FXML
    public void eliminer(ActionEvent event) throws SQLException {
        con = dba.DBConnection.pmartConnection();
        String sql = "delete from utilisateur where id = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, txtid.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "effacé avac succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    
    
}
