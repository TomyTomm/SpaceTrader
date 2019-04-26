package com.amath.spacetrader.entity;

public enum Weapon {
    NONE(0),
    KNIFE(0),
    CANNON(1),
    PROTON_TORPEDOES(2);

    final int cost;

    Weapon(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
