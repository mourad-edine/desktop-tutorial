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


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>test</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>









<?php
//php PDO

try
{
	// On se connecte à MySQL
	$mysqlClient = new PDO('mysql:host=localhost;dbname=mourad;charset=utf8', 'root', '1234');
}
catch(Exception $e)
{
	// En cas d'erreur, on affiche un message et on arrête tout
        die('Erreur : '.$e->getMessage());
}

// Si tout va bien, on peut continuer

// On récupère tout le contenu de la table recipes
$sqlQuery = 'SELECT * FROM test';
$recipesStatement = $mysqlClient->prepare($sqlQuery);
$recipesStatement->execute();
$listes = $recipesStatement->fetchAll(PDO::FETCH_ASSOC);
?>

<table class="table">
  <thead>
    <tr>
      <th scope="col">#id</th>
      <th scope="col">nom</th>
      <th scope="col">email</th>
      <th scope="col">grade</th>
    </tr>
  </thead>
<?php foreach($listes as $liste) {
?>

  <tbody>
    <tr>
      <td><?php echo $liste['user_id']; ?></td>
      <td><?php echo $liste['username']; ?></td>
      <td><?php echo $liste['email']; ?></td>
      <td><?php echo $liste['type']; ?></td>
    </tr>
  </tbody>
<?php
}
?>
</table>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script> 
</body>
</html>










SELECT eleve.nom,note.noteglobal,matiere.nom_matiere 
FROM eleve,note,matiere
WHERE eleve.id = note.id_eleve AND note.id_matiere = matiere.id;
