package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Good;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Ship;

import java.util.Locale;

public class RandomEventInteractor extends Interactor {
    public RandomEventInteractor(Repository repo) {
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
        return String.format(Locale.US, "Your crew has taken %d units of food from you",
                unitsOfFoodLost);
    }

    public String doShipMalfunction() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        Ship ship = player.getShip();
        final int fuelToLose = 500;

        double fuelLost = 0;
        if (ship.getFuel() > 0) {
            if (ship.getFuel() < fuelToLose) {
                fuelLost = ship.getFuel();
                ship.setFuel(0);
            } else {
                ship.setFuel(ship.getFuel() - fuelToLose);
                fuelLost = fuelToLose;
            }
        }
        return String.format(Locale.US,
                "You've lost %.2f units of fuel during the malfunction", fuelLost);
    }

    public String doRobbery() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        final int creditsToLose = 50;
        int creditsLost = 0;
        if (player.getCredits() > 0) {
            if (player.getCredits() < creditsToLose) {
                creditsLost = player.getCredits();
                player.setCredits(0);
            } else {
                creditsLost = creditsToLose;
                player.setCredits(player.getCredits() - creditsToLose);
            }
        }
        return String.format(Locale.US,
                "You've lost %d credits due to the robbery", creditsLost);
    }

}
