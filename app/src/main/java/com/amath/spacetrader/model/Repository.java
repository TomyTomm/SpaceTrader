package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Game> allGames;
    private List<Game> allPlayers;

    public Repository() {
        allGames = new ArrayList<>();
        allPlayers = new ArrayList<>();
    }
}
