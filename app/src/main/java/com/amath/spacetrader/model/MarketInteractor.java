package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.TechLevel;
import com.amath.spacetrader.entity.Universe;

import java.util.HashMap;
import java.util.Map;

public class MarketInteractor extends Interactor {
    protected MarketInteractor(Repository repo) {
        super(repo);
    }


    /**
     * Makes a HashMap of prices for the planet.
     *
     * @return market – A HashMap of prices on a planet
     */
    public Map<Good, Integer> loadMarket() {
        Model model = Model.getInstance();
        Map<Good, Integer> market = new HashMap<>();

        Planet currentPlanet = model.getGame().getCurrentPlanet();
        for (Good good: Good.values()) {
            market.put(good, good.calculatePrice(currentPlanet.getTechLevel()));
            // ^^^^ breaks Law of Demeter
        }

        return market;
    }

    public Map<Good, Integer> loadPlanetInventory() {
        Model model = Model.getInstance();
        Map<Good, Integer> inventory = new HashMap<>();

        Planet currentPlanet = model.getGame().getCurrentPlanet();
        double ttpMultiplier = 1.0;
        for (Good good: Good.values()) {
            if (good.getMtlp() > currentPlanet.getTechLevel().ordinal()) {
                inventory.put(good, 0);
            } else {
                if (good.getTtp() == currentPlanet.getTechLevel().ordinal()) {
                    ttpMultiplier *= 2.4;
                }
                if (good.getIe() == currentPlanet.getStatus()) {
                    ttpMultiplier *= 0.6;
                }
                int resourceLevel = (currentPlanet.getResourceLevel().ordinal() + 1) * 10;
                inventory.put(good, (int)(resourceLevel * (Math.random()/3 + 0.5)*(ttpMultiplier)));
            }
        }
        return inventory;
    }

    public TechLevel getTechLevel() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getTechLevel();
    }

    public String getPlanetName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getName();
    }

    public int getPlayerCredits() {
        Model model = Model.getInstance();
        return model.getPlayer().getCredits();
    }


    /**
     * Adds (amount) # to (good) goods
     *
     * @param good
     * @param amount
     */
    public void buyGood(Good good, int amount, int price, Map<Good, Integer> market) {
        Model model = Model.getInstance();
//        Model.updatePlayerInventory(good, player);
        Player player = model.getPlayer();
        player.addGood(good, amount);
        player.setCredits(player.getCredits() - amount * price);
        market.put(good, market.get(good) - amount);

    }

    /**
     * Removes (amount) # to (good) goods
     *
     * @param good
     * @param amount
     */
    public void sellGood(Good good, int amount, int price, Map<Good, Integer> market) {
        Model model = Model.getInstance();
//        Model.updatePlayerInventory(good, player);
        Player player = model.getPlayer();
        player.removeGood(good, amount);
        player.setCredits(player.getCredits() + amount * price);
        market.put(good, market.get(good) + amount);
    }

    /**
     *
     * Sets the amount of credit to [credits + change]
     *
     * @param change
     */
    public void updateCredits(int change) {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        player.setCredits(player.getCredits() + change);
    }
}
