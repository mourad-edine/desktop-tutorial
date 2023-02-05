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
    
    
    

    
    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showBooks();
        load();

    }
    
    //database
    
    public Connection getConnection(){
        Connection conn;
        try{
          Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mourad?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "1234";
            
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    
    //liste
    
    public ObservableList<facture> getFacture(){
        ObservableList<facture> facture = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM temporary";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
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
        String query = "INSERT INTO temporary(quantite,designation, Pu, Pt) VALUES (" + quantite.getText() + ",'" + designation.getValue() + "','" + prixunit.getText() + "',"
                + quantite.getText() + ")";
       // String quer = "INSERT INTO test VALUES (" + tfId.getText() + "," + tfPages.getText() + ")";
        executeQuery(query);
       // executeQuery(quer);
        showBooks();
    
    }
    
    
    //delete
    
    private void deleteButton(){
        
        
        String query = "DELETE FROM temporary ";
        executeQuery(query);
        showBooks();
    }
    
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
        
        ObservableList<String> list = FXCollections.observableArrayList("kilo","kapoaka","En sac");
        qualite.setItems(list);
        
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
    
    
   
    
    
    
    
    
    
}