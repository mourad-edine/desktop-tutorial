/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class MainController implements Initializable {
    
    
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfAuthor;
    @FXML
    private TextField tfYear;
    @FXML
    private TextField tfPages;
    @FXML
    private TableView<Books> tvBooks;
    @FXML
    private TableColumn<Books, Integer> colId;
    @FXML
    private TableColumn<Books, String> colAuthor;
    @FXML
    private TableColumn<Books, Integer> colYear;
    @FXML
    private TableColumn<Books, Integer> colPages;
    @FXML
    private TableColumn<Books, Integer> colTitle;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        
        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showBooks();
    }
    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mourad", "root","1234");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Books> getBooksList(){
        ObservableList<Books> bookList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM temporary";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Books books;
            while(rs.next()){
                books = new Books(rs.getInt("id"),rs.getInt("quantite"), rs.getString("designation"), rs.getInt("Pu"), rs.getInt("Pt"));
                bookList.add(books);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return bookList;
    }
    
    public void showBooks(){
        ObservableList<Books> list = getBooksList();
        
        colId.setCellValueFactory(new PropertyValueFactory<Books, Integer>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<Books, Integer>("quantite"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<Books, String>("designation"));
        colYear.setCellValueFactory(new PropertyValueFactory<Books, Integer>("Pu"));
        colPages.setCellValueFactory(new PropertyValueFactory<Books, Integer>("Pt"));
        
        tvBooks.setItems(list);
    }
    private void insertRecord(){
        String query = "INSERT INTO temporary VALUES (" + tfId.getText() + ",'" + tfTitle.getText() + "','" + tfAuthor.getText() + "',"
                + tfYear.getText() + "," + tfPages.getText() + ")";
        String quer = "INSERT INTO test(prix,genre) VALUES (" + tfTitle.getText() +"," + tfAuthor.getText() + ")";
        executeQuery(query);
        executeQuery(quer);
        showBooks();
    }
    private void updateRecord(){
        String query = "UPDATE  temporary SET quantite  = '" + tfTitle.getText() + "', designation = '" + tfAuthor.getText() + "', Pu = " +
                tfYear.getText() + ", Pt = " + tfPages.getText() + " WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showBooks();
    }
    private void deleteButton(){
        
        
        String query = "DELETE FROM temporary WHERE id =" + tfId.getText() + "";
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
    
    
    

    /*void handleMouseAction(MouseEvent event) {
       Books book = tvBooks.getSelectionModel().getSelectedItem();
       
       System.out.println("l'identifiant :"+ book.getId());
       System.out.println("quantite :"+ book.getQuantite());
       
    }*/
        
    
}
