package com.amath.spacetrader.model;

import android.content.Context;
import android.util.Log;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Universe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles saving and loading of game.
 */
public class Repository {
    private Game game;
//    private Player player;
//    private GameDifficulty difficulty;

    public Game getGame() {
        return this.game;
    }

    /**
     * Saves current state of game by serializing Game object
     * to text file.
     *
     * @param file file to serialize to
     * @return success
     */
    public boolean saveGame(File file) {
        try {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(game);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference gameRef = database.getReference(String.valueOf(game.uuid));

                gameRef.setValue(json);
                return true;
            } catch (Exception e) {
                ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(game);
                out.close();
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Attempts to retrieve previously saved game. Creates new Game
     * if unsuccessful.
     * @param file save file
     * @return successful
     */
    public boolean loadGame(File file) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            this.game = (Game) in.readObject();
            return true;
        } catch (Exception e) {
            this.game = new Game();
            return false;
        }
    }

    /**
     * Currently does nothing. Once Firebase is implemented,
     * this will push game settings to server. Essentially a
     * save method
     *
     */
    public void update() {

    }

    public void loadPlayer(Player player) {
        this.game.loadPlayer(player);
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


    public Universe getUniverse() { return this.game.getUniverse(); }

    public Player getPlayer() {
        return game.getPlayer();
    } // not sure if we want this method here.
}
