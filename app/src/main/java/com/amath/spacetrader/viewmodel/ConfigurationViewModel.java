package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.entity.GameDifficulty;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.model.ConfigurationInteractor;
import com.amath.spacetrader.model.Model;

public class ConfigurationViewModel extends AndroidViewModel {

    private ConfigurationInteractor interactor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getConfigurationInteractor();
    }

    public String loadPlayer(String name, int pilotPts, int traderPts, int engineerPts, int fighterPts) {
        if (name.length() == 0 || name.length() > 32) {
            return "Couldn't create character: name must be between 0 and 32 characters"; // 1 means name not valid
        } else if (pilotPts < 0 || pilotPts > 16) {
            return "Pilot points must be greater than 0 and less than 16";
        } else if (traderPts < 0 || traderPts > 16) {
            return "Trader points must be greater than 0 and less than 16";
        } else if (engineerPts < 0 || engineerPts > 16) {
            return "Engineer points must be greater than 0 and less than 16";
        } else if (fighterPts < 0 || fighterPts > 16) {
            return "Fighter points must be greater than 0 and less than 16";
        } else if (pilotPts + traderPts + engineerPts + fighterPts > 16) {
            return "Points must total equal to or less than 16";
        }
//        return "";//interactor.getGame().toString();
        interactor.loadPlayer(new Player(name, pilotPts, traderPts, engineerPts, fighterPts));

        return null;
    }

    public void loadDifficulty(GameDifficulty difficulty) {
        Model.loadDifficulty(difficulty);
    }

    /* public void newGame(Game game) {
        interactor.newGame(game);
    } */
}
