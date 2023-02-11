/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tableau;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
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
    private String FILE_NAME = "itext.pdf"; 


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
    public void marimar(ActionEvent event) {
        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();
            
            PdfPTable pdfPTable =new PdfPTable(4); 
                        PdfPCell pdfCell1 = new PdfPCell(new Phrase("nom client")); 
                        PdfPCell pdfCell2 = new PdfPCell(new Phrase("desigantion")); 
                        PdfPCell pdfCell3 = new PdfPCell(new Phrase("quantité")); 
                        PdfPCell pdfCell4 = new PdfPCell(new Phrase("total"));
                        
                        ///
                        PdfPCell pdfCell5 = new PdfPCell(new Phrase(txtnom.getText())); 
                        PdfPCell pdfCell6 = new PdfPCell(new Phrase(idtxt.getText())); 
                        PdfPCell pdfCell7 = new PdfPCell(new Phrase(txtquantite.getText())); 
                        PdfPCell pdfCell8 = new PdfPCell(new Phrase(txtptotal.getText()));
                        ///
                        pdfPTable.addCell(pdfCell1);
                        pdfPTable.addCell(pdfCell2);
                        pdfPTable.addCell(pdfCell3);
                        pdfPTable.addCell(pdfCell4);
                        pdfPTable.addCell(pdfCell5);
                        pdfPTable.addCell(pdfCell6);
                        pdfPTable.addCell(pdfCell7);
                        pdfPTable.addCell(pdfCell8);
                        pdfPTable.setWidthPercentage(70);
                        document.add(pdfPTable);
            /*Paragraph p2 = new Paragraph();
            p2.add("                        "+txtnom.getText()
                    + "                     "+idtxt.getText()
                    + "                     "+txtquantite.getText()
                    + "                     "+txtptotal.getText()); //no alignment

            document.add(p2);*/
            //close
            document.close();
            Desktop.getDesktop().open(new File("itext.pdf"));

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
