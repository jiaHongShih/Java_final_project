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

    private static final String INSERT_QUERY = "INSERT INTO FoodItems (userID, name, quantity, expirationDate, price, foodPreferences) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM FoodItems WHERE userID = ? ORDER BY expirationDate";
    private static final String SELECT_ONE_QUERY = "SELECT * FROM FoodItems WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE FoodItems SET userID = ?, name = ?, quantity = ?, expirationDate = ?, price = ?, foodPreferences = ?, isSurplus =? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM FoodItems WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM FoodItems";

    public void addFoodItem(FoodItemsDTO foodItem) {
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_QUERY);
            prepQuery.setInt(1, foodItem.getUserID());
            prepQuery.setString(2, foodItem.getName());
            prepQuery.setInt(3, foodItem.getQuantity());
            prepQuery.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            prepQuery.setDouble(5, foodItem.getPrice());
            prepQuery.setString(6, foodItem.getFoodPreferences());
            prepQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public FoodItemsDTO getOneFoodItem(int id) {
        FoodItemsDTO foodItem = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ONE_QUERY);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                foodItem = new FoodItemsDTO();
                foodItem.setId(rs.getInt("id"));
                foodItem.setUserID(rs.getInt("userID"));
                foodItem.setName(rs.getString("name"));
                foodItem.setQuantity(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("expirationDate").toLocalDate());
                foodItem.setPrice(rs.getDouble("price"));
                foodItem.setFoodPreferences(rs.getString("foodPreferences"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return foodItem;
    }
    
    public List<FoodItemsDTO> getFoodItem(int userID) {
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_QUERY);
            prepQuery.setInt(1, userID);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                FoodItemsDTO foodItem = new FoodItemsDTO();
                foodItem.setId(rs.getInt("id"));
                foodItem.setUserID(rs.getInt("userID"));
                foodItem.setName(rs.getString("name"));
                foodItem.setQuantity(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("expirationDate").toLocalDate());
                foodItem.setPrice(rs.getDouble("price"));
                foodItem.setFoodPreferences(rs.getString("foodPreferences"));
                foodItem.setSurplus(rs.getBoolean("isSurplus"));
                foodItems.add(foodItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return foodItems;
    }

    public List<FoodItemsDTO> getAllFoodItems() {
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                FoodItemsDTO foodItem = new FoodItemsDTO();
                foodItem.setId(rs.getInt("id"));
                foodItem.setUserID(rs.getInt("userID"));
                foodItem.setName(rs.getString("name"));
                foodItem.setQuantity(rs.getInt("quantity"));
                foodItem.setExpirationDate(rs.getDate("expirationDate").toLocalDate());
                foodItem.setPrice(rs.getDouble("price"));
                foodItem.setFoodPreferences(rs.getString("foodPreferences"));
                foodItems.add(foodItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return foodItems;
    }

    public boolean updateFoodItem(FoodItemsDTO foodItem) {
        boolean rowUpdated = false;
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_QUERY);
            prepQuery.setInt(1, foodItem.getUserID());
            prepQuery.setString(2, foodItem.getName());
            prepQuery.setInt(3, foodItem.getQuantity());
            prepQuery.setDate(4, Date.valueOf(foodItem.getExpirationDate()));
            prepQuery.setDouble(5, foodItem.getPrice());
            prepQuery.setString(6, foodItem.getFoodPreferences());
            prepQuery.setBoolean(7, foodItem.isSurplus());
            prepQuery.setInt(8, foodItem.getId());

            rowUpdated = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rowUpdated;
    }

    public boolean deleteFoodItem(int id) {
        boolean rowDeleted = false;
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(DELETE_QUERY);
            prepQuery.setInt(1, id);
            rowDeleted = prepQuery.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepQuery != null) {
                    prepQuery.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rowDeleted;
    }
}
