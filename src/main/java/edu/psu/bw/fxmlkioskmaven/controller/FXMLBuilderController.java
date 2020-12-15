/*
 * file name: FXMLBuilderController.java
 * programmer name: Nick McManus
 * date created: 10-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.CartItem;
import edu.psu.bw.fxmlkioskmaven.model.TestItem;
import edu.psu.bw.fxmlkioskmaven.model.TestItemList;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Nick McManus
 */
public class FXMLBuilderController implements Initializable {

    @FXML
    private TableView<TestItem> displayTable;
    @FXML
    private Button btnCheckout;
    
    private TestItemList itemList;
    private List<CartItem> cart;
    private Stage stage;
    
    @FXML
    private void handleCheckout()
    {
        //The checkout button was pushed
        System.out.println("Attempting to checkout");
        
        //Load the checkout page
        System.out.println("Loading Checkout Page");
        
        Stage stage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/fxml/Checkout.fxml").openStream());
            CheckoutController cController = (CheckoutController)fxmlLoader.getController();
            if(cController != null)
                cController.loadCart(cart);
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Checkout");
            stage.setScene(scene);
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Launch test frame
        stage.show();
    }
    
    @FXML
    private void handleAdminButton()
    {
        System.out.println("Loading Administration Page");
        
        Stage stage = new Stage();
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
    
    @FXML
    private void handleLoginButton()
    {
        System.out.println("Logging in once this is implemented");
    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemList = new TestItemList();
        cart = new ArrayList();
        
        displayTable.setItems(itemList.getItemList());
        
        //Setting up item columns
        System.out.println("Setting up table columns");
        
        //Setting up columns that correspond to database columns        
        TableColumn<TestItem,String> nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn<TestItem, Double> priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn<TestItem,String> descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));
        
        //Adding the columns to the table
        displayTable.getColumns().setAll(nameColumn, priceColumn, descriptionColumn);
        addButtonColumn();
    }
    
    private void addButtonColumn()
    {
        TableColumn<TestItem,Void> addColumn = new TableColumn("");
        Callback<TableColumn<TestItem, Void>, TableCell<TestItem, Void>> cellFactory = null;
        
        //* Netbeans refuses to parse the file when this is included. I don't blame it. 
        cellFactory = (final TableColumn<TestItem, Void> param) -> {
            final TableCell<TestItem, Void> cell = new TableCell<TestItem, Void>() {
                
                private final Button btn = new Button("Add to Cart");
                
                {
                    btn.setOnAction((ActionEvent event) -> {
                        TestItem data = getTableView().getItems().get(getIndex());
                        System.out.println("selectedData: " + data);
                        addToCart(data);
                        showCart();
                        System.out.println("\n\n");
                    });
                }
                
                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
            return cell;
        };//*/
        
        addColumn.setCellFactory(cellFactory);
        displayTable.getColumns().add(addColumn);
    }
    
    public boolean addToCart(TestItem item)
    {
        if(item == null)
            return false;
        
        for(int i = 0; i < cart.size(); i++)
        {
            if(cart.get(i).getItem() == item)
            {
                cart.get(i).setQuantity(cart.get(i).getQuantity() + 1);
                return true;
            }
        }
        cart.add(new CartItem(item, 1));
        return true;
    }
    
    public void showCart()
    {
        cart.forEach(e -> {
            System.out.println(e);
        });
    }
}