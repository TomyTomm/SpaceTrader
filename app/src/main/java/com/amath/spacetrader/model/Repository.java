package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private Game game;
//    private Player player;
//    private GameDifficulty difficulty;

    public Repository() {
        loadGame();
    }

    /**
     * Currently does nothing. Once Firebase is implemented,
     * will pull game from server.
     */
    private void loadGame() {
        this.game = new Game();
    }

    /**
     * Currently does nothing. Once Firebase is implemented,
     * this will push game settings to server. Essentially a
     * save method
     *
     */
    public void update() {

    }

//    public void newGame(Game game) {
//        allGames.add(game);
//    }
//
//    public List<Game> getGame() {
//        return allGames;
//    }

    public void newGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public Player getPlayer() {
        return game.getPlayer();
    } // not sure if we want this method here.
}
