package com.amath.spacetrader.entity;

public enum ResourceLevel {
    NOSPECIALRESOURCES(0),
    MINERALRICH(1),
    MINERALPOOR(2),
    DESERT(3),
    LOTSOFWATER(4),
    RICHSOIL(5),
    POORSOIL(6),
    RICHFAUNA(7),
    LIFELESS(8),
    WEIRDMUSHROOMS(9),
    LOTSOFHERBS(10),
    ARTISTIC(11),
    WARLIKE(12);

    private final int level;
    ResourceLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
