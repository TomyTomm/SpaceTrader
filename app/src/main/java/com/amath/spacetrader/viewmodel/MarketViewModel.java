package com.amath.spacetrader.viewmodel;

import android.arch.lifecycle.AndroidViewModel;
import android.app.Application;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.MarketInteractor;
import com.amath.spacetrader.model.Model;

import java.util.HashMap;
import java.util.Map;

public class MarketViewModel extends AndroidViewModel {

    private MarketInteractor interactor;
    private Map<Good, Integer> market;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        Model model = Model.getInstance();

        interactor = model.getMarketInteractor();
        market = loadMarket();
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
     * @param good
     * @param amount
     * @return A string error message, null if there is no error
     */
    public String verifyBuy(Good good, int amount) {
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
        return null;
    }

    /**
     * Checks to see if player can sell (amount) # of (good) goods.
     * Check: If player has enough (good) goods
     * Get: Price it sells for at Planet (Try using this.market)
     *
     * PLEASE READ IMPLEMENTATION OF MarketInteractor.java
     *
     * @param good
     * @param amount
     * @return A string error message, null if there is no error
     */
    public String verifySell(Good good, int amount) {
        return null;
    }



}
