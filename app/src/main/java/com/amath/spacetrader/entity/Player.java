package com.amath.spacetrader.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Player implements Serializable {
    private final String name;

    private final int pilotPts;
    private final int traderPts;
    private final int engineerPts;
    private final int fighterPts;
    private int credits;

    // I don't know if the player needs a location, can't we just use the location of the planet
    // it's on?
//    private Coordinate playerLocation;
//    private static final Coordinate playerStartingLocation = new Coordinate(0.0,0.0);

    private Ship ship;

    private Weapon chosenWeapon;

    private final Map<Good, Integer> inventory = new HashMap<>();
    private int inventorySize;

    public Player(String name, int pilotPts, int traderPts, int engineerPts, int fighterPts) {
        final int STARTING_CREDITS = 1000;
        this.name = name;
        this.pilotPts = pilotPts;
        this.traderPts = traderPts;
        this.engineerPts = engineerPts;
        this.fighterPts = fighterPts;
        for (Good good: Good.values()) {
            inventory.put(good, 0);
        }
        acquireShip(ShipType.GNAT);
        this.credits = STARTING_CREDITS;
        this.chosenWeapon = Weapon.NONE;
    }

    public void acquireShip(ShipType newShip) {
        if (inventorySize > newShip.getCargoCapacity()) {
            throw new IllegalArgumentException
                    ("Cannot move to this ship, it has too small of an inventory");
        }
        this.ship = new Ship(newShip);
    }

    public String toString() {
        return String.format(Locale.US, "Name: %s. Pilot points: %d. Trader points: %d. " +
                "Engineer Points: %d. Fighter points: %d. Credits: %d", name, pilotPts, traderPts,
                engineerPts, fighterPts, credits);
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

    public String getName() {
        return this.name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getInventoryCapacity() {
        return this.ship.getHoldSize() - this.chosenWeapon.getCost();
    }

    public int getGoodAmount(Good good) {
        return inventory.get(good);
    }

    public Ship getShip() { return this.ship; }

    public Weapon getChosenWeapon() {
        return chosenWeapon;
    }
    public void setChosenWeapon(Weapon weapon) {
        chosenWeapon = weapon;
    }
}
