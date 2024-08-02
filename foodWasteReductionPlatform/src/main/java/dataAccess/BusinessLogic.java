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

public class BusinessLogic {

    public static boolean addUser(int id, String name, String email,
            String password, UserDTO.UserType userType, String location) {
        if (validUser(id, name, email, password, userType, location)) {
            UserDTO user = new UserDTO(id, name, email, password, userType, location);
            UserDAO userDAO = new UserDAO();
            userDAO.insertUser(user);
            return true;
        } else {
            Logger.log("Failed to add user. Validation failed for: id=" + id + ", name=" + name + ", email=" + email);
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
            Logger.log("Failed to add food item. Validation failed for: id=" + id + ", userID=" + userID + ", name=" + name);
            return false;
        }
    }

    public static boolean addSubscription(int id, int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        if (isValidSubscription(id, userID, phoneNum, communicationMethod, foodPreferences)) {
            SubscriptionsDTO subscription = new SubscriptionsDTO(id, userID, phoneNum, communicationMethod, foodPreferences);
            SubscriptionsDAO subscriptionsDAO = new SubscriptionsDAO();
            subscriptionsDAO.addSubscription(subscription);
            return true;
        } else {
            Logger.log("Failed to add subscription. Validation failed for: id=" + id + ", userID=" + userID + ", phoneNum=" + phoneNum);
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
            Logger.log("Failed to add user question. Validation failed for: id=" + id + ", questionID=" + questionID + ", email=" + email);
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
            Logger.log("Failed to add claims purchase. Validation failed for: id=" + id + ", foodItemID=" + foodItemID + ", quantity=" + quantity);
            return false;
        }
    }

    private static boolean isValidClaimsPurchase(int id, int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        boolean isValid = isValidId(id)
                && isValidFoodItemId(foodItemID)
                && isValidQuantity(quantity)
                && isValidUserId(userID)
                && isValidClaimedAt(claimedAt);
        if (!isValid) {
            Logger.log("Claims Purchase Validation Failed: id=" + id + ", foodItemID=" + foodItemID + ", quantity=" + quantity + ", userID=" + userID + ", claimedAt=" + claimedAt);
        }
        return isValid;
    }

    private static boolean isValidFoodItem(int id, int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodType) {
        boolean isValid = isValidId(id)
                && isValidUserID(userID)
                && isValidName(name)
                && isValidQuantity(quantity)
                && isValidExpirationDate(expirationDate)
                && isValidPrice(price)
                && isValidStartDate(startDate)
                && isValidEndDate(startDate, endDate)
                && isValidFoodType(foodType);
        if (!isValid) {
            Logger.log("Food Item Validation Failed: id=" + id + ", userID=" + userID + ", name=" + name);
        }
        return isValid;
    }

    private static boolean isValidId(int id) {
        boolean isValid = id > 0;
        if (!isValid) {
            Logger.log("Invalid ID: " + id);
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

    private static boolean isValidUserType(UserType userType) {
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

    private static boolean validUser(int id, String name, String email, String password, UserDTO.UserType userType, String location) {
        boolean isValid = isValidId(id)
                && isValidName(name)
                && isValidEmail(email)
                && isValidPassword(password)
                && isValidUserType(userType)
                && isValidLocation(location);
        if (!isValid) {
            Logger.log("User Validation Failed: id=" + id + ", name=" + name + ", email=" + email);
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

    private static boolean isValidSubscription(int id, int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        boolean isValid = isValidId(id)
                && isValidUserID(userID)
                && isValidPhoneNum(phoneNum)
                && isValidCommunicationMethod(communicationMethod)
                && isValidFoodPreferences(foodPreferences);
        if (!isValid) {
            Logger.log("Subscription Validation Failed: id=" + id + ", userID=" + userID + ", phoneNum=" + phoneNum);
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

    private static boolean isValidUserQuestion(int id, int questionID, String email,
            int userID, String answer) {
        boolean isValid = isValidId(id)
                && isValidQuestionID(questionID)
                && isValidEmail(email)
                && isValidUserID(userID)
                && isValidAnswer(answer);
        if (!isValid) {
            Logger.log("User Question Validation Failed: id=" + id + ", questionID=" + questionID + ", email=" + email);
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
