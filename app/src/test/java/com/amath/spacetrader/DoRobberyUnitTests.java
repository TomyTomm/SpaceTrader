package com.amath.spacetrader;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.RandomEvent;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.RandomEventInteractor;
import com.amath.spacetrader.model.Repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoRobberyUnitTests {

    private static Game game;
    private static Player player;
    private static Repository repo;
    private static RandomEventInteractor randomEventInteractor;
    private static Model model;

    @BeforeClass
    public static void setUp() {
        model = Model.TEST_CreateInstance();
        player = new Player("playerName", 4, 4, 4, 4);
        model.getMyRepository().newGame(new Game());
        model.loadPlayer(player);
        repo = model.getMyRepository();
        randomEventInteractor = new RandomEventInteractor(repo);

    }

    @Test
    public void testRobbery0Credits() {
        player.setCredits(0);
        assertEquals("player does not have correct credits", 0,
                player.getCredits());
        assertEquals("You've lost 0 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()", 0,
                player.getCredits());
    }

    @Test
    public void testRobbery30Credits() {
        try {
            player.setCredits(30);
            assertEquals("player does not have correct credits", 30,
                    player.getCredits());
            assertEquals("You've lost 30 credits due to the robbery",
                    randomEventInteractor.doRobbery());
            assertEquals("player does not have correct credits after doRobbery()", 0,
                    player.getCredits());
        } catch (Exception e) {

        }
    }

    @Test
    public void testRobbery50Credits() {
        player.setCredits(50);
        assertEquals("player does not have correct credits", 50,
                player.getCredits());
        assertEquals("You've lost 50 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()", 0,
                player.getCredits());
    }

    @Test
    public void testRobberyWith1000Credits() {
        player.setCredits(1000);
        assertEquals("player does not have correct credits", 1000,
                player.getCredits());
        assertEquals("You've lost 50 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()", 950,
                player.getCredits());
    }
}
