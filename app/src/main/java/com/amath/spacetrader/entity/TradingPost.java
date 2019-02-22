package com.amath.spacetrader.entity;

import java.util.HashSet;
import java.util.Set;

public class TradingPost {
    private Set<Good> goods;

    public TradingPost() {
        goods = new HashSet<>();
    }
}
