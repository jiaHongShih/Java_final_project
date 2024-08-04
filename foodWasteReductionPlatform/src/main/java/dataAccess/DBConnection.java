/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nicholas Jacques
 */
public class DBConnection {

    private static DBConnection db;

    private static Connection connection = null;

    private String serverUrl = "jdbc:mysql://localhost:3306/bookvault";
    private String userString = "root";
    private String passwordString = "11111111";
    private String driverString = "com.mysql.cj.jdbc.Driver";

    //create connection
    private DBConnection() {
        try {
            Class.forName(driverString);
            this.connection = DriverManager.getConnection(serverUrl, userString, passwordString);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * getInstance will return the instance of the DBConnection (only one copy)
     *
     * @return DBConnection
     */
    public static DBConnection getInstance() {
        if (db == null) {
            db = new DBConnection();
        }
        return db;
    }

    /**
     * getConnection will return the Connection
     *
     * @return Connection
     */
    public Connection getConnection() {
        return connection;
    }
}
