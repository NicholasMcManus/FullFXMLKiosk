package edu.psu.bw.fxmlkioskmaven.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLController implements Initializable {

    @FXML
    private Label label;
    private Stage stage;
    
    //Button that launches the admin panel. This will look nicer later.
    @FXML
    private void handleAdminAction(ActionEvent event)
    {
        System.out.println("Loading Administration Page");
        
        stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/AdministrationPage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Administration");
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Launch test frame
        stage.show();
    }
    
    //Handler that launches the menu panel
    @FXML
    private void handleButtonAction(ActionEvent event) {        
        stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/FXMLBuilder.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("JavaFX and Maven");
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Launch test frame
        stage.show();
    }

    //Nothing special happens when the primary frame is called.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Prepare to launch the test frame when the button is pressed
        //*
        
        //*/
    }
}
