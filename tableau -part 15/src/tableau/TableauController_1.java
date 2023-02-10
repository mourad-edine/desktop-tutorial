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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
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
    int index = -1;
    
    
    @FXML
    private TextField recherche;
    @FXML
    private TableView<listproduit> tableaux;
    @FXML
    private TableColumn<listproduit,String> colonneAchat;

    @FXML
    private TableColumn<listproduit,String> colonneClient;

    @FXML
    private TableColumn<listproduit,Integer> colonneID;

    @FXML
    private TableColumn<listproduit,String> colonneNom;

    @FXML
    private TableColumn<listproduit,Integer> colonnePt;

    @FXML
    private TableColumn<listproduit,Integer> colonnePut;

    @FXML
    private TableColumn<listproduit,String> colonneciv;

    @FXML
    private TableColumn<listproduit,Integer> colonnequant;
    @FXML
    private TextField idtxt;
    
    private ObservableList<facture> datax;
    


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
         colonneID.setCellValueFactory(new PropertyValueFactory<listproduit,Integer>("id"));
         colonneAchat.setCellValueFactory(new PropertyValueFactory<listproduit,String>("typeachat"));
         colonneNom.setCellValueFactory(new PropertyValueFactory<listproduit,String>("nom_produit"));
         colonneClient.setCellValueFactory(new PropertyValueFactory<listproduit,String>("client"));
         colonnequant.setCellValueFactory(new PropertyValueFactory<listproduit,Integer>("quantite"));
         colonneciv.setCellValueFactory(new PropertyValueFactory<listproduit,String>("civilite"));
         colonnePut.setCellValueFactory(new PropertyValueFactory<listproduit,Integer>("punitaire"));
         colonnePt.setCellValueFactory(new PropertyValueFactory<listproduit,Integer>("ptotal"));
         tableaux.setItems(data);
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

        FilteredList<listproduit> filterData= new FilteredList<>(data,e->true);
       


       recherche.textProperty().addListener((observable, oldValue, newValue) -> {
           filterData.setPredicate(liste->{
                if(newValue==null || newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String toLowerCaseFilter = newValue.toLowerCase();
                if(liste.getTypeachat().toLowerCase().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else if(liste.getNom_produit().toLowerCase().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else if(liste.getClient().toLowerCase().indexOf(toLowerCaseFilter) > -1){
                    return true;
                }else{
                    return false;
      
                }

            
           });
       });
        SortedList<listproduit> customers = new SortedList<>(filterData);
        customers.comparatorProperty().bind(tableaux.comparatorProperty());
        tableaux.setItems(customers);
        }
    
     @FXML
    public void getSelected(MouseEvent event) {
        index = tableaux.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    idtxt.setText(colonneID.getCellData(index).toString());

    }
    
    
    //////////////////////////////////////////////////////////////////////////
    
    @FXML
    public void deletebutton(ActionEvent event) throws SQLException {
         con = dba.DBConnection.pmartConnection();
    String sql = "delete from archivefacture where id= ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, idtxt.getText());
            statement.execute();
            //UpdateTable();
            JOptionPane.showMessageDialog(null, "reussi");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
}