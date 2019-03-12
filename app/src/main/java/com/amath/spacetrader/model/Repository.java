package com.amath.spacetrader.model;

import android.content.Context;
import android.util.Log;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Universe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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

    public void loadGame(Game game) {
        this.game = game;
    }

    public void loadPlayer(Player player) {
        this.game.loadPlayer(player);
    }

    public boolean serialize(File file) {
        boolean successful = true;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(game);
            out.close();
        } catch (Exception e) {
            Log.d("saveunsuccessful", "Could not serialize game");
            successful = false;
        }
        return successful;
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

    public Universe getUniverse() { return this.game.getUniverse(); }

    public Player getPlayer() {
        return game.getPlayer();
    } // not sure if we want this method here.
}
