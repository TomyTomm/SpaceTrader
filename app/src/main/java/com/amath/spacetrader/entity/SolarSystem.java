package com.amath.spacetrader.entity;

import java.util.HashSet;
import java.util.Set;

public class SolarSystem {
    private String name;
    private Set<Planet> planets;
    private Coordinate location;

    public SolarSystem(String name) {
        this.name = name;
        planets = new HashSet<>();
        //set a location in the future
        location = null;
        //add some planets to the set
    }

    public String getName() {
        return name;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public Coordinate getLocation() {
        return location;
    }
}
