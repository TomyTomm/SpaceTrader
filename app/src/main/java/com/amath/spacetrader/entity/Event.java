package com.amath.spacetrader.entity;

public enum Event {
    DROUGHT,
    COLD,
    CROPFAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACKOFWORKERS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
