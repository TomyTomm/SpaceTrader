package com.amath.spacetrader.entity;

import java.io.Serializable;

public class Ship implements Serializable {
    private ShipType shipType;
    private int holdSize;
    private Coordinate location;    // Do we need to have a coordinate location for our ships? I don't think so
    private double fuel;
    private static final double STARTING_FUEL = 10000;   //random number, should change

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        holdSize = shipType.getCargoCapacity();
        fuel = STARTING_FUEL;
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

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }
}
