/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.FoodItemsDTO;
import functions.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class FoodItemsDAO {

    private static Connection connection = null;
    private static PreparedStatement prepQuery = null;
    private static ResultSet rs = null;

    private static final String INSERT_QUERY = "INSERT INTO food_items (userID, name, quantity, ExpirationDate, price, startDate, endDate, foodType) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM food_items WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE food_items SET userID = ?, name = ?, quantity = ?, ExpirationDate = ?, price = ?, startDate = ?, endDate = ?, foodType = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM food_items WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM food_items";

    public void addFoodItem(FoodItemsDTO foodItem) {
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_QUERY);
            prepQuery.setInt(1, foodItem.getUserID());
            prepQuery.setString(2, foodItem.getName());
            prepQuery.setInt(3, foodItem.getQuantity());
            prepQuery.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            prepQuery.setDouble(5, foodItem.getPrice());
            prepQuery.setTimestamp(6, foodItem.getStartDate());
            prepQuery.setTimestamp(7, foodItem.getEndDate());
            prepQuery.setString(8, foodItem.getFoodType());
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
    }

    public FoodItemsDTO getFoodItem(int id) {
        FoodItemsDTO foodItem = null;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_QUERY);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                foodItem = new FoodItemsDTO();
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
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return foodItem;
    }

    public List<FoodItemsDTO> getAllFoodItems() {
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                FoodItemsDTO foodItem = new FoodItemsDTO();
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
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return foodItems;
    }

    public boolean updateFoodItem(FoodItemsDTO foodItem) {
        boolean rowUpdated = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_QUERY);
            prepQuery.setInt(1, foodItem.getUserID());
            prepQuery.setString(2, foodItem.getName());
            prepQuery.setInt(3, foodItem.getQuantity());
            prepQuery.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            prepQuery.setDouble(5, foodItem.getPrice());
            prepQuery.setTimestamp(6, foodItem.getStartDate());
            prepQuery.setTimestamp(7, foodItem.getEndDate());
            prepQuery.setString(8, foodItem.getFoodType());

            rowUpdated = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowUpdated;
    }

    public boolean deleteFoodItem(int id) {
        boolean rowDeleted = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(DELETE_QUERY);
            prepQuery.setInt(1, id);
            rowDeleted = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return rowDeleted;
    }
}
