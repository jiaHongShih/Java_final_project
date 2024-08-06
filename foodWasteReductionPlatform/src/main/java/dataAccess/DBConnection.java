/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import functions.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbInstance;
    private Connection connection;

    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/foodwaste";
    private static final String USER = "Josh";
    private static final String PASSWORD = "CodeFlash73";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Private constructor to prevent instantiation
    private DBConnection() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(SERVER_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log("Failed to create database connection: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Logger.log("Database driver not found: " + e.getMessage());
        }
    }

    // Double-checked locking singleton pattern
    public static DBConnection getInstance() {
        if (dbInstance == null) {
            synchronized (DBConnection.class) {
                if (dbInstance == null) {
                    dbInstance = new DBConnection();
                }
            }
        }
        return dbInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Method to close the connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Logger.log("Failed to close database connection: " + e.getMessage());
            }
        }
    }

// Method to set a test connection
    public void setTestConnection(Connection connection) {
        this.connection = connection;
    }
}

