/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import java.io.IOException;
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
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class ModifierController implements Initializable {
    //////////////////////
    @FXML
    private TextField txtid;

    @FXML
    private TextField txtkilo;

    @FXML
    private TextField txtkp;

    @FXML
    private TextField txtproduit;

    @FXML
    private TextField txtsac;

    @FXML
    private TextField txttva;

    @FXML
    private TextField txtunit;
    ///////////////
    @FXML
    private TextField recherche;
    
    @FXML
    private TableColumn<liste,Integer> colonneID;

    @FXML
    private TableColumn<liste,Integer> colonnePrixKP;

    @FXML
    private TableColumn<liste,Integer> colonnePrixkilo;

    @FXML
    private TableColumn<liste,Integer> colonnePrixsac;

    @FXML
    private TableColumn<liste,String> colonneProduit;

    @FXML
    private TableColumn<liste,Integer> colonneTva;
    @FXML
    private TableColumn<liste,Integer> colonnepunit;

    @FXML
    private TableView<liste> tableview;
    
    private ObservableList<liste> data;
    @FXML
    private AnchorPane root;
    int index = -1;
    
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
         colonneID.setCellValueFactory(new PropertyValueFactory<liste,Integer>("id"));
         colonneProduit.setCellValueFactory(new PropertyValueFactory<liste,String>("nomproduit"));
         colonnePrixKP.setCellValueFactory(new PropertyValueFactory<liste,Integer>("prixKp"));
         colonnePrixkilo.setCellValueFactory(new PropertyValueFactory<liste,Integer>("prixKilo"));
         colonnePrixsac.setCellValueFactory(new PropertyValueFactory<liste,Integer>("prixsac"));
         colonneTva.setCellValueFactory(new PropertyValueFactory<liste,Integer>("TVA"));
         colonnepunit.setCellValueFactory(new PropertyValueFactory<liste,Integer>("punitaire"));
         
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
    
    @FXML
    public void bandeau(MouseEvent event) {
       index = tableview.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txtid.setText(colonneID.getCellData(index).toString());
    txtproduit.setText(colonneProduit.getCellData(index).toString());
    txtkp.setText(colonnePrixKP.getCellData(index).toString());
    txtsac.setText(colonnePrixsac.getCellData(index).toString());
    txtkilo.setText(colonnePrixkilo.getCellData(index).toString());
    txttva.setText(colonneTva.getCellData(index).toString());
    txtunit.setText(colonnepunit.getCellData(index).toString());
    
    }
    
    
    
    @FXML
    public void modifiere(ActionEvent event) {
         try {
            con = dba.DBConnection.pmartConnection();
            String value1 = txtid.getText();
            String value2 = txtproduit.getText();
            String value3 = txtkp.getText();
            String value4 = txtsac.getText();
            String value5 = txtkilo.getText();
            String value6 = txttva.getText();
            String value7 = txtunit.getText();
            String sql = "update listproduit set id= '"+value1+"',nom_produit= '"+value2+"',prixkp= '"+
                    value3+"',prixsac= '"+value4+"',prixkg= '"+value5+"',tva= '"+value6+"',punitaire= '"+value7+"' where id='"+value1+"' ";
            statement= con.prepareStatement(sql);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "reussi!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    public void supprimere(ActionEvent event) throws SQLException {
       con = dba.DBConnection.pmartConnection();
    String sql = "delete from listproduit where id = ?";
        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, txtid.getText());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "effacé avac succes!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     @FXML
    public void retout(ActionEvent event) {
         try {
            Parent fxml = FXMLLoader.load(getClass().getResource("AjoutProduit.fxml"));
            root.getChildren().removeAll();
            root.getChildren().setAll(fxml);

        }catch(IOException e){
            e.printStackTrace();
        
        }
    }
        
    
}
