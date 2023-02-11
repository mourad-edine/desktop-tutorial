package testy;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CopyController implements Initializable {
     @FXML
    private TextField textnom;

    @FXML
    private TextField textimprimer;
    private Connection con;
    private PreparedStatement statement;
    private ResultSet result;

    private static final String FILE_NAME = "ite.pdf";
    
    public void initialize(URL url, ResourceBundle rb) {

    
    }
    @FXML
    public void PDF() throws SQLException {
        try {
            // TODO
            con = dba.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(CopyController.class.getName()).log(Level.SEVERE, null, ex);
        }
          String nomP = null,nomC = null,nomu = null,noml= null;
           statement = con.prepareStatement("SELECT * FROM test");
           result = statement.executeQuery();
           
           PdfPTable pdfPTable =new PdfPTable(5);
           while(result.next()){
                nomP = result.getString("username");
                nomC = result.getString("password");
                nomu= result.getString("email");
                noml = result.getString("type");
                
                PdfPCell pdfCell1 = new PdfPCell(new Phrase(nomP)); 
                        PdfPCell pdfCell2 = new PdfPCell(new Phrase(nomC)); 
                        PdfPCell pdfCell3 = new PdfPCell(new Phrase(nomu)); 
                        PdfPCell pdfCell4 = new PdfPCell(new Phrase(noml)); 
                        pdfPTable.addCell(pdfCell1);
                        pdfPTable.addCell(pdfCell2);
                        pdfPTable.addCell(pdfCell3);
                         pdfPTable.addCell(pdfCell4);
                        //pdfCell3.setRotation(90);
                        pdfPTable.setWidthPercentage(70);
            } 
                 
            textimprimer.setText("nom");
            textnom.setText("nom");
           Document document = new Document();

           try {

            PdfWriter.getInstance(document, new FileOutputStream(new File(FILE_NAME)));

            //open
            document.open();           
                        document.add(pdfPTable);
                        document.close();
                        Desktop.getDesktop().open(new File("ite.pdf"));

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}