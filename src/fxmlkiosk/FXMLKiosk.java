/*
 * file name: FXMLKiosk.java
 * programmer name: Nick McManus
 * date created: 09-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package fxmlkiosk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nick McManus
 */
public class FXMLKiosk extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        System.out.println(model.FXUnwrapper.getStructure(root));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
