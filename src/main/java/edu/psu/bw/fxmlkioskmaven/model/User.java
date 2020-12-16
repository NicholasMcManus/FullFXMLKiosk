/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Nick
 */
public class User {
    StringProperty name;
    int access;
    
    public User(String username, int access)
    {
        name = new SimpleStringProperty(username);
        this.access = access;
    }
    
    public User()
    {
        this("",-1);
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
    
    public String getName()
    {
        return name.getValue();
    }
    
    public void setName(String username)
    {
        name.set(username);
    }
    
    public StringProperty nameProperty()
    {
        return name;
    }
}
