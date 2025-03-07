/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package functions;

import dataAccess.ClaimsPurchaseDAO;
import dataAccess.DBConnection;
import dataAccess.FoodItemsDAO;
import dataAccess.SubscriptionsDAO;
import dataAccess.UserDAO;
import dataAccess.UserQuestionsDAO;
import dataObjects.Claims_PurchaseDTO;
import dataObjects.FoodItemsDTO;
import dataObjects.SubscriptionsDTO;
import dataObjects.UserDTO;
import dataObjects.UserQuestionsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author JiaHong
 */
public class BusinessLogic {

    public static List<SubscriptionsDTO> listForSub(int userID) {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String SELECT_PREFERENCE_QUERY = "SELECT * FROM Subscriptions WHERE userID = ?";
        List<SubscriptionsDTO> subList = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_PREFERENCE_QUERY);
            prepQuery.setInt(1, userID);
            rs = prepQuery.executeQuery();

            while (rs.next()) {
                SubscriptionsDTO subscription = new SubscriptionsDTO();
                subscription.setId(rs.getInt("id"));
                subscription.setUserID(rs.getInt("userID"));
                subscription.setPhoneNum(rs.getString("phoneNum"));
                subscription.setCommunicationMethod(rs.getString("communicationMethod"));
                subscription.setFoodPreferences(rs.getString("foodPreferences"));
                subList.add(subscription);
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
        return subList;
    }

