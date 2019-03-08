package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
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
        Map<Good, Integer> market = new HashMap<>();

        for (Good good: Good.values()) {
            market.put(good, good.calculatePrice(model.getGame().getCurrentPlanet().getTechLevel()));
            // ^^^^ breaks Law of Demeter
        }

        return market;

    }


    /**
     * Adds (amount) # to (good) goods
     *
     * @param good
     * @param amount
     */
    public void buyGood(Good good, int amount) {
//        Model.updatePlayerInventory(good, player);
        Player player = model.getPlayer();
        player.addGood(good, amount);

    }

    /**
     * Removes (amount) # to (good) goods
     *
     * @param good
     * @param amount
     */
    public void sellGood(Good good, int amount) {
//        Model.updatePlayerInventory(good, player);
        Player player = model.getPlayer();
        player.removeGood(good, amount);
    }

    /**
     *
     * Sets the amount of credit to [credits + change]
     *
     * @param change
     */
    public void updateCredits(int change) {
        Player player = model.getPlayer();
        player.setCredits(player.getCredits() + change);
    }
}
