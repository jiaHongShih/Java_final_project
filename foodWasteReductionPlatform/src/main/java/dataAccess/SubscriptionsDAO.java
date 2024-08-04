/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.SubscriptionsDTO;
import functions.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class SubscriptionsDAO {

    private static Connection connection = null;
    private static PreparedStatement prepQuery = null;
    private static ResultSet rs = null;

    private static final String INSERT_QUERY = "INSERT INTO subscriptions (userID, phoneNum, CommunicationMethod, foodPreferences) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM subscriptions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE subscriptions SET userID = ?, phoneNum = ?, CommunicationMethod = ?, foodPreferences = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM subscriptions WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM subscriptions";

    public void addSubscription(SubscriptionsDTO subscription) {
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(INSERT_QUERY);
            prepQuery.setInt(1, subscription.getUserID());
            prepQuery.setString(2, subscription.getPhoneNum());
            prepQuery.setString(3, subscription.getCommunicationMethod());
            prepQuery.setString(4, subscription.getFoodPreferences());
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

    public SubscriptionsDTO getSubscription(int id) {
        SubscriptionsDTO subscription = null;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_QUERY);
            prepQuery.setInt(1, id);
            rs = prepQuery.executeQuery();

            if (rs.next()) {
                subscription = new SubscriptionsDTO();
                subscription.setUserID(rs.getInt("userID"));
                subscription.setPhoneNum(rs.getString("phoneNum"));
                subscription.setCommunicationMethod(rs.getString("CommunicationMethod"));
                subscription.setFoodPreferences(rs.getString("foodPreferences"));
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
        return subscription;
    }

    public List<SubscriptionsDTO> getAllSubscriptions() {
        List<SubscriptionsDTO> subscriptions = new ArrayList<>();
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                SubscriptionsDTO subscription = new SubscriptionsDTO();
                subscription.setUserID(rs.getInt("userID"));
                subscription.setPhoneNum(rs.getString("phoneNum"));
                subscription.setCommunicationMethod(rs.getString("CommunicationMethod"));
                subscription.setFoodPreferences(rs.getString("foodPreferences"));
                subscriptions.add(subscription);
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
        return subscriptions;
    }

    public boolean updateSubscription(SubscriptionsDTO subscription) {
        boolean rowUpdated = false;
        try {
            connection = (Connection) DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(UPDATE_QUERY);
            prepQuery.setInt(1, subscription.getUserID());
            prepQuery.setString(2, subscription.getPhoneNum());
            prepQuery.setString(3, subscription.getCommunicationMethod());
            prepQuery.setString(4, subscription.getFoodPreferences());

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

    public boolean deleteSubscription(int id) {
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
