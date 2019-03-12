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

import java.util.HashMap;
import java.util.Map;

public class MarketViewModel extends AndroidViewModel {

    private MarketInteractor interactor;
    private Map<Good, Integer> market;


    /**
     * Please use this exception class. Look at ConfigurationViewModel
     * as an example
     *
     */
    public class IllegalTradeException extends Exception {
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
    public boolean verifyBuy(Good good, int amount, int price, Map<Good, Integer> market) throws IllegalTradeException {
//        List<Good> pI = player.getInventory();
//        Good[] tradedGoods = (Good[]) trades.keySet().toArray();
//        Integer[] prices = (Integer[]) trades.values().toArray();
//        int sizeIncrease = 0;
//        int tradeSize = 0;
//        for (int i = 0; i < tradedGoods.length; i++) {
//            if (pI.contains(tradedGoods[i].getType())) {
//                if ((pI.get(pI.lastIndexOf(tradedGoods[i])).getQuantity()
//                        + tradedGoods[i].getQuantity()) < 0) {
//                    return "Cannot sell more than you own.";
//                } else {
//                    if (player.getCredits()
//                            < trades.get(tradedGoods[i])
//                            *tradedGoods[i].getQuantity()) {
//                        return "You're too poor to buy this much.";
//                    } else {
//                        tradeSize += trades.get(tradedGoods[i])
//                                * tradedGoods[i].getQuantity();
//                    }
//                }
//            } else {
//                if (tradedGoods[i].getQuantity() < 0) {
//                    return "You can't sell what you don't have.";
//                } else {
//                    sizeIncrease++;
//                    tradeSize += trades.get(tradedGoods[i]) * tradedGoods[i].getQuantity();
//                }
//            }
//        }
//        if ((sizeIncrease + pI.size()) > player.getOwnedShip().getHoldSize()) {
//            return "Not enough space.";
//        } else if (player.getCredits() + tradeSize < 0) {
//            return "Not enough credits.";
//        } else {
//            for (int i = 0; i < trades.size(); i++) {
//                interactor.tradeGood(player, tradedGoods[i]);
//                interactor.updateCredits(player.getCredits() + tradeSize);
//            }
//        }
//        return "Trade completed for " + tradeSize + " credits.";
        Player player = Model.getInstance().getPlayer();
        int credits = player.getCredits();
        if (amount * price > credits) {
            throw new IllegalTradeException(String.format("You cannot purchase %d %s(s), you do not have enough credits", amount, good.toString()));
        } else if (amount + player.getInventorySize() > player.getInventoryCapacity()) {
            throw new IllegalTradeException(String.format("You cannot purchase %d %s(s), you do not have enough capacity", amount, good.toString()));
        } else if (amount > market.get(good)) {
            throw new IllegalTradeException(String.format("You cannot purchase %d %s(s), planet does not have enough goods", amount, good.toString()));
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
    public boolean verifySell(Good good, int amount, int price, Map<Good, Integer> market) throws IllegalTradeException {

        Player player = Model.getInstance().getPlayer();
        int playerAmount = player.getGoodAmount(good);

        if (amount > playerAmount) {
            throw new IllegalTradeException(String.format("You cannot sell %d %s(s), you do not have enough goods", amount, good.toString()));
        }
        interactor.sellGood(good, amount, price, market);
        return true;
    }



}
