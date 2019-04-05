package com.amath.spacetrader.entity;

public enum RandomEvent {
    ROBBERY("You've been robbed!"),
    SHIP_MALFUNCTION("Your ship has malfunctioned!"),
    CREW_MUTINY("Your crew has started a riot!"),
    BLACK_HOLE("You've been sucked into a black hole!");

    private final String description;

    RandomEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
