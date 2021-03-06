/*
 * file name: SQLiteDatabaseTest.java
 * programmer name: Nick McManus
 * date created: 10-18-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package edu.psu.bw.fxmlkioskmaven.controller;

//JDBC Source: https://github.com/xerial/sqlite-jdbc

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseTest {

    //Code taken from https://github.com/xerial/sqlite-jdbc
    public SQLiteDatabaseTest()
      {
        Connection connection = null;
        try
        {
          // create a database connection
          //relative path modified to work properly
          connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/db/sample.db");
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);  // set timeout to 30 sec.

          statement.executeUpdate("drop table if exists person");
          statement.executeUpdate("create table person (id integer, name string)");
          statement.executeUpdate("insert into person values(1, 'leo')");
          statement.executeUpdate("insert into person values(2, 'yui')");
          ResultSet rs = statement.executeQuery("select * from person");
          while(rs.next())
          {
            // read the result set
            System.out.println("name = " + rs.getString("name"));
            System.out.println("id = " + rs.getInt("id"));
          }
        }
        catch(SQLException e)
        {
          // if the error message is "out of memory",
          // it probably means no database file is found
          System.err.println(e.getMessage());
        }
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
      }
}
