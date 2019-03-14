package com.amath.spacetrader.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable {
    private String name;

    private int pilotPts;
    private int traderPts;
    private int engineerPts;
    private int fighterPts;

    private final int STARTING_CREDITS = 1000;
    private int credits = STARTING_CREDITS;

    // I don't know if the player needs a location, can't we just use the location of the planet
    // it's on?
//    private Coordinate playerLocation;
//    private static final Coordinate playerStartingLocation = new Coordinate(0.0,0.0);

    private Ship ship;

    private Map<Good, Integer> inventory = new HashMap<>();
    private int inventorySize = 0;
//    private int inventoryCapacitzy = 0;

    public Player(String name, int pilotPts, int traderPts, int engineerPts, int fighterPts) {
        this.name = name;
        this.pilotPts = pilotPts;
        this.traderPts = traderPts;
        this.engineerPts = engineerPts;
        this.fighterPts = fighterPts;
        for (Good good: Good.values()) {
            inventory.put(good, 0);
        }
        acquireShip(ShipType.GNAT);
    }

    public void acquireShip(ShipType newShip) {
        if (inventorySize > newShip.getCargoCapacity()) {
            throw new IllegalArgumentException("Cannot move to this ship, it has too small of an inventory");
        }
        this.ship = new Ship(newShip);
    }

    public String toString() {
        return String.format("Name: %s. Pilot points: %d. Trader points: %d. Engineer Points: %d." +
                        " Fighter points: %d. Credits: %d", name,
                pilotPts, traderPts, engineerPts, fighterPts, credits);
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public Map<Good, Integer> getInventory() {
        return inventory;
    }

    public void addGood(Good good, int amount) {
        this.inventory.put(good, this.inventory.get(good) + amount);
        inventorySize += amount;
    }

    public void removeGood(Good good, int amount) {
        this.inventory.put(good, this.inventory.get(good) - amount);
        inventorySize -= amount;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getInventoryCapacity() {
        return this.ship.getHoldSize();
    }

    public int getGoodAmount(Good good) {
        return inventory.get(good);
    }
}
