
package tableau;
 

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.stage.StageStyle;

 
public class TableauMainApp extends Application {
    
    
    @Override
    public void start(Stage Stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("page3.fxml"));
       Scene scene = new Scene(root);
       Stage.setScene(scene);
       //Stage.initStyle(StageStyle.TRANSPARENT);
       Stage.show();
       Stage.setTitle("le vent du sud");
       
    }
    public static void main(String[] args) {
        launch(args);
    }
}