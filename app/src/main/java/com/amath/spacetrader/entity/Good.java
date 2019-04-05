package com.amath.spacetrader.entity;

import static java.lang.Math.random;

public enum Good {

    WATER(0, 0, 2, 30, 3, 4, Event.DROUGHT, ResourceLevel.LOTSOFWATER, ResourceLevel.DESERT,
            30, 50),
    FURS(0, 0, 0, 250, 10, 10, Event.COLD, ResourceLevel.RICHFAUNA, ResourceLevel.LIFELESS,
            230, 280),
    FOOD(1, 0, 1, 100, 5, 5, Event.CROPFAIL, ResourceLevel.RICHSOIL, ResourceLevel.POORSOIL,
            90, 160),
    ORE(2,2,3,350,20,10,Event.WAR,ResourceLevel.MINERALRICH,ResourceLevel.MINERALPOOR,350,420),
    GAMES(3, 1, 6, 250, -10, 5, Event.BOREDOM, ResourceLevel.ARTISTIC, null, 160, 270),
    FIREARMS(3, 1, 5, 1250, -75, 100, Event.WAR, ResourceLevel.WARLIKE, null, 600, 1100),
    MEDICINE(4, 1, 6, 650, -20, 10,Event.PLAGUE, ResourceLevel.LOTSOFHERBS, null, 400, 700),
    MACHINES(4, 3, 5, 900, -30, 5, Event.LACKOFWORKERS, null, null, 600, 800),
    NARCOTICS(5, 0, 5, 3500, -125, 150, Event.BOREDOM, ResourceLevel.WEIRDMUSHROOMS, null,
            2000, 3000),
    ROBOTS(6, 4, 7, 5000, -150, 100, Event.LACKOFWORKERS, null, null, 3500, 5000);

    private final int mtlp;
    private final int mtlu;
    private final int ttp;
    private final int basePrice;
    private final int ipl;
    private final int variance;
    private final Event ie;
    private final ResourceLevel cheapCond;
    private final ResourceLevel expensiveCond;
    private final int mtl;
    private final int mth;
    private static final double RATIO = 0.01;

    Good(int mtlp, int mtlu, int ttp, int basePrice, int ipl, int variance, Event ie,
             ResourceLevel cheapCond, ResourceLevel expensiveCond, int mtl, int mth) {
        this.mtlp = mtlp;
        this.mtlu = mtlu;
        this.ttp = ttp;
        this.basePrice = basePrice;
        this.ipl = ipl;
        this.variance = variance;
        this.ie = ie;
        this.cheapCond = cheapCond;
        this.expensiveCond = expensiveCond;
        this.mtl = mtl;
        this.mth = mth;
    }

    public int getMtlp() {
        return mtlp;
    }

    public int getMtlu() {
        return mtlu;
    }

    public int getTtp() {
        return ttp;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getIpl() {
        return ipl;
    }

    public int getVariance() {
        return variance;
    }

    public Event getIe() {
        return ie;
    }

    public ResourceLevel getCheapCond() {
        return cheapCond;
    }

    public ResourceLevel getExpensiveCond() {
        return expensiveCond;
    }

    public int getMtl() {
        return mtl;
    }

    public int getMth() {
        return mth;
    }


    public int calculatePrice(TechLevel tech) {
        int minT = this.getMtlp();
        int basePrice = this.getBasePrice();
        int ipl = this.getIpl();
        int variance = this.getVariance();
        int planetTechLevel = tech.ordinal();

        int price = basePrice + ipl * (planetTechLevel - minT);
        int priceVar = (int)(basePrice * RATIO * variance * (random() - random()));

        return price + priceVar;
    }

}