/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author mcman
 */
public abstract class Item {
    protected final StringProperty nameProperty, descriptionProperty;
    protected final DoubleProperty priceProperty;

    /**
     * Constructor for a database record
     * @param name The name given to the record
     * @param description The description of the record
     * @param price The price of the item
     */
    public Item(String name, String description, double price) {
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.priceProperty = new SimpleDoubleProperty(price);
    }
    
    //Getters for the basic data held in the property    
    public String getName()
    {
        return nameProperty.get();
    }
    
    public String getDescription()
    {
        return descriptionProperty.get();
    }
    
    public double getPrice()
    {
        return priceProperty.get();
    }

    public void setNameProperty(String name) {
        this.nameProperty.set(name);
    }

    public void setDescriptionProperty(String description) {
        this.descriptionProperty.set(description);
    }

    public void setPriceProperty(double price) {
        this.priceProperty.set(price);
    }
    
    @Override
    public String toString() {
        return String.format("%s: $%.2f", getName(), getPrice());
    }
}
