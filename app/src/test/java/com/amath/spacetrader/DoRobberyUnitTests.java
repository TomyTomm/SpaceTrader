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
//Kok Wei "Thomas" Tan
public class DoRobberyUnitTests {

    private static Game game;
    private static Player player;
    private static RandomEventInteractor randomEventInteractor;

    @BeforeClass
    public static void setUp() {
        Model model = Model.TEST_CreateInstance();
        player = new Player("playerName", 4, 4, 4, 4);
        model.getMyRepository().newGame(new Game());
        model.loadPlayer(player);
        Repository repo = model.getMyRepository();
        randomEventInteractor = new RandomEventInteractor(repo);
    }

    @Test
    public void testRobbery0Credits() {
        int credits = 0;
        player.setCredits(credits);
        assertEquals("player does not have correct credits", credits,
                player.getCredits());
        assertEquals("You've lost 0 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()",
                0, player.getCredits());
    }

    @Test
    public void testRobbery30Credits() {
        int credits = 30;
        player.setCredits(credits);
        assertEquals("player does not have correct credits", credits,
                player.getCredits());
        assertEquals("You've lost 30 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()",
                0, player.getCredits());
    }

    @Test
    public void testRobbery50Credits() {
        int credits = 50;
        player.setCredits(credits);
        assertEquals("player does not have correct credits", credits,
                player.getCredits());
        assertEquals("You've lost 50 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()",
                0, player.getCredits());
    }

    @Test
    public void testRobberyWith1000Credits() {
        int credits = 1000;
        player.setCredits(credits);
        assertEquals("player does not have correct credits", credits,
                player.getCredits());
        assertEquals("You've lost 50 credits due to the robbery",
                randomEventInteractor.doRobbery());
        assertEquals("player does not have correct credits after doRobbery()",
                credits - 50, player.getCredits());
    }
}
