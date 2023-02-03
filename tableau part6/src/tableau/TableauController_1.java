/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

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
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class TableauController_1 implements Initializable {
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    private ObservableList<listproduit> data;
    
    
    
    @FXML
    private TableView<listproduit> tableaux;
    @FXML
    private TableColumn<?, ?> colonneAchat;

    @FXML
    private TableColumn<?, ?> colonneClient;

    @FXML
    private TableColumn<?, ?> colonneID;

    @FXML
    private TableColumn<?, ?> colonneNom;

    @FXML
    private TableColumn<?, ?> colonnePt;

    @FXML
    private TableColumn<?, ?> colonnePut;

    @FXML
    private TableColumn<?, ?> colonneciv;

    @FXML
    private TableColumn<?, ?> colonnequant;


    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        data = FXCollections.observableArrayList();
        setcell();
        load();
        
    }
    private void setcell() {
         colonneID.setCellValueFactory(new PropertyValueFactory<>("id"));
         colonneAchat.setCellValueFactory(new PropertyValueFactory<>("typeachat"));
         colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         colonneClient.setCellValueFactory(new PropertyValueFactory<>("client"));
         colonnequant.setCellValueFactory(new PropertyValueFactory<>("quantite"));
         colonneciv.setCellValueFactory(new PropertyValueFactory<>("civilite"));
         colonnePut.setCellValueFactory(new PropertyValueFactory<>("punitaire"));
         colonnePt.setCellValueFactory(new PropertyValueFactory<>("ptotal"));
    } 
    
    private void load(){
    
        try {
            statement = con.prepareStatement("SELECT * FROM archivefacture");
            result = statement.executeQuery();
            
            while(result.next()){
                data.add(new listproduit(
                result.getInt("id"),
                result.getString("Type_achat"),
                 result.getString("nom_produit"),
                 result.getString("nom_client"),
                result.getInt("quantite"),
                result.getString("civilite"),
                result.getInt("punitaire"),
                result.getInt("ptotal")
      
                ));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableaux.setItems(data);
        }
    
    
}
