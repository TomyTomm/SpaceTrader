package com.amath.spacetrader.entity;

public class Game {
    private GameDifficulty gameDifficulty;
    private Player player;

    public Game(Player player, GameDifficulty gameDifficulty) {
        this.player = player;
        this.gameDifficulty = gameDifficulty;
    }
}
