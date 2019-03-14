package com.amath.spacetrader.entity;

import java.io.Serializable;

public class Sun extends SpaceBody implements Serializable {
    private double radius;
    private static final int MAX_SIZE = 15;
    private static Coordinate location = new Coordinate(SolarSystem.BOUNDS.getX()/2, SolarSystem.BOUNDS.getY()/2);
    public Sun() {
        this((int) (10 + Math.random() * MAX_SIZE));
    }
    public Sun(int size) {
        this.radius = size;
    }

    public int getSize() {
        return (int) radius;
    }

    public double getRadius() { return (double) radius; }

    public Coordinate getLocation() {
        return location;
    }
}