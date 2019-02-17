package com.amath.spacetrader.entity;

public class Player {
    private String name;

    private int pilotPts;
    private int traderPts;
    private int engineerPts;
    private int fighterPts;

    private int credits;

    private final int STARTING_CREDITS = 1000;

    public Player(String name, int pilotPts, int traderPts, int engineerPts, int fighterPts) {
        this.name = name;
        this.pilotPts = pilotPts;
        this.traderPts = traderPts;
        this.engineerPts = engineerPts;
        this.fighterPts = fighterPts;
        credits = 1000;
    }
}
