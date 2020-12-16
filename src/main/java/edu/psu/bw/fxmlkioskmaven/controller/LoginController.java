/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author Nick
 */
public class LoginController implements Initializable {

    @FXML
    PasswordField passwordField;
    @FXML
    TextField userTextField;
    @FXML
    Button btnQuit;
    @FXML
    Label loginStatus;
    
    private final static Logger LOG = LogManager.getLogger(LoginController.class);
    
    private UserController loginSystem;
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginSystem = new UserController();
        user = null;
    }    
    
    public User getUser()
    {
        return user;
    }
    
    public void onQuit(EventHandler<ActionEvent> e)
    {
        btnQuit.setOnAction(e);
    }
    
    @FXML
    private void handleLogin()
    {
        login();
        if(user != null)
        {
            loginStatus.setText("Welcome, " + user.getName());
            userTextField.setText("");
            passwordField.setText("");
            LOG.info("Login Successful.");
        }
        else
        {
            loginStatus.setText("Try again.");
            LOG.info("Login Failed.");
        }
    }
    
    private void login()
    {
        user = loginSystem.attemptLogin(userTextField.getText(), passwordField.getText());
    }
}
