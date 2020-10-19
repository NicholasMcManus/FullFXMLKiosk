/*
 * file name: TestItemList.java
 * programmer name: Nick McManus
 * date created: 10-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package edu.psu.bw.fxmlkioskmaven.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

public class TestItemList {
    
    //Setting up an observable list
    private ListProperty<TestItem> itemList;

    /**
     * Just put together a list for testing methodology
     */
    public TestItemList()
    {
        itemList = new SimpleListProperty(FXCollections.observableArrayList());
        
        Connection connection = null;
        try {
            // create a database connection
            //relative path modified to work properly
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/Kiosk.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            //This will eventually be a prepared statement getting table from information given by admin users
            ResultSet rs = statement.executeQuery("select * from TestItems");
            
            //ItemID|ItemName|ItemPrice|ItemDescription
            //ID, Name, Description, Price
            while (rs.next()) {
                // read the result set
                TestItem temp;
                
                //Get the componants from the current record
                String name = rs.getString("ItemName");
                int id = rs.getInt("ItemId");
                double price = rs.getDouble("ItemPrice");
                String description = rs.getString("ItemDescription");
                
                temp = new TestItem(id, name, description, price);
                itemList.add(temp);
            }
            
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
    
    /**
     * The getter that actually return the observable list
     * @return An observable list of TestItems
     */
    public ListProperty<TestItem> getItemList() {
        return itemList;
    }
}
