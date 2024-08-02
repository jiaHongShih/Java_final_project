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

    private int userID;
    private String name;
    private int quantity;
    private LocalDate ExpirationDate;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private String foodType;

    public FoodItemsDTO(){}
    public FoodItemsDTO(int userID, String name, int quantity, LocalDate expirationDate, double price, Timestamp startDate, Timestamp endDate, String foodType) {
        this.userID = userID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.foodType = foodType;
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
        return ExpirationDate;
    }

    public void setExpirationDate(LocalDate ExpirationDate) {
        this.ExpirationDate = ExpirationDate;
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

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
