package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;

public class MainInteractor extends Interactor {
    protected MainInteractor(Repository repo) {
        super(repo);
    }

    public void loadGame(Game game) throws Exception {
        Repository repo = getRepository();
        repo.loadGame(game);
    }
}
