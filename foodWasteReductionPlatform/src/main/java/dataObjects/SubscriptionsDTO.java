/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObjects;

/**
 *
 * @author Josh Barrett
 */
public class SubscriptionsDTO {
    private int id;
    private int userID;
    private String phoneNum;
    private String CommunicationMethod;
    private String foodPreferences;

    public SubscriptionsDTO() {
    }

    public SubscriptionsDTO(int userID, String phoneNum,
            String communicationMethod, String foodPreferences) {
        this.CommunicationMethod = communicationMethod;
        this.foodPreferences = foodPreferences;
        this.phoneNum = phoneNum;
        this.userID = userID;
    }

    public SubscriptionsDTO(int id, int userID, String phoneNum, String CommunicationMethod, String foodPreferences) {
        this.id = id;
        this.userID = userID;
        this.phoneNum = phoneNum;
        this.CommunicationMethod = CommunicationMethod;
        this.foodPreferences = foodPreferences;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCommunicationMethod() {
        return CommunicationMethod;
    }

    public void setCommunicationMethod(String CommunicationMethod) {
        this.CommunicationMethod = CommunicationMethod;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

}
