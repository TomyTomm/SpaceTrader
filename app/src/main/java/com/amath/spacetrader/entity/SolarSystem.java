package com.amath.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SolarSystem extends SpaceBody implements Serializable {

    private Set<Planet> planets;
    private Sun sun = new Sun();

    public static final Coordinate BOUNDS = new Coordinate(100, 100);

    private static int allPlanetsNum = 0;
    private String name;
    private Coordinate location;
    private double radius;

    public SolarSystem(String name, Set<Planet> planets, Coordinate location, double radius) {
        this.name = name;
        this.planets = planets;
        this.location = location;
        this.radius = radius;

    }

    public SolarSystem(String name, List<SolarSystem> systems) {
        this.name = name;
        this.planets = instantiatePlanets(systems);
        this.location = setLocation(systems);

    }

    public SolarSystem() {
        this("", new ArrayList<SolarSystem>());
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
//        Log.d("initialization", String.format("making planets (inside addPlanet()):\t%d", planets.size()));
        planets.add(new Planet(sun.getSize(), this.planets, this));
    }

    private void addPlanet(Set<Planet> planets) {
        Log.d("initialization", String.format("making planets (inside addPlanet()):\t%d", planets.size()));
        planets.add(new Planet(sun.getSize(), planets, this));
    }

    public int getSunSize() { return sun.getSize(); }

    public double getRadius() { return this.radius; }

    private Set<Planet> instantiatePlanets(List<SolarSystem> systems) {
        Set<Planet> planets = new HashSet<>();
        Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        for (int i = 0; i < (int)(Math.random() * 5 + 5); i++) {
            addPlanet(planets);
            Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        }
        return planets;
        //add some planets to the set
    }

    private Coordinate setLocation(List<SolarSystem> systems) {
        radius = Math.random() * 8 + 2;
        Coordinate location = null;
        while (location == null) {
//            Log.d("what?", String.valueOf(systems.size()));
            Coordinate temp = new Coordinate(Math.random() * Universe.BOUNDS.getX(), Math.random() * Universe.BOUNDS.getY());
            boolean overlapping = false;
            for (SolarSystem system: systems) {
//                Log.d("what?", "I thought this wasn't empty!");
                if (overlap(temp, system)) {
                    overlapping = true;
                    break;
                }
            }
            if (!overlapping) location = temp;
            else if (radius > 1) {
                radius *= 0.9;      //decrease size of radius to decrease future chance of overlapping.
            }
        }
        return location;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("Solar System:\t\t%s\n" +
                        "Location:(%f, %f)\n" +
                        "Radius:\t\t\t%f\n",
                name, location.getX(),
                location.getY(), radius));
        for (Planet planet: this.getPlanets()) {
            str.append(String.format("Planet:\t\t%s\n", planet.toString()));
        }
        return str.toString();
    }

    public Sun getSun() {
        return sun;
    }

    public boolean equals(SolarSystem other) {
        return this.getName().equals(other.getName());
    }
}
