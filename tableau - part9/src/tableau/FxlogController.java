/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
    private TextField client;
    
    @FXML
    private Label total;
    
    
    @FXML
    private TableColumn<facture, Integer> colonne1;

    @FXML
    private TableColumn<facture, String> colonne2;

    @FXML
    private TableColumn<facture, Integer> colonne3;

    @FXML
    private TableColumn<facture, Integer> colonne4;
    
    //@FXML
    //private TableColumn<facture, Integer> colonne5;
    
    @FXML
    private TableView<facture> tvBooks;
    
    
   
    
    
    @FXML
    private TextField quantite;
    @FXML
    private TextField prixunit;
     @FXML
    private ComboBox<String> genre;
     
    
    @FXML
    private Button Loads;
    
    @FXML
    private ComboBox<String> designation;
    
    //private ObservableList<facture> datax;
    
    
    @FXML
    private ComboBox<String> qualite;
    
    
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;
    
    @FXML
    private Button charger;
    
    

    
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }/*else if(event.getSource() == charger){
            charger();
        }*/
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBooks();
        load();

    }
    
    //database
    
   
    
    //liste
    
    public ObservableList<facture> getFacture(){
        ObservableList<facture> facture = FXCollections.observableArrayList();
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        String query = "SELECT * FROM temporary";
        Statement st;
        ResultSet rs;
        
        try{
            st = con.createStatement();
            rs = st.executeQuery(query);
            facture books;
            while(rs.next()){
                books = new facture(rs.getInt("quantite"), rs.getString("designation"), rs.getInt("Pu"), rs.getInt("Pt"));
                facture.add(books);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return facture;
    }
    
    
    
    public void showBooks(){
        ObservableList<facture> list = getFacture();
        
        colonne1.setCellValueFactory(new PropertyValueFactory<facture, Integer>("quantiteP"));
        colonne2.setCellValueFactory(new PropertyValueFactory<facture, String>("designation"));
        colonne3.setCellValueFactory(new PropertyValueFactory<facture, Integer>("Prix_unit"));
        colonne4.setCellValueFactory(new PropertyValueFactory<facture, Integer>("prixT"));
        
        tvBooks.setItems(list);
    }
    
    //insert
    
    private void insertRecord(){
        
        try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        String type,nomC,civil,vrai;
        
        type = qualite.getValue();
        nomC = client.getText();
        civil = genre.getValue();
        int Quantite = Integer.parseInt(quantite.getText());
        vrai = type;
        int unitaire = Integer.parseInt(prixunit.getText());
        int pTotal = Quantite*unitaire;
        String query = "INSERT INTO temporary(quantite,designation, Pu, Pt) VALUES (" + Quantite+ ",'" + designation.getValue() + "','" +unitaire + "',"+ pTotal + ")";
       String quer = "INSERT INTO archivefacture(Type_achat,nom_produit, nom_client, quantite, civilite, punitaire,ptotal) VALUES (" + type + "," + designation.getValue()+ "," + nomC + "," + Quantite + "," + civil + "," + unitaire + "," + pTotal + ")";
        executeQuery(query);
        //String quer = "INSERT INTO test(prix,genre) VALUES (" +unitaire + ","+nomC+")";
       executeQuery(quer);
        showBooks();
       /* String typeachat,nomproduit,nomclient,civilite;
            
            
            typeachat = (String)qualite.getValue();
            nomproduit = (String)designation.getValue();
            nomclient = client.getText();
            int Quantite = Integer.parseInt(quantite.getText());
            civilite = genre.getValue();
            int punitaire = Integer.parseInt(prixunit.getText());
            int pTotal = Quantite*punitaire;
            String sql = "insert into archivefacture(Type_achat,nom_produit,nom_client,quantite,civilite,punitaire,ptotal) value(?,?,?,?,?,?,?)";
                //statement  = con.prepareStatement(sql);
               try{
                
                statement.setString(1,typeachat);
                statement.setString(2,nomproduit);
                statement.setString(3,nomclient);
                statement.setInt(4,Quantite);
                statement.setString(5,civilite);
                statement.setInt(6,punitaire);
                statement.setInt(7,pTotal);
                
                int result = statement.executeUpdate(sql);
               }catch(SQLException e){
                   e.printStackTrace();
               }*/
    }
    
    
    //delete
    
    private void deleteButton(){
        
        
        String query = "DELETE FROM temporary ";
        executeQuery(query);
        showBooks();
    }
    
    private void executeQuery(String query) {
        
       try {
               // TODO
               con = dba.DBConnection.pmartConnection();
            } catch (SQLException ex) {
               Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Statement st;
        try{
            
            st = con.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    
    
////////////////////////////////////////////////////////////////////////////////////
    
    
    //combo box
    
    
    @FXML
    public void load() {
        
        
        try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> james= FXCollections.observableArrayList("monsieur","madame");
        genre.setItems(james);
        
        ObservableList<String> giravy = FXCollections.observableArrayList("kilo","kapoaka","En sac");
        qualite.setItems(giravy);
        
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
        
        
        try{
        
          String sqli = "SELECT SUM(Pt) FROM temporary";
          statement = con.prepareStatement(sqli);
          result = statement.executeQuery();
          String tect = result.getString("SUM(Pt)");
          total.setText(tect);
        }catch(SQLException e){
        
           e.printStackTrace();
        }
        designation.setItems(null);
        designation.setItems(data);

    }
    /*private void charger(){
        PreparedStatement st;
        ResultSet rs;
        try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TableauController_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
        
          String sqli = "SELECT SUM(Pt) FROM temporary";
          st = con.prepareStatement(sqli);
          rs = statement.executeQuery();
          String tect = result.getString("SUM(Pt)");
          total.setText(tect);
        }catch(SQLException e){
        
           e.printStackTrace();
        }
    }*/
    
    
   
    
    
    
    
    
    
}