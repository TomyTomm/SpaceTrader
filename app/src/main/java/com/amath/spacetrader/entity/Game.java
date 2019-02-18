package com.amath.spacetrader.entity;

public class Game {
    private GameDifficulty gameDifficulty;
    private Player player;

    public Game() {
        this.gameDifficulty = GameDifficulty.EASY;
    }

    public Game(Player player, GameDifficulty gameDifficulty) {
        this.player = player;
        this.gameDifficulty = gameDifficulty;
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

    }
}
