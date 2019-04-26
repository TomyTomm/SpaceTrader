package com.amath.spacetrader.entity;

public enum RandomEvent {
    ROBBERY("You've been robbed!"),
    SHIP_MALFUNCTION("Your ship has malfunctioned!"),
    CREW_MUTINY("Your crew has started a riot!"),
    BLACK_HOLE("You've been sucked into a black hole!"),
    PIRATE_ENCOUNTER("Rogue pirates! Scum of the earth, stars, AND galaxies! Watch out!"),
    POLICE_ENCOUNTER("In the lawless void of space... who governs the governors? No one!");

    private final String description;

    RandomEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
