package com.amath.spacetrader.entity;

public class Ship {
    ShipType shipType;
    int holdSize;
    Coordinate location;

    public Ship(ShipType ship) {
        shipType = ship;
        holdSize = ship.getCargoCapacity();
    }

    public int getHoldSize() {
        return holdSize;
    }

    public ShipType getShipType() {
        return shipType;
    }
}
