package com.amath.spacetrader.entity;

public class Good {
    private int quantity;
    private GoodType type;

    public Good(GoodType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public void buy(int amount) {
        quantity += amount;
    }

    public void sell(int amount) {
        quantity -= amount;
    }

    public GoodType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int calculatePrice(TechLevel minT) {
        return 0;
    }
}
