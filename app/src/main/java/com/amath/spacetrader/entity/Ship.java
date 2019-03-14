package com.amath.spacetrader.entity;

import java.io.Serializable;

public class Ship implements Serializable {
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
