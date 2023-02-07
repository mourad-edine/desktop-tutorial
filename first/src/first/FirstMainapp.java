/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package first;

import com.sun.javafx.logging.PlatformLogger.Level;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chams
 */
public class FirstMainapp {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //connect to database
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/mourad","root","1234");
            if(connect == null){
                System.out.println("echec");
            }else{
                 System.out.println("ça marche");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         

    }
    
    
}













SELECT eleve.nom,note.noteglobal,matiere.nom_matiere 
FROM eleve,note,matiere
WHERE eleve.id = note.id_eleve AND note.id_matiere = matiere.id;
