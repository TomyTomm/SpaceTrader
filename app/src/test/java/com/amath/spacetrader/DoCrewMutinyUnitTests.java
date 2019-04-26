package com.amath.spacetrader;

import android.support.v4.view.ViewCompat;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.Good;
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

public class DoCrewMutinyUnitTests {
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
        player.removeGood(Good.FOOD, player.getGoodAmount(Good.FOOD));
    }

    @Test
    public void boundaryFoodTest() {
        player.removeGood(Good.FOOD, player.getGoodAmount(Good.FOOD));
        int food = player.getGoodAmount(Good.FOOD);
        assertEquals("Food not zero.", 0, food);
        player.addGood(Good.FOOD, 1);
        food = player.getGoodAmount(Good.FOOD);
        randomEventInteractor.doCrewMutiny();
        int postFood = player.getGoodAmount(Good.FOOD);
        assertEquals("Boundary case (1 food) failed.", food - 1, postFood);
    }

    @Test
    public void zeroFoodTest() {
        int food = player.getGoodAmount(Good.FOOD);
        assertEquals("Food not zero.", 0, food);
        int postFood;
        randomEventInteractor.doCrewMutiny();
        postFood = player.getGoodAmount(Good.FOOD);
        assertEquals("Zero case failed.", food, postFood);
    }

    @Test
    public void positiveFoodTest() {
        int food = player.getGoodAmount(Good.FOOD);
        assertEquals("Food not zero.", 0, food);
        player.addGood(Good.FOOD, 10);
        food = player.getGoodAmount(Good.FOOD);
        randomEventInteractor.doCrewMutiny();
        int postFood = player.getGoodAmount(Good.FOOD);
        assertEquals("Positive, non-boundary case (10 food) failed.", food - 1, postFood);
    }



    @Test
    public void justAboveBoundaryFoodTest() {
        player.removeGood(Good.FOOD, player.getGoodAmount(Good.FOOD));
        int food = player.getGoodAmount(Good.FOOD);
        assertEquals("Food not zero.", 0, food);
        player.addGood(Good.FOOD, 2);
        food = player.getGoodAmount(Good.FOOD);
        randomEventInteractor.doCrewMutiny();
        int postFood = player.getGoodAmount(Good.FOOD);
        assertEquals("Edge case (2 food) failed.", food - 1, postFood);
    }

}
