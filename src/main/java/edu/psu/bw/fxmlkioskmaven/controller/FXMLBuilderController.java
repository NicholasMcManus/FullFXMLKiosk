/*
 * file name: FXMLBuilderController.java
 * programmer name: Nick McManus
 * date created: 10-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.TestItem;
import edu.psu.bw.fxmlkioskmaven.model.TestItemList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Nick McManus
 */
public class FXMLBuilderController implements Initializable {

    @FXML
    private TableView<TestItem> displayTable;

    private TestItemList itemList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemList = new TestItemList();
        displayTable.setItems(itemList.getItemList());
        
        //Setting up item columns
        System.out.println("Setting up table columns");
        
        //Setting up columns that correspond to database columns
        TableColumn<TestItem,Integer> idColumn = new TableColumn("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        
        TableColumn<TestItem,String> nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn<TestItem, Double> priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn<TestItem,String> descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        
        //Adding the columns to the table
        displayTable.getColumns().setAll(idColumn, nameColumn, priceColumn, descriptionColumn);
    }
}
