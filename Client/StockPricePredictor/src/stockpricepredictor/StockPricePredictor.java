
package stockpricepredictor;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StockPricePredictor extends Application {

    public static final String serverIP = "192.168.43.110";

    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Boundary/PredictionView.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
          
    }

    public static void main(String[] args) {
//        System.out.println("in main");

        launch(args);
      
    }
    
}
