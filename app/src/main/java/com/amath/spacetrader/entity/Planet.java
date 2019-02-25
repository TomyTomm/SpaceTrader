package com.amath.spacetrader.entity;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Planet extends SpaceBody{

    private TechLevel techLevel;
    private ResourceLevel resourceLevel;

    private static LinkedList<String> AVAILABLE_PLANET_NAMES;
    private static Set<String> USED_PLANET_NAMES = new HashSet<>();

    public Planet() {

        this(0, new HashSet<Planet>());


    }

    public Planet(int sunSize, Set<Planet> planets) {

        // Get name from the list of available planet names. This was randomized on start up, so everything should be ok.
        // Removes a name from the list to ensure that AVAILABLE_PLANET_NAMES will never have anything from USED_PLANET_NAMES.

        name = null;
        while (name == null) {
            Log.d("pnames", String.valueOf(AVAILABLE_PLANET_NAMES.size()));
            if (AVAILABLE_PLANET_NAMES.isEmpty()) {
                throw new RuntimeException("App does not handle when AVAILABLE_PLANET_NAMES becomes empty");
            }
            Log.d("initialization", "making planets: inside Planet()");
            String temp = AVAILABLE_PLANET_NAMES.pop();
            if (!USED_PLANET_NAMES.contains(temp)) {
                name = temp;
                break;
            }
        }


        // Makes a completely random coordinate with respect to Solar system size.

        Random rand = new Random();
        radius = Math.random() * 8;
        while (location == null) {
            double x = ((rand.nextBoolean() ? -1 : 1) * (Math.random() * (SolarSystem.BOUNDS.getY() / 2 - sunSize / 2)) + sunSize/2) + SolarSystem.BOUNDS.getY() / 2;
            double y = ((rand.nextBoolean() ? -1 : 1) * (Math.random() * (SolarSystem.BOUNDS.getY() / 2 - sunSize / 2)) + sunSize/2) + SolarSystem.BOUNDS.getY() / 2;
//            location = new Coordinate(x, y);

            Coordinate temp = new Coordinate(x, y);
            boolean overlapping = false;
            for (Planet planet: planets) {
                if (overlap(temp, planet)) {
                    overlapping = true;
                    break;
                }
            }
            if (!overlapping) this.location = temp;
            else if (radius > 1) {
                radius *= 0.9;      //decrease size of radius to decrease future chance of overlapping.
            }
        }


        techLevel = TechLevel.values()[(int) Math.random() * 12];

        resourceLevel = ResourceLevel.values()[(int) (Math.random() * 12)];

        Log.d("initialization", "making planets: inside Planet()");
    }

    public Planet(String name, Coordinate location, int techLevel, int resourceLevel) {
        this(name, location, TechLevel.values()[techLevel], ResourceLevel.values()[resourceLevel]);
    }

    public Planet(String name, Coordinate location, TechLevel tech, ResourceLevel resource) {

        USED_PLANET_NAMES.add(name);

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

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public ResourceLevel getResourceLevel() {
        return resourceLevel;
    }

    public double getRadius() { return this.radius; }

    /**
     * Called at startup by Model. Initalizes the LinkedList
     * with a randomized list of possible names.
     *
     * Using LinkedList because .removeFirst() / .pop() have
     * efficiency of O(1).
     *
     * @param availablePlanetNames
     */
    public static void setAvailablePlanetNames(List<String> availablePlanetNames) {
        AVAILABLE_PLANET_NAMES = (LinkedList<String>) availablePlanetNames;
    }

    /**
     * Called if and when player wants to create a custom planet
     * Will check whether or not the name has already been used.
     *
     * @param name
     * @return whether or not the name has been used
     */
    public static boolean isValidName(String name) {
        return USED_PLANET_NAMES.contains(name);
    }

    @Override
    public String toString() {
        return String.format("Planet %s:\n" +
                "Location with respect to sun: (%f, %f)\n" +
                "Radius:\t\t%f%n" +
                "Technology level:%s\n" +
                "Resources:%s",
                name, location.getX(), location.getY(), this.getRadius(),
                techLevel.toString(), resourceLevel.toString());
    }
}
