package com.amath.spacetrader.entity;

public class Good {
    private int quantity;
    private String type;

    public Good(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public void buy(int amount) {
        quantity += amount;
    }

    public void sell(int amount) {
        quantity -= amount;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
