package com.amath.spacetrader.entity;

import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SolarSystem {
    private String name;
    private Set<Planet> planets;
    private Coordinate location;
    private Sun sun;

    public static final Coordinate BOUNDS = new Coordinate(100, 100);

    private static int allPlanetsNum = 0;

    public SolarSystem(String name) {
        this.name = name;
        planets = new HashSet<>();
        this.sun = new Sun();
        Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        for (int i = 0; i < (int)(Math.random() * 5 + 5); i++) {
            addPlanet();
            Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        }
        location = null;
        //add some planets to the set
    }

    public SolarSystem() {
        this("");
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

    private void addPlanet() {
        Log.d("initialization", String.format("making planets (inside addPlanet()):\t%d", planets.size()));
        planets.add(new Planet(sun.getSize()));
    }

    public int getSunSize() { return sun.getSize(); }

}
