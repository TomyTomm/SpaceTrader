package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Ship;
import com.amath.spacetrader.entity.Universe;

import java.io.File;

public class FlightInteractor extends Interactor {

    protected FlightInteractor(Repository repo) {
        super(repo);
    }

    public double getRemainingFuel() {
        Model model = Model.getInstance();
        return model.getPlayer().getShip().getFuel();
    }

    public void flyTo(Planet planet, double distance, File file) {
        Model model = Model.getInstance();
        Game game = model.getGame();
        Player player = game.getPlayer();
        Ship playerShip = player.getShip();

        playerShip.setFuel(playerShip.getFuel() - distance);
        game.setCurrentPlanet(planet);
        getRepository().saveGame(file);
    }

}