    public static List<FoodItemsDTO> listForPref(int userID) {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String SELECT_ALL_QUERY = "SELECT f.* "
                + "FROM FoodItems f "
                + "JOIN Users u ON f.userID = u.id "
                + "JOIN Subscriptions s ON s.userID = ? "
                + "WHERE f.price > 0  "
                + "  AND f.isSurplus = true  "
                + "  AND u.location = (SELECT location FROM Users WHERE id = ?) "
                + "  AND f.quantity > 0"
                + "  AND f.foodPreferences = s.foodPreferences; ";
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            prepQuery.setInt(1, userID);
            prepQuery.setInt(2, userID);
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

    public static List<FoodItemsDTO> listForPrefChar(int userID) {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String SELECT_ALL_QUERY = "SELECT f.* "
                + "FROM FoodItems f "
                + "JOIN Users u ON f.userID = u.id "
                + "JOIN Subscriptions s ON s.userID = ? "
                + "WHERE f.price = 0  "
                + "  AND f.isSurplus = true  "
                + "  AND u.location = (SELECT location FROM Users WHERE id = ?) "
                + "  AND f.quantity > 0"
                + "  AND f.foodPreferences = s.foodPreferences; ";
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
            prepQuery.setInt(1, userID);
            prepQuery.setInt(2, userID);
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

    public static boolean claimItem(int quanity, int itemID) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement prepQuery = null;
        String QUERY_STRING = "UPDATE FoodItems"
                + " SET quantity = quantity - ?"
                + " WHERE id = ?;";
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(QUERY_STRING);
            prepQuery.setInt(1, quanity);
            prepQuery.setInt(2, itemID);
            int affectedRows = prepQuery.executeUpdate();
            if (affectedRows > 0) {
                isUpdated = true; // Update was successful
            }

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
        return isUpdated;
    }

    public static List<FoodItemsDTO> listForLocation(int userID) {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String SELECT_ALL_QUERY = "SELECT f.* "
                + "FROM FoodItems f "
                + "JOIN Users u ON f.userID = u.id "
                + "WHERE f.price > 0 AND f.isSurplus = true "
                + "AND f.quantity > 0 "
                + "AND u.location = (SELECT location FROM Users WHERE id = ?)";
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
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
    
    public static List<FoodItemsDTO> listForLocationChar(int userID) {
        Connection connection = null;
        PreparedStatement prepQuery = null;
        ResultSet rs = null;
        String SELECT_ALL_QUERY = "SELECT f.* "
                + "FROM FoodItems f "
                + "JOIN Users u ON f.userID = u.id "
                + "WHERE f.price = 0 AND f.isSurplus = true "
                + "AND f.quantity > 0 "
                + "AND u.location = (SELECT location FROM Users WHERE id = ?)";
        List<FoodItemsDTO> foodItems = new ArrayList<>();
        try {
            connection = DBConnection.getInstance().getConnection();
            prepQuery = connection.prepareStatement(SELECT_ALL_QUERY);
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

    public static boolean addUser(String name, String email,
            String password, String userType, String location) {
        if (validUser(name, email, password, userType, location)) {
            UserDTO user = new UserDTO(name, email, password, userType, location);
            UserDAO userDAO = new UserDAO();
            int results = userDAO.insertUser(user);
            if (results == 0) {
                Logger.log("Failed to add user. Validation failed for: name=" + name + ", email=" + email);

            } else {
                Logger.log("added" + name + ", email=" + email);

            }
            return true;
        } else {
            Logger.log("Failed to add user. Validation failed for: name=" + name + ", email=" + email);
            return false;
        }

    }

    public static boolean addFoodItem(int userID, String name,
            int quantity, LocalDate expirationDate, double price, Timestamp startDate,
            Timestamp endDate, String foodType, String foodPreferences) {
        if (isValidFoodItem(userID, name, quantity, expirationDate, price, startDate, endDate, foodType, foodPreferences)) {
            FoodItemsDTO foodItem = new FoodItemsDTO(userID, name, quantity, expirationDate, price, foodType);
            FoodItemsDAO foodItemsDAO = new FoodItemsDAO();
            foodItemsDAO.addFoodItem(foodItem);
            return true;
        } else {
            Logger.log("Failed to add food item. Validation failed for: userID=" + userID + ", name=" + name);
            return false;
        }
    }

    public static boolean addSubscription(int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        if (isValidSubscription(userID, phoneNum, communicationMethod, foodPreferences)) {
            SubscriptionsDTO subscription = new SubscriptionsDTO(userID, phoneNum, communicationMethod, foodPreferences);
            SubscriptionsDAO subscriptionsDAO = new SubscriptionsDAO();
            subscriptionsDAO.addSubscription(subscription);
            return true;
        } else {
            Logger.log("Failed to add subscription. Validation failed for: userID=" + userID + ", phoneNum=" + phoneNum);
            return false;
        }
    }

    public static boolean addUserQuestion(int questionID, String email,
            String answer) {
        if (isValidUserQuestion(questionID, email, answer)) {
            UserQuestionsDTO userQuestion = new UserQuestionsDTO(questionID, email, answer);
            UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();
            userQuestionsDAO.addUserQuestion(userQuestion);
            return true;
        } else {
            Logger.log("Failed to add user question. Validation failed for: questionID=" + questionID + ", email=" + email);
            return false;
        }
    }

    public static boolean addClaimsPurchase(int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        if (isValidClaimsPurchase(foodItemID, quantity, userID, claimedAt)) {
            Claims_PurchaseDTO claimsPurchase = new Claims_PurchaseDTO(foodItemID, quantity, userID, claimedAt);
            ClaimsPurchaseDAO claimsPurchaseDAO = new ClaimsPurchaseDAO();
            claimsPurchaseDAO.addClaim(claimsPurchase);
            return true;
        } else {
            Logger.log("Failed to add claims purchase. Validation failed for: foodItemID=" + foodItemID + ", quantity=" + quantity);
            return false;
        }
    }

    private static boolean isValidClaimsPurchase(int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        boolean isValid
                = isValidFoodItemId(foodItemID)
                && isValidQuantity(quantity)
                && isValidUserId(userID)
                && isValidClaimedAt(claimedAt);
        if (!isValid) {
            Logger.log("Claims Purchase Validation Failed: foodItemID=" + foodItemID + ", quantity=" + quantity + ", userID=" + userID + ", claimedAt=" + claimedAt);
        }
        return isValid;
    }

    private static boolean isValidFoodItem(int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodType, String foodPreferences) {
        boolean isValid
                = isValidUserID(userID)
                && isValidName(name)
                && isValidQuantity(quantity)
                && isValidExpirationDate(expirationDate)
                && isValidPrice(price)
                && isValidStartDate(startDate)
                && isValidEndDate(startDate, endDate)
                && isValidFoodType(foodType);
        if (!isValid) {
            Logger.log("Food Item Validation Failed: userID=" + userID + ", name=" + name);
        }
        return isValid;
    }

    private static boolean isValidFoodItemId(int foodItemID) {
        boolean isValid = foodItemID > 0;
        if (!isValid) {
            Logger.log("Invalid Food Item ID: " + foodItemID);
        }
        return isValid;
    }

    private static boolean isValidQuantity(int quantity) {
        boolean isValid = quantity >= 0;
        if (!isValid) {
            Logger.log("Invalid Quantity: " + quantity);
        }
        return isValid;
    }

    private static boolean isValidUserId(int userID) {
        boolean isValid = userID > 0;
        if (!isValid) {
            Logger.log("Invalid User ID: " + userID);
        }
        return isValid;
    }

    private static boolean isValidClaimedAt(Timestamp claimedAt) {
        boolean isValid = claimedAt != null;
        if (!isValid) {
            Logger.log("Invalid Claimed At Timestamp: " + claimedAt);
        }
        return isValid;
    }

    private static boolean isValidName(String name) {
        boolean isValid = name != null && !name.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Name: " + name);
        }
        return isValid;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        boolean isValid = email != null && pattern.matcher(email).matches();
        if (!isValid) {
            Logger.log("Invalid Email: " + email);
        }
        return isValid;
    }

    private static boolean isValidPassword(String password) {
        boolean isValid = password != null && password.length() >= 8;
        if (!isValid) {
            Logger.log("Invalid Password: " + password);
        }
        return isValid;
    }

    private static boolean isValidUserType(String userType) {
        boolean isValid = userType != null;
        if (!isValid) {
            Logger.log("Invalid UserType: " + userType);
        }
        return isValid;
    }

    private static boolean isValidLocation(String location) {
        boolean isValid = location != null && !location.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Location: " + location);
        }
        return isValid;
    }

    private static boolean validUser(String name, String email, String password, String userType, String location) {
        boolean isValid
                = isValidName(name)
                && isValidEmail(email)
                && isValidPassword(password)
                && isValidUserType(userType)
                && isValidLocation(location);
        if (!isValid) {
            Logger.log("User Validation Failed: name=" + name + ", email=" + email);
        }
        return isValid;
    }

    private static boolean isValidExpirationDate(LocalDate expirationDate) {
        boolean isValid = expirationDate != null && expirationDate.isAfter(LocalDate.now());
        if (!isValid) {
            Logger.log("Invalid Expiration Date: " + expirationDate);
        }
        return isValid;
    }

    private static boolean isValidPrice(double price) {
        boolean isValid = price >= 0;
        if (!isValid) {
            Logger.log("Invalid Price: " + price);
        }
        return isValid;
    }

    private static boolean isValidStartDate(Timestamp startDate) {
        boolean isValid = startDate != null;
        if (!isValid) {
            Logger.log("Invalid Start Date: " + startDate);
        }
        return isValid;
    }

    private static boolean isValidEndDate(Timestamp startDate, Timestamp endDate) {
        boolean isValid = endDate != null && startDate != null && endDate.after(startDate);
        if (!isValid) {
            Logger.log("Invalid End Date: " + endDate + ", Start Date: " + startDate);
        }
        return isValid;
    }

    private static boolean isValidFoodType(String foodType) {
        boolean isValid = foodType != null && !foodType.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Food Type: " + foodType);
        }
        return isValid;
    }

    private static boolean isValidSubscription(int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        boolean isValid
                = isValidUserID(userID)
                && isValidCommunicationMethod(communicationMethod)
                && isValidFoodPreferences(foodPreferences);
        if (!isValid) {
            Logger.log("Subscription Validation Failed: userID=" + userID + ", phoneNum=" + phoneNum);
        }
        return isValid;
    }

    private static boolean isValidUserID(int userID) {
        boolean isValid = userID > 0;
        if (!isValid) {
            Logger.log("Invalid UserID: " + userID);
        }
        return isValid;
    }

    private static boolean isValidPhoneNum(String phoneNum) {
        String phoneRegex = "^\\+?[0-9]{10,13}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        boolean isValid = phoneNum != null && pattern.matcher(phoneNum).matches();
        if (!isValid) {
            Logger.log("Invalid Phone Number: " + phoneNum);
        }
        return isValid;
    }

    private static boolean isValidCommunicationMethod(String communicationMethod) {
        boolean isValid = communicationMethod != null && !communicationMethod.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Communication Method: " + communicationMethod);
        }
        return isValid;
    }

    private static boolean isValidFoodPreferences(String foodPreferences) {
        boolean isValid = foodPreferences != null && !foodPreferences.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Food Preferences: " + foodPreferences);
        }
        return isValid;
    }

    private static boolean isValidUserQuestion(int questionID, String email, String answer) {
        boolean isValid
                = isValidQuestionID(questionID)
                && isValidEmail(email)
                && isValidAnswer(answer);
        if (!isValid) {
            Logger.log("User Question Validation Failed: questionID=" + questionID + ", email=" + email);
        }
        return isValid;
    }

    private static boolean isValidQuestionID(int questionID) {
        boolean isValid = questionID > 0;
        if (!isValid) {
            Logger.log("Invalid Question ID: " + questionID);
        }
        return isValid;
    }

    private static boolean isValidAnswer(String answer) {
        boolean isValid = answer != null && !answer.trim().isEmpty();
        if (!isValid) {
            Logger.log("Invalid Answer: " + answer);
        }
        return isValid;
    }
}
