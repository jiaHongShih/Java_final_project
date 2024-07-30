/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dataRelated;

import java.util.Date;

/**
 *
 * @author JiaHong
 */
public class FoodItemDTO {
    private int id;
    private int userID;
    private String name;
    private int quantity;
    private Date expirationDate;
    private boolean isSurplus;
    private double price;
    
    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean isIsSurplus() {
        return isSurplus;
    }

    public double getPrice() {
        return price;
    }

}
