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

    public String doPirates() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        double character, pirate;
        character = Math.random() * 10;
        pirate = Math.random() * 10;
        boolean gotAway = character >= pirate;

        final int creditsToLose = 50;
        int creditsLost = 0;
        if (player.getCredits() > 0 && !gotAway) {
            if (player.getCredits() < creditsToLose) {
                creditsLost = player.getCredits();
                player.setCredits(0);
            } else {
                creditsLost = creditsToLose;
                player.setCredits(player.getCredits() - creditsToLose);
            }
        }
        return String.format(Locale.US,
                "Pirates stole %d credits from you.", creditsLost);
    }

    public String doPolice() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        double character, police;
        character = Math.random() * 10;
        police = 5;
        boolean justiceServed = character < police;

        final int creditsToLose = 50;
        int creditsLost = 0;
        if (player.getCredits() > 0 && justiceServed) {
            if (player.getCredits() < creditsToLose) {
                creditsLost = player.getCredits();
                player.setCredits(0);
            } else {
                creditsLost = creditsToLose;
                player.setCredits(player.getCredits() - creditsToLose);
            }
        }
        return String.format(Locale.US,
                "Police fined you %d credits. Corruption at its worst!", creditsLost);
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

    public String displayPirates() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        return String.format("You have %d credits left.", player.getCredits());
    }

    public String displayPolice() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();

        return String.format("You have %d credits left.", player.getCredits());
    }
}
