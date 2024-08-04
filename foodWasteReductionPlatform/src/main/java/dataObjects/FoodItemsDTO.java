/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObjects;

import java.time.LocalDate;
import java.sql.Timestamp;

/**
 *
 * @author Josh Barrett
 */
public class FoodItemsDTO {
    
    private int id;
    private int userID;
    private String name;
    private int quantity;
    private LocalDate expirationDate;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private String foodPreferences;

    public FoodItemsDTO() {}

    public FoodItemsDTO(int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodPreferences) {
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.foodPreferences = foodPreferences;
    }

    public FoodItemsDTO(int id, int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodPreferences) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.foodPreferences = foodPreferences;
    }
    
    

    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }
}

