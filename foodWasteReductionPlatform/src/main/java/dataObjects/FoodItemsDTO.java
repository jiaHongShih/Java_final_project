package dataObjects;

import java.time.LocalDate;

public class FoodItemsDTO {
    
    private int id;
    private int userID;
    private String name;
    private int quantity;
    private LocalDate expirationDate;
    private double price;
    private String foodPreferences;
    private boolean isSurplus;

    public FoodItemsDTO(int id, int userID, String name, int quantity, LocalDate expirationDate, double price, String foodPreferences, boolean isSurplus) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.foodPreferences = foodPreferences;
        this.isSurplus = isSurplus;
    }

    public FoodItemsDTO() {}

    public FoodItemsDTO(int userID, String name, int quantity, LocalDate expirationDate, double price, String foodPreferences) {
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.price = price;
        this.foodPreferences = foodPreferences;
    }

    public boolean isSurplus() {
        return isSurplus;
    }

    public void setSurplus(boolean isSurplus) {
        this.isSurplus = isSurplus;
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

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }
}
