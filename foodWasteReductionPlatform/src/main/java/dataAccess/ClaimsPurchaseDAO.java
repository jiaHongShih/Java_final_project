/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.Claims_PurchaseDTO;
import functions.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class ClaimsPurchaseDAO {

    private static Connection connection = null;
    private static PreparedStatement prepQuery = null;
    private static ResultSet rs = null;

    private static final String INSERT_QUERY = "INSERT INTO claims_purchase (foodItemID, quantity, userID, claimedAt) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM claims_purchase WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE claims_purchase SET foodItemID = ?, quantity = ?, userID = ?, claimedAt = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM claims_purchase WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM claims_purchase";

    public void addClaim(Claims_PurchaseDTO claim) {
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_QUERY);
            prepQuery.setInt(1, claim.getFoodItemID());
            prepQuery.setInt(2, claim.getQuantity());
            prepQuery.setInt(3, claim.getUserID());
            prepQuery.setTimestamp(4, claim.getClaimedAt());
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

    public Claims_PurchaseDTO getClaim(int id) {
        Claims_PurchaseDTO claim = null;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_QUERY);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                claim = new Claims_PurchaseDTO();
                claim.setFoodItemID(rs.getInt("foodItemID"));
                claim.setQuantity(rs.getInt("quantity"));
                claim.setUserID(rs.getInt("userID"));
                claim.setClaimedAt(rs.getTimestamp("claimedAt"));
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
        return claim;
    }

    public List<Claims_PurchaseDTO> getAllClaims() {
        List<Claims_PurchaseDTO> claims = new ArrayList<>();
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = prepQuery.executeQuery();

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
        } finally {
            try {
                rs.close();
                prepQuery.close();
            } catch (SQLException ex) {
                Logger.log("fail to close");
            }
        }
        return claims;
    }

    public boolean updateClaim(Claims_PurchaseDTO claim) {
        boolean rowUpdated = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_QUERY);
            prepQuery.setInt(1, claim.getFoodItemID());
            prepQuery.setInt(2, claim.getQuantity());
            prepQuery.setInt(3, claim.getUserID());
            prepQuery.setTimestamp(4, claim.getClaimedAt());

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

    public boolean deleteClaim(int id) {
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
