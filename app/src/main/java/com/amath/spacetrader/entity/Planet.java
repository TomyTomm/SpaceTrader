package com.amath.spacetrader.entity;

public class Planet {
    private String name;
    private Coordinate location;
    private int techLevel;
    private int resourceLevel;

    public Planet(String name, Coordinate location, int techLevel, int resourceLevel) {
        this.name = name;
        this.location = location;
        this.techLevel = techLevel;
        this.resourceLevel = resourceLevel;
    }

    public String getName() {
        return name;
    }

    public Coordinate getLocation() {
        return location;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public int getResourceLevel() {
        return resourceLevel;
    }
}
