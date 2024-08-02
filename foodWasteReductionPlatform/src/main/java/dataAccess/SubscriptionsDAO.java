/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.SubscriptionsDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Josh Barrett
 */
public class SubscriptionsDAO {

    private static final String URL = "jdbc:your_database_url";
    private static final String USER = "your_database_user";
    private static final String PASSWORD = "your_database_password";

    private static final String INSERT_QUERY = "INSERT INTO subscriptions (userID, phoneNum, CommunicationMethod, foodPreferences) VALUES (?, ?, ?, ?)";
    private static final String SELECT_QUERY = "SELECT * FROM subscriptions WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE subscriptions SET userID = ?, phoneNum = ?, CommunicationMethod = ?, foodPreferences = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM subscriptions WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM subscriptions";

    public void addSubscription(SubscriptionsDTO subscription) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, subscription.getUserID());
            preparedStatement.setString(2, subscription.getPhoneNum());
            preparedStatement.setString(3, subscription.getCommunicationMethod());
            preparedStatement.setString(4, subscription.getFoodPreferences());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SubscriptionsDTO getSubscription(int id) {
        SubscriptionsDTO subscription = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                subscription = new SubscriptionsDTO();
                subscription.setUserID(rs.getInt("userID"));
                subscription.setPhoneNum(rs.getString("phoneNum"));
                subscription.setCommunicationMethod(rs.getString("CommunicationMethod"));
                subscription.setFoodPreferences(rs.getString("foodPreferences"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscription;
    }

    public List<SubscriptionsDTO> getAllSubscriptions() {
        List<SubscriptionsDTO> subscriptions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet rs = preparedStatement.executeQuery();

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
        }
        return subscriptions;
    }

    public boolean updateSubscription(SubscriptionsDTO subscription) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, subscription.getUserID());
            preparedStatement.setString(2, subscription.getPhoneNum());
            preparedStatement.setString(3, subscription.getCommunicationMethod());
            preparedStatement.setString(4, subscription.getFoodPreferences());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteSubscription(int id) {
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