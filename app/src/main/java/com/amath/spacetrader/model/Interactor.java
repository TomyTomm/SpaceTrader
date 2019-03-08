package com.amath.spacetrader.model;

public abstract class Interactor {

    private Repository myRepository;
    protected Model model = Model.getInstance();

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Repository getRepository() {
        return myRepository;
    }
}