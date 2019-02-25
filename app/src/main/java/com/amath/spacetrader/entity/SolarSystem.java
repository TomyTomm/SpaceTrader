package com.amath.spacetrader.entity;

import android.util.Log;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SolarSystem extends SpaceBody {

    private Set<Planet> planets;
    private Sun sun;

    public static final Coordinate BOUNDS = new Coordinate(100, 100);

    private static int allPlanetsNum = 0;

    public SolarSystem(String name, Set<SolarSystem> systems) {
        this.name = name;
        planets = new HashSet<>();
        this.sun = new Sun();
        Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        for (int i = 0; i < (int)(Math.random() * 5 + 5); i++) {
            addPlanet();
            Log.d("initialization", String.format("making planets:\t%d", planets.size()));
        }
        radius = Math.random() * 8;
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
            if (!overlapping) this.location = temp;
            else if (radius > 1) {
                radius *= 0.9;      //decrease size of radius to decrease future chance of overlapping.
            }
        }
        //add some planets to the set
    }

    public SolarSystem() {
        this("", new HashSet<SolarSystem>());
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
        planets.add(new Planet(sun.getSize(), this.planets));
    }

    public int getSunSize() { return sun.getSize(); }

    public double getRadius() { return this.radius; }



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

}
