package com.amath.spacetrader.entity;

public enum GoodType {
    WATER(TradeReq.WATER),
    FURS(TradeReq.FURS),
    FOOD(TradeReq.FOOD),
    ORE(TradeReq.ORE),
    GAMES(TradeReq.GAMES),
    FIREARMS(TradeReq.FIREARMS),
    MEDICINE(TradeReq.MEDICINE),
    MACHINES(TradeReq.MACHINES),
    NARCOTICS(TradeReq.NARCOTICS),
    ROBOTS(TradeReq.ROBOTS);

    private TradeReq tradeReq;

    GoodType(TradeReq tradeReq) {
        this.tradeReq = tradeReq;
    }

    public TradeReq getTradeReq() {
        return tradeReq;
    }
}
