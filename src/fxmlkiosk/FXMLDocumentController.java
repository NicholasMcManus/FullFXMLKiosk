/*
 * file name: FXMLDocumentController.java
 * programmer name: Nick McManus
 * date created: 09-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package fxmlkiosk;

//Implementation of FXML Encoder based on research by 
//https://people.kth.se/~maguire/.c/DEGREE-PROJECT-REPORTS/131008-Elmira_Khodabandehloo-with-cover.pdf


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Nick McManus
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
