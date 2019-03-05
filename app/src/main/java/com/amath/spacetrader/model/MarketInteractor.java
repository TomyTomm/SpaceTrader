package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.TradingPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MarketInteractor extends Interactor {
    protected MarketInteractor(Repository repo) {
        super(repo);
    }


    public HashMap<Good, Integer> loadMarket(Player player, Planet planet) {
        return Model.inventoryTradePostMerger(player, planet);
    }

    public void tradeGood(Player player, Good good) {
        Model.updatePlayerInventory(good, player);
    }

    public void updateCredits(int amount) {
        Model.updateCredits(amount);
    }
}
