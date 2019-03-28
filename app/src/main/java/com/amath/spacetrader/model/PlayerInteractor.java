package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Planet;

public class PlayerInteractor extends Interactor {
    protected PlayerInteractor(Repository repo) {
        super(repo);
    }

    public String getPlayerName() {
        Model model = Model.getInstance();
        return model.getPlayer().getName();
    }

    public String getCurrentPlanetName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getName();
    }

    public String getCurrentSolarSystemName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getSolarSystem().getName();
    }

    public double getFuelRemaining() {
        Model model = Model.getInstance();
        return model.getPlayer().getShip().getFuel();
    }
}
