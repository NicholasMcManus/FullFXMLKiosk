/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.psu.bw.fxmlkioskmaven.controller;

import edu.psu.bw.fxmlkioskmaven.model.User;
import java.util.HashMap;

/**
 *
 * @author Nick
 */
public class UserController {
/*The purpose of the class is to be more extensible in the future starting
  with a hashmap with a single record that should be easier to transition to
  a database. Basically treating this part more like an adapter.
*/
    private HashMap<String, User> people;//Representing role table
    private HashMap<String,String> data; //Representing username password table
    
    public UserController()
    {
        data = new HashMap();
        data.put("ist261", "162tsi");
        data.put("admin", "admin");
        
        people = new HashMap();
        people.put("ist261", new User("ist261",0));
        people.put("admin", new User ("admin",3));
    }
    
    /**
     * 
     * @param username the username to search
     * @param password the password that is paired with it for authentication
     * @return null if match fails or username is not found
     */
    public User attemptLogin(String username, String password)
    {
        if(!data.containsKey(username) || !data.get(username).equals(password))
        {
            return null;
        }
        
        return people.get(username);
    }
}
