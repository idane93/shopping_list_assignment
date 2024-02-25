package com.example.shoppinglistassignment.Classes;

import androidx.annotation.NonNull;

public class ShoppingItem {
    private String itemName;
    private int quantity;  // Change the type to int

    public ShoppingItem(String itemName, int quantity) {
        this.itemName = itemName;

        // Ensure quantity is positive, set to 0 if it's negative
        this.quantity = Math.max(quantity, 0);
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    // Other methods as needed

    @NonNull
    @Override
    public String toString() {
        return "Item name:" + itemName + " , Amount:" + quantity;
    }
}


