package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Model {
/*
    private Repository repo;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        this.repo = new Repository();
    }
    */

    /** the data repository */
    private Repository myRepository;

    private Map<String, Object> interactorMap;

    private Game game;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        game = myRepository.getGame();
        registerInteractors();
    }

    /** end Singleton Pattern */

    public static void loadPlayer(Player player) {
        instance.game.loadPlayer(player);
        instance.myRepository.update();
    }

    public static void loadDifficulty(GameDifficulty difficulty) {
        instance.game.changeDifficulty(difficulty);
    }


    /**
     * Create a set of interactors to be used by the application
     */
    private void registerInteractors() {
        interactorMap.put("Game", new ConfigurationInteractor(myRepository));
    }

    public ConfigurationInteractor getConfigurationInteractor() {
        return (ConfigurationInteractor) interactorMap.get("Game");
    }
}
