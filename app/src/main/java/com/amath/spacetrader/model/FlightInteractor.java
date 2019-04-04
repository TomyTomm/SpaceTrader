package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Ship;
import com.amath.spacetrader.entity.Universe;

import java.io.File;

public class FlightInteractor extends Interactor {

    private Game game;
    private Ship playerShip;
    private Player player;

    protected FlightInteractor(Repository repo) {
        super(repo);
        // You can't access the model instance from the constructor of a flight interactor.
//        game = Model.getInstance().getGame();
//        playerShip = game.getPlayer().getShip();
    }

    public double getRemainingFuel() {
        Model model = Model.getInstance();
        return model.getPlayer().getShip().getFuel();
    }

    public void flyTo(Planet planet, double distance, File file) {
        Model model = Model.getInstance();
        game = model.getGame();
        player = game.getPlayer();
        playerShip = player.getShip();

        playerShip.setFuel(playerShip.getFuel() - distance);
        game.setCurrentPlanet(planet);
        getRepository().saveGame(file);
    }

}
