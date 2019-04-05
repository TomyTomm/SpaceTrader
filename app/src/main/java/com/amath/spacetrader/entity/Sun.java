package com.amath.spacetrader.entity;

import java.io.Serializable;

public class Sun extends SpaceBody {
    private final double radius;
    private static final int MAX_SIZE = 15;
    private static final Coordinate location = new Coordinate(SolarSystem.BOUNDS.getX()/2,
            SolarSystem.BOUNDS.getY()/2);
    public Sun() {
        this((int) (10 + Math.random() * MAX_SIZE));
    }
    public Sun(int size) {
        this.radius = size;
    }

    public int getSize() {
        return (int) radius;
    }

    @Override
    public double getRadius() { return radius; }

    @Override
    public Coordinate getLocation() {
        return location;
    }
}