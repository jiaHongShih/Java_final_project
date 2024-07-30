/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.UserDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO - A Data Access Object (DAO) class for managing UserDTO entities.
 * Provides methods for performing CRUD operations on user data in the database.
 * 
 * @author Joshua Barrett
 */
public class UserDAO {
    
    // Database connection details
    private static final String DB_URL = "jdbc:your_database_url";
    private static final String DB_USERNAME = "your_username";
    private static final String DB_PASSWORD = "your_password";

    // SQL queries
    private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, password, userType, location) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, password = ?, userType = ?, location = ? WHERE id = ?";

    /**
     * Inserts a new user into the database.
     * @param user UserDTO object containing user data to be inserted.
     */
    public void insertUser(UserDTO user) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType().name());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    /**
     * Selects a user from the database by ID.
     * @param id The ID of the user to be retrieved.
     * @return UserDTO object containing user data, or null if not found.
     */
    public UserDTO selectUser(int id) {
        UserDTO user = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                UserDTO.UserType userType = UserDTO.UserType.valueOf(rs.getString("userType"));
                String location = rs.getString("location");
                user = new UserDTO();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setLocation(location);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     * @return List of UserDTO objects containing data for all users.
     */
    public List<UserDTO> selectAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                UserDTO.UserType userType = UserDTO.UserType.valueOf(rs.getString("userType"));
                String location = rs.getString("location");
                UserDTO user = new UserDTO();
                user.setId(id);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                user.setLocation(location);
                users.add(user);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

      /**
     * Deletes a user from the database by ID.
     * @param id The ID of the user to be deleted.
     * @return true if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(int id) {
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    /**
     * Updates an existing user in the database.
     * @param user UserDTO object containing updated user data.
     * @return true if the user was successfully updated, false otherwise.
     */
    public boolean updateUser(UserDTO user) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType().name());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setInt(6, user.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    /**
     * Handles SQL exceptions by printing detailed error information.
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
