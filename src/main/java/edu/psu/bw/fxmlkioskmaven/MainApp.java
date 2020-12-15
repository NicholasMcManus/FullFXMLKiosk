package edu.psu.bw.fxmlkioskmaven;

import edu.psu.bw.fxmlkioskmaven.controller.FXMLBuilderController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {   
        
        //Launching root application
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/fxml/FXMLBuilder.fxml").openStream());
        FXMLBuilderController bController = (FXMLBuilderController)fxmlLoader.getController();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("[Restaurant Name Here]");
        stage.setScene(scene);
        
        //Add the stage to the controller so it can be opened and closed
        if(bController != null)
            bController.setStage(stage);
        
        stage.show();
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
