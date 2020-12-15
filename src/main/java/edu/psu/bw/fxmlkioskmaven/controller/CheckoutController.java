/*
 * file name: CheckoutController.java
 * programmer name: Nick McManus
 * date created: 12-04-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.CartItem;
import edu.psu.bw.fxmlkioskmaven.model.FXUnwrapper;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 * FXML Controller class
 *
 * @author Nick McManus
 */
public class CheckoutController implements Initializable {

    @FXML
    VBox receiptBox;
    
    @FXML
    TextField totalBox;
    
    @FXML
    TextField paymentBox;
    
    @FXML
    TextField remainingBox;
    
    @FXML
    Region sampleRegion;
    
    private ObservableList<CartItem> cart;
    private DoubleProperty total;
    private DoubleProperty payment;
    private DoubleProperty change;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cart = new SimpleListProperty(FXCollections.observableArrayList());
        total = new SimpleDoubleProperty(0);
        payment = new SimpleDoubleProperty(0);
        change = new SimpleDoubleProperty();
        StringConverter<Number> strConverter = new NumberStringConverter();
        
        //Bind total to the display object
        totalBox.textProperty().bind(total.asString("$%.2f"));
        paymentBox.textProperty().addListener((ObservableValue<? extends String> ov, String oValue, String nValue) -> {
            if(!nValue.matches("\\d{0,7}([\\.]\\d{0,2})?"))
            {
                paymentBox.setText(oValue);
            }
        });
        
        paymentBox.textProperty().bindBidirectional(payment, strConverter);
        change.bind(total.subtract(payment));
        remainingBox.textProperty().bind(change.asString("$%.2f"));
    }    
    
    public void loadCart(List<CartItem> cart)
    {
        this.cart.addAll(cart);        
        System.out.println("Cart passed into Checkout: Size: " + cart.size());
        cart.forEach(item -> 
        {
            System.out.println("Name: " + item.getItem().getName());
            addItemCost(item);
        });
        System.out.println("Total: " + String.format("$%.2f", this.total.getValue()));
        System.out.println(FXUnwrapper.getStructure(receiptBox));
    }
    
    private void addItemCost(CartItem item)
    {
        //Add items to vbox
        HBox row = new HBox();
        Region leftSpace = new Region();
        setupRegion(leftSpace);
        
        Region rightSpace = new Region();
        setupRegion(rightSpace);
        
        row.setAlignment(Pos.CENTER_LEFT);
        
        Label itemLabel = new Label();
        //itemLabel.setAlignment(Pos.CENTER_LEFT);
        itemLabel.textProperty().bind(item.getItem().nameProperty());
        itemLabel.setPrefWidth(100);
        
        Label quantityLabel = new Label();
        //quantityLabel.setAlignment(Pos.CENTER);
        quantityLabel.textProperty().bind(item.quantity().asString());
        quantityLabel.setPrefWidth(100);
        
        Label totalLabel = new Label();
        //totalLabel.setAlignment(Pos.CENTER_RIGHT);
        totalLabel.textProperty().bind(item.total().asString("%.2f"));
        totalLabel.setPrefWidth(100);
        
        row.getChildren().addAll(itemLabel, leftSpace, quantityLabel,
                rightSpace, totalLabel);
        
        receiptBox.getChildren().add(row);
        
        //Add items to total
        total.setValue(total.add(item.total().get()).get());
    }
    
    private void setupRegion(Region current)
    {
        current.maxHeightProperty().bind(current.prefHeightProperty());
        current.prefHeightProperty().bind(sampleRegion.prefHeightProperty());
        current.prefWidthProperty().bind(sampleRegion.prefWidthProperty());
    }
    
    @FXML
    private void handlePayment()
    {
        System.out.println("You are trying to checkout, but it's not implemented yet...");
    }
}
