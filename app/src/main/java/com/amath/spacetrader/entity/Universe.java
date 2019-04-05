package com.amath.spacetrader.entity;

import android.util.Log;

import com.amath.spacetrader.model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Universe implements Serializable {

    private final List<SolarSystem> solarSystems;

    public static final Coordinate BOUNDS = new Coordinate(150, 100);
    private Planet currentPlanet;

    public Universe() {
        solarSystems = new ArrayList<>();

        // Create standard SolarSystem
        // Begin by making a set of planets
        Set<Planet> planets = new HashSet<>();
        final int eighty = 80;
        final int twelve = 12;
        final int twenty = 20;
        final int fifty = 50;
        final int seventy = 70;
        Planet genesis = new Planet("Genesis", new Coordinate(eighty, twelve),
                4, 5, 10);
        Planet hera = new Planet("Hera", new Coordinate(twenty, twenty),
                2, 5, 5);
        Planet athena = new Planet("Athena", new Coordinate(fifty, eighty),
                4, 3, 5);
        Planet ares = new Planet("Ares", new Coordinate(eighty, seventy),
                1, 1, 8);

        planets.add(genesis);
        planets.add(hera);
        planets.add(athena);
        planets.add(ares);

        final double seventyFive = 75.0;
        final double fifty0 = 50.0;
        final int fifteen = 15;

        SolarSystem beginning = new SolarSystem("Humble Beginnings", planets,
                new Coordinate(seventyFive, fifty0), fifteen);
        genesis.setSolarSystem(beginning);
        hera.setSolarSystem(beginning);
        athena.setSolarSystem(beginning);
        ares.setSolarSystem(beginning);

        solarSystems.add(beginning);

        this.currentPlanet = genesis;

        initializeSolarSystems();
    }

    private void initializeSolarSystems() {
        Log.d("initialization", "Initializing solar systems");
        double random = Math.random() * 5 + 10;
        Log.d("initialization", String.valueOf(random));
        for (int i = 0; i < (int) random; i++) {
            Log.d("initialization", String.format(Locale.US,
                    "making solarsystem:\t%d", solarSystems.size()));
            solarSystems.add(new SolarSystem(String.format(Locale.US,
                    "%d", i), this.getSolarSystems()));
        }
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

}
