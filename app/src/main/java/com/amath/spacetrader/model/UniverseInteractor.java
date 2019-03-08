package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Universe;

public class UniverseInteractor extends Interactor {
    protected UniverseInteractor(Repository repo) {
        super(repo);
    }

    public Universe getUniverse() { return getRepository().getUniverse(); }
}
