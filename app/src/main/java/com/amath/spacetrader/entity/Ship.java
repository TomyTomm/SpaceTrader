package com.amath.spacetrader.entity;

public class Ship {
    private ShipType shipType;
    private int holdSize;
    private Coordinate location;    // Do we need to have a coordinate location for our ships? I don't think so

    public Ship(ShipType shipType) {
        shipType = shipType;
        holdSize = shipType.getCargoCapacity();
    }

    public int getHoldSize() {
        return holdSize;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Coordinate getLocation() {
        return location;
    }
}
