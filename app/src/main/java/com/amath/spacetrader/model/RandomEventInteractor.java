package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Ship;

public class RandomEventInteractor extends Interactor {
    protected RandomEventInteractor(Repository repo) {
        super(repo);
    }

    public String doBlackHole() {
        Model model = Model.getInstance();
        //end the game
        return "You lose";
    }

    public String doCrewMutiny() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        int unitsOfFoodLost = 0;
        if (player.getGoodAmount(Good.FOOD) > 0) {
            player.removeGood(Good.FOOD, 1);
            unitsOfFoodLost = 1;
        }
        return String.format("Your crew has taken %d units of food from you", unitsOfFoodLost);
    }

    public String doShipMalfunction() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        Ship ship = player.getShip();

        double fuelLost = 0;
        if (ship.getFuel() > 0) {
            if (ship.getFuel() < 500) {
                fuelLost = ship.getFuel();
                ship.setFuel(0);
            } else {
                ship.setFuel(ship.getFuel() - 500);
                fuelLost = 500;
            }
        }
        return String.format("You've lost %.2f units of fuel during the malfunction", fuelLost);
    }

    public String doRobbery() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        int creditsLost = 0;
        if (player.getCredits() > 0) {
            if (player.getCredits() < 50) {
                creditsLost = player.getCredits();
                player.setCredits(0);
            } else {
                creditsLost = 50;
                player.setCredits(player.getCredits() - 50);
            }
        }
        return String.format("You've lost %d credits due to the robbery", creditsLost);
    }

    public String displayCrewMutiny() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        return String.format("You have %d units of food left.", player.getGoodAmount(Good.FOOD));
    }

    public String displayMalfunction() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        Ship ship = player.getShip();

        return String.format("You have %d units of fuel left.", ship.getFuel());
    }

    public String displayRobbery() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        return String.format("You have %d credits left.", player.getCredits());
    }
}
