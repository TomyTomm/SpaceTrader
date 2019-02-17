package com.amath.spacetrader.model;

public class Model {

    private Repository repo;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        this.repo = new Repository();
    }
}
