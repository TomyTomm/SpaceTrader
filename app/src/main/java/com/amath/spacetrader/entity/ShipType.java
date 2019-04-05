package com.amath.spacetrader.entity;

public enum ShipType {
    GNAT(15);

    private final int cargoCapacity;
    ShipType(int holdSize) {
        cargoCapacity = holdSize;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }
}