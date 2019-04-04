package com.amath.spacetrader.model;

import java.io.File;

public abstract class Interactor {

    private Repository myRepository;
    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Repository getRepository() {
        return myRepository;
    }

    public boolean saveLocalGame(File file) {
        return myRepository.saveGame(file);
    }

}