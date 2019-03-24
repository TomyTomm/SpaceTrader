package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.SolarSystem;

public class SolarSystemInteractor extends Interactor {
    protected  SolarSystemInteractor(Repository repo) {super(repo); }

    public SolarSystem getSolarSystem() {return getRepository().getSolarSystem(); }
}
