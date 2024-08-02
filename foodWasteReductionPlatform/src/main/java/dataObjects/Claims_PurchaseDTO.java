/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataObjects;

import java.sql.Timestamp;

/**
 *
 * @author Josh Barrett
 */
public class Claims_PurchaseDTO {

    private int foodItemID;
    private int quantity;
    private int userID;
    private Timestamp claimedAt;

    public Claims_PurchaseDTO() {
    }

    // Parameterized constructor
    public Claims_PurchaseDTO(int foodItemID, int quantity, int userID, Timestamp claimedAt) {
        this.foodItemID = foodItemID;
        this.quantity = quantity;
        this.userID = userID;
        this.claimedAt = claimedAt;
    }



    public int getFoodItemID() {
        return foodItemID;
    }

    public void setFoodItemID(int foodItemID) {
        this.foodItemID = foodItemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Timestamp getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(Timestamp claimedAt) {
        this.claimedAt = claimedAt;
    }
}
