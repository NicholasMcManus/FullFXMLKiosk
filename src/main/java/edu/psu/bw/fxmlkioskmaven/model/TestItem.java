/*
 * file nameProperty: TestItem.java
 * programmer nameProperty: Nick McManus
 * date created: 10-18-2020
 * date of last revision: 
 * details of last revision:
 * short descriptionProperty: 
 */

package edu.psu.bw.fxmlkioskmaven.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestItem {
    //Class variables
    private final IntegerProperty idProperty;
    private final StringProperty nameProperty, descriptionProperty;
    private final DoubleProperty priceProperty;

    /**
     * Constructor for a database record
     * @param ID The ID of the record
     * @param name The name given to the record
     * @param description The description of the record
     * @param price The price of the item
     */
    public TestItem(int ID, String name, String description, double price) {
        this.idProperty = new SimpleIntegerProperty(ID);
        this.nameProperty = new SimpleStringProperty(name);
        this.descriptionProperty = new SimpleStringProperty(description);
        this.priceProperty = new SimpleDoubleProperty(price);
    }

    //Accessor methods for the individual properties
    public IntegerProperty idProperty() {
        return idProperty;
    }

    public StringProperty nameProperty() {
        return nameProperty;
    }

    public StringProperty descriptionProperty() {
        return descriptionProperty;
    }

    public DoubleProperty priceProperty() {
        return priceProperty;
    }
    
    //Getters for the basic data held in the property
    public int getId()
    {
        return idProperty.get();
    }
    
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

    //Setters for the different properties
    public void setIdProperty(int id) {
        this.idProperty.set(id);
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
}
