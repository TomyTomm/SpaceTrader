package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.entity.Universe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UniverseInteractor extends Interactor {
    protected UniverseInteractor(Repository repo) {
        super(repo);
    }

    public Universe getUniverse() { return getRepository().getUniverse(); }

    public String getCurrentSolarSystemName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getSolarSystem().getName();
    }

    public Map<SolarSystem, Double> loadSolarSystems() {
        Model model = Model.getInstance();
        Map<SolarSystem, Double> solarSystemsMap = new HashMap<>();
        SolarSystem currentSolarSystem = model.getGame().getCurrentPlanet().getSolarSystem();
        List<SolarSystem> solarSystems = model.getGame().getSolarSystems();

        for (SolarSystem solarSystem: solarSystems) {
            solarSystemsMap.put(solarSystem,
                                calculateDistanceBetweenSolarSystems(currentSolarSystem, solarSystem));
        }
        return solarSystemsMap;
    }

    public double calculateDistanceBetweenSolarSystems(SolarSystem curr, SolarSystem other) {
        double distance;
        final double RATIO = 100;  //random ratio between the 2 different coordinate systems;

        distance = RATIO * Math.sqrt(Math.pow(other.getLocation().getX() - curr.getLocation().getX(), 2)
                + Math.pow(other.getLocation().getY() - curr.getLocation().getY(), 2));
        return distance;
    }
}
