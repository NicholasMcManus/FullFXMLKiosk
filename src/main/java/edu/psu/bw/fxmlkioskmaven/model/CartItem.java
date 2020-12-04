/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author mcman
 */
public class CartItem {
    private Item item;
    private final IntegerProperty quantity;
    
    public CartItem(Item item, int qty)
    {
        this.item = item;
        quantity = new SimpleIntegerProperty(qty);
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

    @Override
    public String toString() {
        return "CartItem{" + "item=" + item + ", quantity=" + quantity + '}' + "total: " + (item.getPrice()*getQuantity());
    }
    
    
}