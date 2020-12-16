/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.TestItem;
import edu.psu.bw.fxmlkioskmaven.model.TestItemList;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author mcman
 */
public class AdministrationPageController implements Initializable {


    //Fields for creating an item
    @FXML
    private Label createStatus;
    @FXML
    private TextField createName;
    @FXML
    private TextField createDescription;
    @FXML 
    private TextField createPrice;
    
    //Fields for updating items
    @FXML
    private Label updateStatus;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updateDescription;
    @FXML
    private TextField updatePrice;
    
    //Combobox used for both deleting and updating
    @FXML
    private ComboBox deleteBox;
    
    //The connection to be used to communicate with the database
    private Connection connection;
    private TestItemList itemList;
    
    /**
     * The method used by the button that is the user saying that a record
     * is ready to be added to the database
     * @param event 
     */
    @FXML
    private void handleItemCreate(ActionEvent event)
    {
        //Delete before Commit:
        //Insert Into TestItems(ItemName, ItemPrice, ItemDescription) Values ('Grilled Cheese', 1.50, 'Processed Cheese');
        String createStatement = "Insert Into TestItems(ItemName, ItemPrice, ItemDescription) Values (?,?,?)";
        try(PreparedStatement pState = connection.prepareStatement(createStatement))
        {
            pState.setString(1, createName.getText().trim());
            pState.setDouble(2,Double.parseDouble(createPrice.getText().trim()));
            pState.setString(3, createDescription.getText().trim());
            
            //Execute and get the status of the create attempt
            String status;
            if(pState.executeUpdate() > 0)
            {
                createStatus.setTextFill(Paint.valueOf("#00AA00"));
                status = "Success";
                resetCreateInput();
                this.getData();
            }
            else
            {
                createStatus.setTextFill(Paint.valueOf("#FF0000"));
                status = "Failure";
            }
            createStatus.setText(status);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdministrationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * A method to be called that clears the input boxes
     */
    private void resetCreateInput()
    {
        createName.setText("");
        createPrice.setText("");
        createDescription.setText("");
    }
    
    /**
     * The method that handles deleting by using the database ID contained in
     * the object to delete a single entry. If none selected, nothing happens
     * @param event 
     */
    @FXML
    private void handleItemDelete(ActionEvent event)
    {
        if(deleteBox.getValue() == null)
        {
            return;
        }
        
        //Change to a log entry
        System.out.println("Delete Item at ID: " + ((TestItem)deleteBox.getValue()).getId());
        
        String createStatement = "Delete From TestItems Where ItemID = ?";
        try(PreparedStatement pState = connection.prepareStatement(createStatement))
        {            
            pState.setInt(1, ((TestItem)deleteBox.getValue()).getId());
            
            //Execute and get the status of the create attempt
            String status;
            if(pState.executeUpdate() > 0)
            {
                status = "Success";
                this.getData();
            }
            else
            {
                status = "Failure";
            }
            System.out.println("Delete: " + status);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdministrationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * The method to handle update requests from administrators
     * Nothing happens if the ComboBox has nothing selected
     * @param event 
     */
    @FXML
    private void handleItemUpdate(ActionEvent event)
    {
        if(deleteBox.getValue() == null){return;}            
            
        String createStatement = "Update TestItems Set ItemName = ?, ItemPrice = ?, ItemDescription = ? Where ItemID = ?";
        try(PreparedStatement pState = connection.prepareStatement(createStatement))
        {
            pState.setString(1, updateName.getText().trim());
            pState.setDouble(2,Double.parseDouble(updatePrice.getText().trim()));
            pState.setString(3, updateDescription.getText().trim());
            pState.setInt(4, ((TestItem)deleteBox.getValue()).getId());
            //Execute and get the status of the create attempt
            String status;
            if(pState.executeUpdate() > 0)
            {
                status = "Success";
                resetCreateInput();
                this.getData();
                updateStatus.setTextFill(Paint.valueOf("#00AA00"));
            }
            else
            {
                updateStatus.setTextFill(Paint.valueOf("#FF0000"));
                status = "Failure";
            }
            updateStatus.setText(status);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdministrationPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    /**
     * Get data from the database
     */
    private void getData()
    {
        itemList.loadItemList();
    }
    
    private void setupPriceRestrictions(TextField field)
    {
        field.textProperty().addListener((ObservableValue<? extends String> ov, String oValue, String nValue) -> {
            if(!nValue.matches("\\d{0,7}([\\.]\\d{0,2})?"))
            {
                field.setText(oValue);
            }
        });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Initialize the ItemList and configure the combobox
        itemList = new TestItemList();
        deleteBox.itemsProperty().bind(itemList.getItemList());
        
        //Setup the function that updates the update fields when records are selected
        deleteBox.valueProperty().addListener((arg, oldVal, newVal) -> {
            if(newVal != null)
            {
                TestItem item = (TestItem)newVal;
                updateName.setText(item.getName());
                updatePrice.setText(item.getPrice()+"");
                updateDescription.setText(item.getDescription());
            }
            else
            {
                updateName.setText("");
                updatePrice.setText("");
                updateDescription.setText("");
            }
        });
        
        //Setup the database connection
        try
        {
          // create a database connection
          //relative path modified to work properly
          connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/Kiosk.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        
        //Configure price boxes
        setupPriceRestrictions(createPrice);
        setupPriceRestrictions(updatePrice);
    }    
}
