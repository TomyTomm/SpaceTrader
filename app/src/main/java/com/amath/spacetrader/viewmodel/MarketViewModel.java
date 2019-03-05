package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.model.MarketInteractor;
import com.amath.spacetrader.model.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MarketViewModel extends AndroidViewModel {

    private MarketInteractor interactor;
    private HashMap<Good, Integer> market;
    private Player player;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getMarketInteractor();
        player = Model.getInstance().getGame().getPlayer();
        market = interactor.loadMarket(player, player.getCurrentPlanet());
    }

    public HashMap<Good, Integer> loadMarket(Player player, Planet planet) {
        return interactor.loadMarket(player, planet);
    }

    public String processTransactions(HashMap<Good, Integer> trades) {
        List<Good> pI = player.getInventory();
        Good[] tradedGoods = (Good[]) trades.keySet().toArray();
        Integer[] prices = (Integer[]) trades.values().toArray();
        int sizeIncrease = 0;
        int tradeSize = 0;
        for (int i = 0; i < tradedGoods.length; i++) {
            if (pI.contains(tradedGoods[i].getType())) {
                if ((pI.get(pI.lastIndexOf(tradedGoods[i])).getQuantity()
                        + tradedGoods[i].getQuantity()) < 0) {
                    return "Cannot sell more than you own.";
                } else {
                    if (player.getCredits()
                            < trades.get(tradedGoods[i])
                            *tradedGoods[i].getQuantity()) {
                        return "You're too poor to buy this much.";
                        } else {
                        tradeSize += trades.get(tradedGoods[i])
                                * tradedGoods[i].getQuantity();
                    }
                }
            } else {
                if (tradedGoods[i].getQuantity() < 0) {
                    return "You can't sell what you don't have.";
                } else {
                    sizeIncrease++;
                    tradeSize += trades.get(tradedGoods[i]) * tradedGoods[i].getQuantity();
                }
            }
        }
        if ((sizeIncrease + pI.size()) > player.getOwnedShip().getHoldSize()) {
            return "Not enough space.";
        } else if (player.getCredits() + tradeSize < 0) {
            return "Not enough credits.";
        } else {
            for (int i = 0; i < trades.size(); i++) {
                interactor.tradeGood(player, tradedGoods[i]);
                interactor.updateCredits(tradeSize);
            }
        }
        return "Trade completed for " + tradeSize + " credits.";
    }

}
