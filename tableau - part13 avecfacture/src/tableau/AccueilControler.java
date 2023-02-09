/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class AccueilControler implements Initializable {
    
    @FXML
    private TextField recherche;
    
    @FXML
    private TableColumn<?, ?> colonneID;

    @FXML
    private TableColumn<?, ?> colonnePrixKP;

    @FXML
    private TableColumn<?, ?> colonnePrixkilo;

    @FXML
    private TableColumn<?, ?> colonnePrixsac;

    @FXML
    private TableColumn<?, ?> colonneProduit;

    @FXML
    private TableColumn<?, ?> colonneTva;
    @FXML
    private TableColumn<?, ?> colonnepunit;

    @FXML
    private TableView<liste> tableview;
    
    private ObservableList<liste> data;
    
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
         colonneProduit.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
         colonnePrixKP.setCellValueFactory(new PropertyValueFactory<>("prixKp"));
         colonnePrixkilo.setCellValueFactory(new PropertyValueFactory<>("prixKilo"));
         colonnePrixsac.setCellValueFactory(new PropertyValueFactory<>("prixsac"));
         colonneTva.setCellValueFactory(new PropertyValueFactory<>("TVA"));
         colonnepunit.setCellValueFactory(new PropertyValueFactory<>("punitaire"));
         
    } 
    
    private void load(){
    
        try {
            statement = con.prepareStatement("SELECT * FROM listproduit");
            result = statement.executeQuery();
            
            while(result.next()){
                data.add(new liste(
                result.getInt("id"),
                result.getString("nom_produit"),
                 result.getInt("prixkp"), 
                 result.getInt("prixkg"),
                 result.getInt("prixsac"),
                 result.getInt("tva"),
                 result.getInt("punitaire")
                ));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableview.setItems(data);
        FilteredList<liste> filterData= new FilteredList<>(data,e->true);
       


       recherche.textProperty().addListener((observable, oldValue, newValue) -> {
           filterData.setPredicate(liste->{
                if(newValue==null || newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(liste.getId().toString().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else  if(liste.getNomproduit().toLowerCase().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else  if(liste.getPrixKp().toString().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else  if(liste.getPrixsac().toString().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else  if(liste.getPrixKilo().toString().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else  if(liste.getTVA().toString().indexOf(toLowerCaseFilter) > -1){
                    return true;
                
                }else{
                    return false;
                }

            
           });
       });
        SortedList<liste> customers = new SortedList<>(filterData);
        customers.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(customers);
        
        }
        
    
}
