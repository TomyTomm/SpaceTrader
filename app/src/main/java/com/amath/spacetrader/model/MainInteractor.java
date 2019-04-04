package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;

import java.io.File;

public class MainInteractor extends Interactor {
    protected MainInteractor(Repository repo) {
        super(repo);
    }

    public boolean loadGame(File file) {
        Repository repo = getRepository();
        return repo.loadGame(file);
    }
}
