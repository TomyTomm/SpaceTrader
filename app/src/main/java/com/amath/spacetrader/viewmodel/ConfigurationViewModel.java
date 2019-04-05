package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amath.spacetrader.entity.Constants;
import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.model.ConfigurationInteractor;
import com.amath.spacetrader.model.Model;

import java.io.File;

public class ConfigurationViewModel extends AndroidViewModel {

    private final ConfigurationInteractor interactor;

    private class IllegalPlayerInformationException extends Exception {
        public IllegalPlayerInformationException(String message) {
            super(message);
        }
    }

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getConfigurationInteractor();
    }

    public String loadPlayer(String name, int pilotPts, int traderPts, int engineerPts,
                             int fighterPts) throws IllegalPlayerInformationException {
        int maxNameLength = 32;
        if (name.length() == 0 || name.length() > maxNameLength) {
            throw new IllegalPlayerInformationException
                    ("Couldn't create character: name must be between 0 and 32 characters");
                    // 1 means name not valid
        } else if (pilotPts < 0 || pilotPts > Constants.TOTAL_SKILL_POINTS) {
            throw new IllegalPlayerInformationException
                    ("Pilot points must be greater than 0 and less than 16");
        } else if (traderPts < 0 || traderPts > Constants.TOTAL_SKILL_POINTS) {
            throw new IllegalPlayerInformationException
                    ("Trader points must be greater than 0 and less than 16");
        } else if (engineerPts < 0 || engineerPts > Constants.TOTAL_SKILL_POINTS) {
            throw new IllegalPlayerInformationException
                    ("Engineer points must be greater than 0 and less than 16");
        } else if (fighterPts < 0 || fighterPts > Constants.TOTAL_SKILL_POINTS) {
            throw new IllegalPlayerInformationException
                    ("Fighter points must be greater than 0 and less than 16");
        } else if (pilotPts + traderPts + engineerPts + fighterPts
                != Constants.TOTAL_SKILL_POINTS) {
            throw new IllegalPlayerInformationException("Points must total to 16");
        }
//        return "";//interactor.getGame().toString();
        Player p = new Player(name, pilotPts, traderPts, engineerPts, fighterPts);
        interactor.loadPlayer(p);

//        return p.toString(); Used for toast, but makes an illegal Player object
        return p.toString();
    }

    public boolean saveGameLocally(File file) {
        return interactor.saveLocalGame(file);
    }

    public void loadDifficulty(GameDifficulty difficulty) {
        Model.loadDifficulty(difficulty);
    }

    /* public void newGame(Game game) {
        interactor.newGame(game);
    } */
}
