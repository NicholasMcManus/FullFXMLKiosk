package edu.psu.bw.fxmlkioskmaven;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import edu.psu.bw.fxmlkioskmaven.model.*;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Testing DB Stuff
        new edu.psu.bw.fxmlkioskmaven.controller.SQLiteDatabaseTest();
        
        //*
        //Launching root application
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLBuilder.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        
        //Test unwrapping javafx node
        System.out.println(FXUnwrapper.getStructure(root));
        
        stage.show();
        //*/
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
