package com.amath.spacetrader.entity;

import java.util.Set;
import java.util.HashSet;

public class Universe {

    private Set<SolarSystem> solarSystems;

    public Universe() {
        solarSystems = new HashSet<>();
    }

    public Set<SolarSystem> getSolarSystems() {
        return solarSystems;
    }
}
