/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.UserDTO;
import functions.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO - A Data Access Object (DAO) class for managing UserDTO entities.
 * Provides methods for performing CRUD operations on user data in the database.
 *
 * @author Josh Barrett
 */
public class UserDAO {

    // Database connection details
    private static Connection connection = null;
    private static PreparedStatement prepQuery = null;
    private static ResultSet rs = null;
    // SQL queries
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, password, userType, location) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, password = ?, userType = ?, location = ? WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";



    /**
     * Inserts a new user into the database.
     *
     * @param user UserDTO object containing user data to be inserted.
     */
    public int insertUser(UserDTO user) {
        int rowsInserted = 0;

        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_USER_SQL);
            prepQuery.setString(1, user.getName());
            prepQuery.setString(2, user.getEmail());
            prepQuery.setString(3, user.getPassword());
            prepQuery.setString(4, user.getUserType());
            prepQuery.setString(5, user.getLocation());
            rowsInserted = prepQuery.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            if (prepQuery != null) {
                try {
                    prepQuery.close();
                } catch (SQLException ex) {
                    Logger.log("Failed to close PreparedStatement: " + ex.getMessage());
                }
            }
        }
        return rowsInserted;
    }

    /**
     * Selects a user from the database by ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return UserDTO object containing user data, or null if not found.
     */
    public UserDTO selectUser(int id) {
        UserDTO user = null;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_USER_BY_ID);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                String location = rs.getString("location");
                user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setLocation(location);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return List of UserDTO objects containing data for all users.
     */
    public List<UserDTO> selectAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_USERS);
            rs = prepQuery.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String userType = rs.getString("userType");
                String location = rs.getString("location");
                UserDTO user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setLocation(location);
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return users;
    }

    /**
     * Deletes a user from the database by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(DELETE_USER_SQL);
            prepQuery.setInt(1, id);
            rowDeleted = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowDeleted;
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user UserDTO object containing updated user data.
     * @return true if the user was successfully updated, false otherwise.
     */
    public boolean updateUser(UserDTO user) {
        boolean rowUpdated = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_USER_SQL);
            prepQuery.setString(1, user.getName());
            prepQuery.setString(2, user.getEmail());
            prepQuery.setString(3, user.getPassword());
            prepQuery.setString(4, user.getUserType());
            prepQuery.setString(5, user.getLocation());
            rowUpdated = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowUpdated;
    }
    
        /**
     * Selects a user from the database by email and password.
     *
     * @param email The email of the user to be retrieved.
     * @param password The password of the user to be retrieved.
     * @return UserDTO object containing user data, or null if not found.
     */
    public UserDTO selectUserByEmailAndPassword(String email, String password) {
        UserDTO user = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_USER_BY_EMAIL_AND_PASSWORD);
            prepQuery.setString(1, email);
            prepQuery.setString(2, password);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String userType = rs.getString("userType");
                String location = rs.getString("location");

                user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setLocation(location);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (prepQuery != null) prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("Failed to close resources: " + ex.getMessage());
            }
        }
        return user;
    }

    /**
     * Handles SQL exceptions by printing detailed error information.
     *
     * @param ex SQLException object containing details of the error.
     */
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
