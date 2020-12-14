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
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Nick McManus
 */
public class CheckoutController implements Initializable {

    
    private ObservableList<CartItem> cart;
    private DoubleProperty total;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cart = new SimpleListProperty(FXCollections.observableArrayList());
        total = new SimpleDoubleProperty(0);
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
    }
    
    private void addItemCost(CartItem item)
    {
        total.setValue(total.add(item.total().get()).get());
    }
}
