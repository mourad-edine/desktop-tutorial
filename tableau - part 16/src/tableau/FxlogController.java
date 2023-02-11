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
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class FxlogController implements Initializable {
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    
    //facture
     @FXML
    private Button delete;
    @FXML
    private DatePicker date;
    @FXML
    private TableColumn<facture,Integer> colonn1;

    @FXML
    private TableColumn<facture, String> colonn2;

    @FXML
    private TableColumn<facture,Integer> colonn3;

    @FXML
    private TableColumn<facture,Integer> colonn4;
    
    @FXML
    private TableView<facture> tablefacture;
    
    @FXML
    private Label total;
    @FXML
    private Button bouton;
    @FXML
    private TextField client;
    @FXML
    private TextField punit;
    @FXML
    private TextField quantit;
     @FXML
    private ComboBox<String> genre;
     
    
    @FXML
    private Button btn12;
    
    @FXML
    private ComboBox<String> designation;
    
    private ObservableList<facture> datax;
    
    
    @FXML
    private ComboBox<String> qualit;
    
    
    ObservableList<String> list = FXCollections.observableArrayList("kilo","kapoaka","En sac");

    
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        list();
        try {
            // TODO
          con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
         somme();
         load();

        try {
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         
         
         
    
    }

    @FXML
    public void load() {
        ObservableList<String> james= FXCollections.observableArrayList("monsieur","madame");
        genre.setItems(james);
        
        ObservableList<String> data= FXCollections.observableArrayList();
        try {
            statement = con.prepareStatement("SELECT * FROM listproduit");
            result = statement.executeQuery();
            
            while(result.next()){
                
                data.add(
                       result.getString("nom_produit")    
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
        designation.setItems(null);
        designation.setItems(data);

    }
    public void list(){
       qualit.setItems(list);
       
    }
    public void ajouter(ActionEvent event) throws SQLException{
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String typeachat,nomproduit,nomclient,civilite;
            
            String birth = String.valueOf(date.getValue());
            typeachat = (String)qualit.getValue();
            nomproduit = (String)designation.getValue();
            nomclient = client.getText();
            int quantite = Integer.parseInt(quantit.getText());
            civilite = genre.getValue();
            int punitaire = Integer.parseInt(punit.getText());
            int pTotal = quantite*punitaire;
            
            
            try{
                String sql = "insert into archivefacture(Type_achat,nom_produit,nom_client,quantite,civilite,punitaire,ptotal,date) value(?,?,?,?,?,?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setString(1,typeachat);
                statement.setString(2,nomproduit);
                statement.setString(3,nomclient);
                statement.setInt(4,quantite);
                statement.setString(5,civilite);
                statement.setInt(6,punitaire);
                statement.setInt(7,pTotal);
                statement.setString(8, birth);
                
                
                
                int result = statement.executeUpdate();
                
            }catch(SQLException e){
                e.printStackTrace();
            
            
            }
            try{
                String sql = "insert into temporary(quantite,designation,Pu,Pt) value(?,?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setInt(1,quantite);
                statement.setString(2,nomproduit);
                statement.setInt(3,punitaire);
                statement.setInt(4,pTotal);
                int result = statement.executeUpdate();
                
            }catch(SQLException e){
                e.printStackTrace();
            
            
            }
            String sqlu = "SELECT SUM(Pt) FROM temporary";
            int Total = 0;
            try{
               statement  = con.prepareStatement(sqlu);
               result = statement.executeQuery();
            if(result.next()){
                 Total = result.getInt("SUM(Pt)");
            }   
            
            total.setText(Integer.toString(Total)+" Ar");
            }catch(Exception e){
                e.printStackTrace();
            }
            afficher();
            
    
    }
    
    public void afficher() throws SQLException{
    
       colonn1.setCellValueFactory(new PropertyValueFactory<facture,Integer>("quantiteP"));
         colonn2.setCellValueFactory(new PropertyValueFactory<facture,String>("designation"));
         colonn3.setCellValueFactory(new PropertyValueFactory<facture,Integer>("Prix_unit"));
         colonn4.setCellValueFactory(new PropertyValueFactory<facture,Integer>("prixT"));
         
        datax= dba.DBConnection.getDatausers();
        tablefacture.setItems(datax);
    }
    
    
    @FXML
    void miseAjour(ActionEvent event) throws SQLException{
        try {
               // TODO
             con = dba.DBConnection.pmartConnection();
         }catch (SQLException ex) {
             Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
         }
        String query = "DELETE FROM temporary";
        Statement st;
        try{
            st = con.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        afficher();

    }
    
    /*private void deleteButton(){
         try {
               // TODO
             con = dba.DBConnection.pmartConnection();
         }catch (SQLException ex) {
             Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        String query = "DELETE FROM temporary";
        Statement st;
        try{
            st = con.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    
        

    }
    */
    @FXML
     public void somme() {
        
    }
    
    
    
}