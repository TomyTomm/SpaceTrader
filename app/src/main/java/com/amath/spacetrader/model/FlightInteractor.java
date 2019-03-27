package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Ship;
import com.amath.spacetrader.entity.Universe;

public class FlightInteractor extends Interactor {

    private Game game;
    private Ship playerShip;

    protected FlightInteractor(Repository repo) {
        super(repo);
        // You can't access the model instance from the constructor of a flight interactor.
//        game = Model.getInstance().getGame();
//        playerShip = game.getPlayer().getShip();
    }

    public void fly(Planet planet) {
    }

}
