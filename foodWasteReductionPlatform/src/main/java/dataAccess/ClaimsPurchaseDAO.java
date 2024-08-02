/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.Claims_PurchaseDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class ClaimsPurchaseDAO {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    private static final String INSERT_QUERY = "INSERT INTO claims_purchase (foodItemID, quantity, userID, claimedAt) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM claims_purchase WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE claims_purchase SET foodItemID = ?, quantity = ?, userID = ?, claimedAt = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM claims_purchase WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM claims_purchase";

    public void addClaim(Claims_PurchaseDTO claim) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, claim.getFoodItemID());
            preparedStatement.setInt(2, claim.getQuantity());
            preparedStatement.setInt(3, claim.getUserID());
            preparedStatement.setTimestamp(4, claim.getClaimedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Claims_PurchaseDTO getClaim(int id) {
        Claims_PurchaseDTO claim = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                claim = new Claims_PurchaseDTO();
                claim.setFoodItemID(rs.getInt("foodItemID"));
                claim.setQuantity(rs.getInt("quantity"));
                claim.setUserID(rs.getInt("userID"));
                claim.setClaimedAt(rs.getTimestamp("claimedAt"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claim;
    }

    public List<Claims_PurchaseDTO> getAllClaims() {
        List<Claims_PurchaseDTO> claims = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Claims_PurchaseDTO claim = new Claims_PurchaseDTO();
                claim.setFoodItemID(rs.getInt("foodItemID"));
                claim.setQuantity(rs.getInt("quantity"));
                claim.setUserID(rs.getInt("userID"));
                claim.setClaimedAt(rs.getTimestamp("claimedAt"));
                claims.add(claim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public boolean updateClaim(Claims_PurchaseDTO claim) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, claim.getFoodItemID());
            preparedStatement.setInt(2, claim.getQuantity());
            preparedStatement.setInt(3, claim.getUserID());
            preparedStatement.setTimestamp(4, claim.getClaimedAt());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteClaim(int id) {
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
