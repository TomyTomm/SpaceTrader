package com.amath.spacetrader.entity;

import android.util.Log;

import com.amath.spacetrader.model.Model;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Universe {

    private Set<SolarSystem> solarSystems;

    public static final Coordinate BOUNDS = new Coordinate(150, 100);
    private Planet currentPlanet;

    public Universe() {
        solarSystems = new HashSet<>();

        // Create standard SolarSystem
        // Begin by making a set of planets
        Set<Planet> planets = new HashSet<>();
        Planet genesis = new Planet("Genesis", new Coordinate(80, 12), 5, 5, 10);
        Planet hera = new Planet("Hera", new Coordinate(20, 20), 2, 5, 5);
        Planet athena = new Planet("Athena", new Coordinate(50, 80), 4, 3, 5);
        Planet ares = new Planet("Ares", new Coordinate(80, 70), 1, 1, 8);

        planets.add(genesis);
        planets.add(hera);
        planets.add(athena);
        planets.add(ares);

        SolarSystem beginning = new SolarSystem("Humble Beginnings", planets, new Coordinate(75.0, 50.0), 15);
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
            Log.d("initialization", String.format("making solarsystem:\t%d", solarSystems.size()));
            solarSystems.add(new SolarSystem(String.format("%d", i), this.getSolarSystems()));
        }
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        this.currentPlanet = currentPlanet;
    }

    public Set<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

}
