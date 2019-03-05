package com.amath.spacetrader.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {
    private String name;

    private int pilotPts;
    private int traderPts;
    private int engineerPts;
    private int fighterPts;

    private int credits;
    private final int STARTING_CREDITS = 1000;

    private Planet currentPlanet;
    private Coordinate playerLocation;

    private final Coordinate playerStartingLocation = new Coordinate(0.0,0.0);

    private Ship ownedShip;
    private List<Good> inventory;

    public Player(String name, int pilotPts, int traderPts, int engineerPts, int fighterPts) {
        this.name = name;
        this.pilotPts = pilotPts;
        this.traderPts = traderPts;
        this.engineerPts = engineerPts;
        this.fighterPts = fighterPts;
        credits = STARTING_CREDITS;
        currentPlanet = new Planet("Genesis", new Coordinate(0,0), 5,5);
        playerLocation = currentPlanet.getLocation();
        inventory = new ArrayList<>();
        ownedShip = new Ship(ShipType.GNAT);
    }

    public void acquireShip(ShipType newShip) {
        ownedShip = new Ship(newShip);
        ArrayList<Good> movingIn = new ArrayList<>(newShip.getCargoCapacity());
        for (int i = 0; i < inventory.size(); i++) {
            movingIn.add(inventory.get(i));
        }
        inventory = movingIn;
    }

    public List<Good> getInventory() {
        return inventory;
    }

    public void updateInventory(List<Good> newInventory) {
        inventory = newInventory;
    }

    public Ship getOwnedShip() {
        return ownedShip;
    }

    public Planet getCurrentPlanet() {
        return currentPlanet;
    }

    public void updateCredits(int amount) {
        credits += amount;
    }

    public int getCredits() {
        return credits;
    }

    public String toString() {
        return String.format("Name: %s. Pilot points: %d. Trader points: %d. Engineer Points: %d." +
                " Fighter points: %d. Credits: %d", name,
                pilotPts, traderPts, engineerPts, fighterPts, credits);
    }
}
