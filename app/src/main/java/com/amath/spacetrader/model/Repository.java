package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Game> allGames;

    public Repository() {
        allGames = new ArrayList<>();
    }

    public void newGame(Game game) {
        allGames.add(game);
    }

    public List<Game> getAllGames() {
        return allGames;
    }
}
