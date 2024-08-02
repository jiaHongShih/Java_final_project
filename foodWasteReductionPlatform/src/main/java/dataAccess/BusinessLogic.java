/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import dataObjects.Claims_PurchaseDTO;
import dataObjects.FoodItemsDTO;
import dataObjects.SubscriptionsDTO;
import dataObjects.UserDTO;
import dataObjects.UserDTO.UserType;
import dataObjects.UserQuestionsDTO;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 *
 * @author Nicholas Jacques
 */
public class BusinessLogic {

    public static boolean addUser(int id, String name, String email,
            String password, UserDTO.UserType userType, String location) {
        if (validUser(id, name, email, password, userType, location)) {
            UserDTO user = new UserDTO(id, name, email, password, userType, location);
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(user);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addFoodItem(int id, int userID, String name,
            int quantity, LocalDate expirationDate, double price, Timestamp startDate,
            Timestamp endDate, String foodType) {
        if (isValidFoodItem(id, userID, name, quantity, expirationDate, price, startDate, endDate, foodType)) {
            FoodItemsDTO foodItem = new FoodItemsDTO(id, userID, name, quantity, expirationDate, price, startDate, endDate, foodType);
            FoodItemsDAO foodItemsDAO = new FoodItemsDAO();
            foodItemsDAO.addFoodItem(foodItem);
            return true;
        } else {
            return false;
        }
    }

//    isValidSubscription
    public static boolean addSubscription(int id, int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        if (isValidSubscription(id, userID, phoneNum, communicationMethod, foodPreferences)) {
            SubscriptionsDTO subscription = new SubscriptionsDTO(id, userID, phoneNum, communicationMethod, foodPreferences);
            SubscriptionsDAO subscriptionsDAO = new SubscriptionsDAO();
            subscriptionsDAO.addSubscription(subscription);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addUserQuestion(int id, int questionID, String email,
            int userID, String answer) {
        if (isValidUserQuestion(id, questionID, email, userID, answer)) {
            UserQuestionsDTO userQuestion = new UserQuestionsDTO(id, questionID, email, userID, answer);
            UserQuestionsDAO userQuestionsDAO = new UserQuestionsDAO();
            userQuestionsDAO.addUserQuestion(userQuestion);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addClaimsPurchase(int id, int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        if (isValidClaimsPurchase(id, foodItemID, quantity, userID, claimedAt)) {
            Claims_PurchaseDTO claimsPurchase = new Claims_PurchaseDTO(id, foodItemID, quantity, userID, claimedAt);
            ClaimsPurchaseDAO claimsPurchaseDAO = new ClaimsPurchaseDAO();
            claimsPurchaseDAO.addClaim(claimsPurchase);
            return true;
        } else {
            return false;
        }
    }

    //private method to validate
    private static boolean isValidClaimsPurchase(int id, int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        return isValidId(id)
                && isValidFoodItemId(foodItemID)
                && isValidQuantity(quantity)
                && isValidUserId(userID)
                && isValidClaimedAt(claimedAt);
    }

    private static boolean isValidId(int id) {
        return id > 0;
    }

    private static boolean isValidFoodItemId(int foodItemID) {
        return foodItemID > 0;
    }

    private static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }

    private static boolean isValidUserId(int userID) {
        return userID > 0;
    }

    private static boolean isValidClaimedAt(Timestamp claimedAt) {
        return claimedAt != null;
    }

    private static boolean isValidFoodItem(int id, int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodType) {
        return isValidId(id)
                && isValidUserID(userID)
                && isValidName(name)
                && isValidQuantity(quantity)
                && isValidExpirationDate(expirationDate)
                && isValidPrice(price)
                && isValidStartDate(startDate)
                && isValidEndDate(startDate, endDate)
                && isValidFoodType(foodType);
    }

    private static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return email != null && pattern.matcher(email).matches();
    }

    private static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }

    private static boolean isValidUserType(UserType userType) {
        return userType != null;
    }

    private static boolean isValidLocation(String location) {
        return location != null && !location.trim().isEmpty();
    }

    private static boolean validUser(int id, String name, String email, String password, UserDTO.UserType userType, String location) {
        return isValidId(id)
                && isValidName(name)
                && isValidEmail(email)
                && isValidPassword(password)
                && isValidUserType(userType)
                && isValidLocation(location);
    }

    private static boolean isValidExpirationDate(LocalDate expirationDate) {
        return expirationDate != null && expirationDate.isAfter(LocalDate.now());
    }

    private static boolean isValidPrice(double price) {
        return price >= 0;
    }

    private static boolean isValidStartDate(Timestamp startDate) {
        return startDate != null;
    }

    private static boolean isValidEndDate(Timestamp startDate, Timestamp endDate) {
        return endDate != null && startDate != null && endDate.after(startDate);
    }

    private static boolean isValidFoodType(String foodType) {
        return foodType != null && !foodType.trim().isEmpty();
    }

    private static boolean isValidSubscription(int id, int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        return isValidId(id)
                && isValidUserID(userID)
                && isValidPhoneNum(phoneNum)
                && isValidCommunicationMethod(communicationMethod)
                && isValidFoodPreferences(foodPreferences);
    }

    private static boolean isValidUserID(int userID) {
        return userID > 0;
    }

    private static boolean isValidPhoneNum(String phoneNum) {
        String phoneRegex = "^\\+?[0-9]{10,13}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return phoneNum != null && pattern.matcher(phoneNum).matches();
    }

    private static boolean isValidCommunicationMethod(String communicationMethod) {
        return communicationMethod != null && !communicationMethod.trim().isEmpty();
    }

    private static boolean isValidFoodPreferences(String foodPreferences) {
        return foodPreferences != null && !foodPreferences.trim().isEmpty();
    }

    private static boolean isValidUserQuestion(int id, int questionID, String email,
            int userID, String answer) {
        return isValidId(id)
                && isValidQuestionID(questionID)
                && isValidEmail(email)
                && isValidUserID(userID)
                && isValidAnswer(answer);
    }

    private static boolean isValidQuestionID(int questionID) {
        return questionID > 0;
    }

    private static boolean isValidAnswer(String answer) {
        return answer != null && !answer.trim().isEmpty();
    }

}
