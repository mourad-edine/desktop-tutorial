/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package testy;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;


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
    private TableColumn<?, ?> colonn1;

    @FXML
    private TableColumn<?, ?> colonn2;

    @FXML
    private TableColumn<?, ?> colonn3;

    @FXML
    private TableColumn<?, ?> colonn4;
    
    @FXML
    private TableView<facture> tablefacture;
    
    
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
         
         load();
         datax = FXCollections.observableArrayList();
         afficher();
        
         
    
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
    public void ajouter(ActionEvent event){
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(FxlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            String typeachat,nomproduit,nomclient,civilite;
            
            
            typeachat = (String)qualit.getValue();
            nomproduit = (String)designation.getValue();
            nomclient = client.getText();
            int quantite = Integer.parseInt(quantit.getText());
            civilite = genre.getValue();
            int punitaire = Integer.parseInt(punit.getText());
            int pTotal = quantite*punitaire;
            
            
            try{
                String sql = "insert into archivefacture(Type_achat,nom_produit,nom_client,quantite,civilite,punitaire,ptotal) value(?,?,?,?,?,?,?)";
                statement  = con.prepareStatement(sql);
                
                statement.setString(1,typeachat);
                statement.setString(2,nomproduit);
                statement.setString(3,nomclient);
                statement.setInt(4,quantite);
                statement.setString(5,civilite);
                statement.setInt(6,punitaire);
                statement.setInt(7,pTotal);
                
                
                
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
    
    public void afficher(){
    
       colonn1.setCellValueFactory(new PropertyValueFactory<>("quantiteP"));
         colonn2.setCellValueFactory(new PropertyValueFactory<>("designation"));
         colonn3.setCellValueFactory(new PropertyValueFactory<>("prix_unit"));
         colonn4.setCellValueFactory(new PropertyValueFactory<>("prixT"));
        
    }
    
    
    
}