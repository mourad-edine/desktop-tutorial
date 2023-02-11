/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Manouher
 */
public class FactureController implements Initializable {
    
    @FXML
    private TextField txtnom;

    @FXML
    private TextField txtptotal;

    @FXML
    private TextField txtquantite;
    
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;
    
    private ObservableList<listproduit> data;
    
    int index = -1;
    @FXML
    private TextField idtxt;
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
    
     @FXML
    private TextField recherche;
    private String FILE_NAME = "ite.pdf"; 


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
    @FXML
    public void marimar(ActionEvent event) throws SQLException {
        try {
            // TODO
              con = dba.DBConnection.pmartConnection();
           } catch (SQLException ex) {
              Logger.getLogger(FactureController.class.getName()).log(Level.SEVERE, null, ex);
           }
          String nomP = null,nomC = null,nomu = null;
          int i = 0,j=0,k=0;
           String value1 = txtnom.getText();
           statement = con.prepareStatement("SELECT Type_achat,nom_produit,nom_client,quantite,punitaire,ptotal FROM archivefacture WHERE nom_client = '"+value1+"'");
           result = statement.executeQuery();
           
           PdfPTable pdfPTable =new PdfPTable(6);
           PdfPCell pdfPCell7 = new PdfPCell(new Paragraph("quantite"));
                        PdfPCell pdfPCell8 = new PdfPCell(new Paragraph("desigantion"));
                        PdfPCell pdfPCell9 = new PdfPCell(new Paragraph("client"));
                        PdfPCell pdfPCell10 = new PdfPCell(new Paragraph("prix unitaire"));
                        PdfPCell pdfPCell11 = new PdfPCell(new Paragraph("Type achat"));
                        PdfPCell pdfPCell12 = new PdfPCell(new Paragraph("Prix total"));
           
           while(result.next()){
                nomP = result.getString("Type_achat");
                nomC = result.getString("nom_produit");
                nomu= result.getString("nom_client");
                i = result.getInt("quantite");
                j = result.getInt("punitaire");
                k = result.getInt("ptotal");
                        
                        PdfPCell pdfCell5 = new PdfPCell(new Phrase(nomP)); 
                        PdfPCell pdfCell2 = new PdfPCell(new Phrase(nomC)); 
                        PdfPCell pdfCell3 = new PdfPCell(new Phrase(nomu)); 
                        PdfPCell pdfCell1 = new PdfPCell(new Phrase(Integer.toString(i))); 
                        PdfPCell pdfCell4 = new PdfPCell(new Phrase(Integer.toString(j))); 
                        PdfPCell pdfCell6 = new PdfPCell(new Phrase(Integer.toString(k))); 
                        pdfPTable.addCell(pdfCell1);
                        pdfPTable.addCell(pdfCell2);
                        pdfPTable.addCell(pdfCell3);
                        pdfPTable.addCell(pdfCell4);
                        pdfPTable.addCell(pdfCell5);
                        pdfPTable.addCell(pdfCell6);
                        //pdfCell3.setRotation(90);
                        pdfPTable.setWidthPercentage(70);
            } 
                 

            Document document = new Document();

            try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));
                        document.open();
                        pdfPTable.addCell(pdfPCell7);
                        pdfPTable.addCell(pdfPCell8);
                        pdfPTable.addCell(pdfPCell9);
                        pdfPTable.addCell(pdfPCell10);
                        pdfPTable.addCell(pdfPCell11);
                        pdfPTable.addCell(pdfPCell12);
                        Paragraph p = new Paragraph();
                        p.add("FACTURE");
                        p.setAlignment(Element.ALIGN_CENTER);
                        
                        document.add(pdfPTable);
                        document.close();
                        Desktop.getDesktop().open(new File("ite.pdf"));

            } catch (FileNotFoundException | DocumentException e) {
               e.printStackTrace();
            } catch (IOException e) {
               e.printStackTrace();
        }
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
    idtxt.setText(colonneNom.getCellData(index).toString());
    txtnom.setText(colonneClient.getCellData(index).toString());
    txtquantite.setText(colonnequant.getCellData(index).toString());
    txtptotal.setText(colonnePt.getCellData(index).toString());
    }
    
    
}
