package com.amath.spacetrader.entity;

import java.io.Serializable;

public class Game implements Serializable {
    private GameDifficulty gameDifficulty;
    private Player player;
    private Universe universe;
    private SolarSystem solarSystem;

    public Game() {
        this(null, GameDifficulty.EASY, new Universe(), new SolarSystem());
    }

    public Game(Player player, GameDifficulty gameDifficulty, Universe universe, SolarSystem solarSystem) {
        this.player = player;
        this.gameDifficulty = gameDifficulty;
        this.universe = universe;
        this.solarSystem = solarSystem;
    }

    /**
     * Loads a player into the game.
     *
     * @param player
     */
    public void loadPlayer(Player player) {
        this.player = player;
    }

    /**
     * Loads universe into game
     *
     * @param universe
     */
    public void loadUniverse(Universe universe) {
        this.universe = universe;
    }

    /**
     * Getter method for player attribute
     *
     * @return the player of the game
     */
    public Player getPlayer() {
        return player;
    }



    /**
     * Changes difficulty of the game
     *
     * @param difficulty the new difficulty to set as
     */
    public void changeDifficulty(GameDifficulty difficulty) {
        this.gameDifficulty = difficulty;
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public Universe getUniverse() {
        return universe;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public Planet getCurrentPlanet() {
        return universe.getCurrentPlanet();
    }

    public void setCurrentPlanet(Planet currentPlanet) {
        universe.setCurrentPlanet(currentPlanet);
    }
}
