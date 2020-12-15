/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mcman
 */
public class CartItem {
    private Item item;
    private final IntegerProperty quantity;
    private final DoubleProperty total;
    
    public CartItem(Item item, int qty)
    {
        this.item = item;
        quantity = new SimpleIntegerProperty(qty);
        total = new SimpleDoubleProperty();
        total.bind(quantity.multiply(item.priceProperty));
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }
    
    public IntegerProperty quantity()
    {
        return this.quantity;
    }

    public double getTotal()
    {
        return total.getValue();
    }
    
    public DoubleProperty total()
    {
        return this.total;
    }
    
    @Override
    public String toString() {
        return "CartItem: " + "item=" + item + ", quantity=" + quantity + ", total= " + total.getValue();
    }
    
    
}