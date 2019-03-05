package com.amath.spacetrader.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static java.lang.Math.random;

public class TradingPost {
    // economic model
    public static final Object[][] infoRef = {
            {"water", 0, 0, 2, 30, 3, 4, "DROUGHT", ResourceLevel.LOTSOFWATER, ResourceLevel.DESERT, 30, 50},
            {"furs", 0, 0, 0, 250, 10, 10, "COLD", ResourceLevel.RICHFAUNA, ResourceLevel.LIFELESS, 230, 280},
            {"food",1,0,1,100,5,5,"CROPFAIL",ResourceLevel.RICHSOIL,ResourceLevel.POORSOIL,90,160},
            {"ore",2,2,3,350,20,10,"WAR",ResourceLevel.MINERALRICH,ResourceLevel.MINERALPOOR,350,420},
            {"games",3,1,6,250,-10,5,"BOREDOM",ResourceLevel.ARTISTIC,null,160,270},
            {"firearms",3,1,5,1250,-75,100,"WAR",ResourceLevel.WARLIKE,null,600,1100},
            {"medicine",4,1,6,650,-20,10,"PLAGUE",ResourceLevel.LOTSOFHERBS, null,400,700},
            {"machines",4,3,5,900,-30,5,"LACKOFWORKERS",null,null,600,800},
            {"narcotics",5,0,5,3500,-125,150,"BOREDOM",ResourceLevel.WEIRDMUSHROOMS,null,2000,3000},
            {"robots",6,4,7,5000,-150,100,"LACKOFWORKERS",null,null,3500,5000}};

    private Planet ownerPlanet;
    private boolean exists;
    public TradingPost(Planet planet) {
        exists = planet.isMarketAvailable();
        ownerPlanet = planet;
    }

    // (base price)
    // + (Price Increase Per Tech Level Above Min * (Tech Level - Minimum Tech Level to Produce))
    // +/- (Base Price * Variance)
    public Map<Good, Integer> generateMarket(){
        Map<Good, Integer> market = new HashMap<>();
        for (int i = 0; i < infoRef.length; i++) {
            int minT = (int) infoRef[i][1];
            if (minT <= ownerPlanet.getTechLevel().getLevel()) {
                int price;
                int basePrice = (int) infoRef[i][4];
                int pI = (int) infoRef[i][5];
                int var = (int) infoRef[i][6];
                price = basePrice + pI
                        * (ownerPlanet.getTechLevel().getLevel() - minT);
                double priceVar = basePrice * 0.01 * var * (random() - random());
                price += priceVar;
                String goodName = (String) infoRef[i][0];
                market.put(new Good(goodName,new Integer(0)), price);
            }
        }
        return market;
    }
}
