package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;

import java.util.List;

public class ConfigurationInteractor extends Interactor {
    public ConfigurationInteractor(Repository repo) {
        super(repo);
    }

    public void newGame(Game game) {
        getRepository().newGame(game);
    }

    public List<Game> getAllGames() {
        return getRepository().getAllGames();
    }
}
