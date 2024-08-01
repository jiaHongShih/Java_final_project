/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.FoodItems;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class FoodItemsDAO {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    private static final String INSERT_QUERY = "INSERT INTO food_items (userID, name, quantity, ExpirationDate, price, startDate, endDate, foodType) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM food_items WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE food_items SET userID = ?, name = ?, quantity = ?, ExpirationDate = ?, price = ?, startDate = ?, endDate = ?, foodType = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM food_items WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM food_items";

    public void addFoodItem(FoodItems foodItem) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, foodItem.getUserID());
            preparedStatement.setString(2, foodItem.getName());
            preparedStatement.setInt(3, foodItem.getQuantity());
            preparedStatement.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            preparedStatement.setDouble(5, foodItem.getPrice());
            preparedStatement.setTimestamp(6, foodItem.getStartDate());
            preparedStatement.setTimestamp(7, foodItem.getEndDate());
            preparedStatement.setString(8, foodItem.getFoodType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FoodItems getFoodItem(int id) {
        FoodItems foodItem = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                foodItem = new FoodItems();
                foodItem.setId(rs.getInt("id"));
                foodItem.setUserID(rs.getInt("userID"));
                foodItem.setName(rs.getString("name"));
                foodItem.setQuantity(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("ExpirationDate").toLocalDate());
                foodItem.setPrice(rs.getDouble("price"));
                foodItem.setStartDate(rs.getTimestamp("startDate"));
                foodItem.setEndDate(rs.getTimestamp("endDate"));
                foodItem.setFoodType(rs.getString("foodType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItem;
    }

    public List<FoodItems> getAllFoodItems() {
        List<FoodItems> foodItems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                FoodItems foodItem = new FoodItems();
                foodItem.setId(rs.getInt("id"));
                foodItem.setUserID(rs.getInt("userID"));
                foodItem.setName(rs.getString("name"));
                foodItem.setQuantity(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("ExpirationDate").toLocalDate());
                foodItem.setPrice(rs.getDouble("price"));
                foodItem.setStartDate(rs.getTimestamp("startDate"));
                foodItem.setEndDate(rs.getTimestamp("endDate"));
                foodItem.setFoodType(rs.getString("foodType"));
                foodItems.add(foodItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public boolean updateFoodItem(FoodItems foodItem) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, foodItem.getUserID());
            preparedStatement.setString(2, foodItem.getName());
            preparedStatement.setInt(3, foodItem.getQuantity());
            preparedStatement.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            preparedStatement.setDouble(5, foodItem.getPrice());
            preparedStatement.setTimestamp(6, foodItem.getStartDate());
            preparedStatement.setTimestamp(7, foodItem.getEndDate());
            preparedStatement.setString(8, foodItem.getFoodType());
            preparedStatement.setInt(9, foodItem.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteFoodItem(int id) {
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }
}
