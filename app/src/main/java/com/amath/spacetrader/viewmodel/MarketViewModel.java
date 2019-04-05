package com.amath.spacetrader.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.TechLevel;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.MarketInteractor;
import com.amath.spacetrader.model.Model;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MarketViewModel extends AndroidViewModel {

    private final MarketInteractor interactor;
    private final Map<Good, Integer> market;


    /**
     * Please use this exception class. Look at ConfigurationViewModel
     * as an example
     *
     */
    public final class IllegalTradeException extends Exception {
        private IllegalTradeException(String message) {
            super(message);
        }
    }

    public MarketViewModel(@NonNull Application application) {
        super(application);
        Model model = Model.getInstance();

        interactor = model.getMarketInteractor();
        market = loadMarket();
    }

    public TechLevel getTechLevel() {
        return interactor.getTechLevel();
    }

    public String getPlanetName() {
        return interactor.getPlanetName();
    }

    public int getPlayerCredits() {
        return interactor.getPlayerCredits();
    }

    public Map<Good, Integer> loadMarket() {
        return interactor.loadMarket();
    }

    public Map<Good, Integer> loadPlanetInventory() {
        return interactor.loadPlanetInventory();
    }

//    public HashMap<Good, Integer> loadMarket() {
//        Model model = Model.getInstance();
//        Player player = model.getPlayer();
//        return interactor.loadMarket(player);
//    }

    /**
     * Checks to see if player can buy (amount) # of (good) goods.
     * Check: If planet has enough goods
     * Check: if player's credits > amount * price
     * Check: If adding player.getInventorySize() + amount <= cargo capacity
     *
     * Get: Price it sells for at currentPlanet (Try using this.market)
     *
     * if valid, call MarketInteractor buyGood(Good good, int amount, int price)
     *
     * PLEASE READ IMPLEMENTATION OF MarketInteractor.java
     *
     * @throws IllegalArgumentException
     * @param good
     * @param amount
     * @return A string error message, null if there is no error
     */
    public boolean verifyBuy(Good good, int amount, int price, Map<Good, Integer> market)
            throws IllegalTradeException {
        Player player = Model.getInstance().getPlayer();
        int credits = player.getCredits();

        if (amount > market.get(good)) {
            throw new IllegalTradeException(String.format(Locale.US,
                    "You cannot purchase %d %s(s), planet does not have enough goods",
                    amount, good.toString()));
        } else if (amount + player.getInventorySize() > player.getInventoryCapacity()) {
            throw new IllegalTradeException(String.format(Locale.US,
                    "You cannot purchase %d %s(s), you do not have enough capacity",
                    amount, good.toString()));
        } else if (amount * price > credits) {
            throw new IllegalTradeException(String.format(Locale.US,
                    "You cannot purchase %d %s(s), you do not have enough credits",
                    amount, good.toString()));
        }
        interactor.buyGood(good, amount, price, market);
        return true;
    }

    /**
     * Checks to see if player can sell (amount) # of (good) goods.
     * Check: If player has enough (good) goods
     * Get: Price it sells for at Planet (Try using this.market)
     *
     * PLEASE READ IMPLEMENTATION OF MarketInteractor.java
     *
     * @throws IllegalTradeException
     * @param good
     * @param amount
     * @return A string error message, null if there is no error
     */
    public boolean verifySell(Good good, int amount, int price, Map<Good, Integer> market)
            throws IllegalTradeException {

        int playerAmount = getGoodAmount(good);

        if (amount > playerAmount) {
            throw new IllegalTradeException(String.format(Locale.US,
                    "You cannot sell %d %s(s), you do not have enough goods",
                    amount, good.toString()));
        }
        interactor.sellGood(good, amount, price, market);
        return true;
    }

    public int getGoodAmount(Good good) { return interactor.getGoodAmount(good); }


    public boolean saveGameLocally(File file) {
        return interactor.saveLocalGame(file);
    }

    public int getInventorySize() {
        return interactor.getInventorySize();
    }

    public int getCapacity() {
        return interactor.getCapacity();
    }

}
