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
import edu.psu.bw.fxmlkioskmaven.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML
    private Button btnAdmin;
    @FXML
    private Label lblWelcome;
    
    private TestItemList itemList;
    private List<CartItem> cart;
    private Stage stage;
    private Stage checkoutStage;
    private Stage adminStage;

    @FXML
    private void handleCheckout() {
        //Load the checkout page
        System.out.println("Loading Checkout Page");

        checkoutStage = new Stage();
        Parent root = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/fxml/Checkout.fxml").openStream());
            CheckoutController cController = (CheckoutController) fxmlLoader.getController();
            if (cController != null) {
                cController.loadCart(cart);
            }
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            checkoutStage.setTitle("Checkout");
            checkoutStage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Hide the menu when the checkout menu is being shown
        this.stage.hide();
        checkoutStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                openMenu();
            }
        });

        //Launch test frame
        checkoutStage.show();
    }

    public void openMenu()
    {
        stage.show();
    }
    
    public void updateMenu()
    {
        itemList.loadItemList();
    }
    
    @FXML
    private void handleAdminButton() {
        System.out.println("Loading Administration Page");

        adminStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/AdministrationPage.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");

            adminStage.setTitle("Administration");
            adminStage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Update the menu from the DB after the admin panel is closed
        adminStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                updateMenu();
            }
        });
        
        //Launch test frame
        adminStage.show();
    }

    @FXML
    private void handleLoginButton() {
        Stage loginStage = new Stage();
        Parent root = null;
        
        final LoginController lControl;
        
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            root = fxmlLoader.load(getClass().getResource("/fxml/Login.fxml").openStream());
            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            lControl = (LoginController)fxmlLoader.getController();
            
            loginStage.setTitle("Administration");
            loginStage.setScene(scene);

            
            loginStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                User cUser = lControl.getUser();
                //openMenu();
                if(cUser != null)
                {
                    addNameToWelcome(cUser.getName());
                    if(cUser.getAccess() > 2)
                        authorizeAdmin();
                }
            }
        });
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        //Launch test frame
        loginStage.show();
    }

    private void addNameToWelcome(String name)
    {
        lblWelcome.setText("Welcome, " + name);
    }
    
    private void authorizeAdmin()
    {
        btnAdmin.setVisible(true);
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
        TableColumn<TestItem, String> nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn<TestItem, Double> priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn<TestItem, String> descriptionColumn = new TableColumn("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory("description"));

        //Adding the columns to the table
        displayTable.getColumns().setAll(nameColumn, priceColumn, descriptionColumn);
        addButtonColumn();
        
        btnAdmin.setVisible(false);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void addButtonColumn() {
        TableColumn<TestItem, Void> addColumn = new TableColumn("");
        Callback<TableColumn<TestItem, Void>, TableCell<TestItem, Void>> cellFactory = null;
 
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
        };

        addColumn.setCellFactory(cellFactory);
        displayTable.getColumns().add(addColumn);
    }

    public boolean addToCart(TestItem item) {
        if (item == null) {
            return false;
        }

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getItem() == item) {
                cart.get(i).setQuantity(cart.get(i).getQuantity() + 1);
                return true;
            }
        }
        
        return cart.add(new CartItem(item, 1));
    }

    public void showCart() {
        cart.forEach(e -> {
            System.out.println(e);
        });
    }
}
