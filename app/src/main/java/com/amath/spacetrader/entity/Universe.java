package com.amath.spacetrader.entity;

import android.util.Log;

import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Universe {

    private Set<SolarSystem> solarSystems;

    public static final Coordinate BOUNDS = new Coordinate(150, 100);

    public Universe() {
        solarSystems = new HashSet<>();
        initializeSolarSystems();
    }

    private void initializeSolarSystems() {
        Log.d("initialization", "Initializing solar systems");
        double random = Math.random() * 5 + 10;
        Log.d("initialization", String.valueOf(random));
        for (int i = 0; i < (int)random; i++) {
            Log.d("initialization", String.format("making solarsystem:\t%d", solarSystems.size()));
            solarSystems.add(new SolarSystem(String.format("%d", i)));
        }
    }

    public Set<SolarSystem> getSolarSystems() {
        return solarSystems;
    }
}
