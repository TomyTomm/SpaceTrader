package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.model.ConfigurationInteractor;
import com.amath.spacetrader.model.Model;

public class ConfigurationViewModel extends AndroidViewModel {

    private ConfigurationInteractor interactor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getConfigurationInteractor();
    }

    public void newGame(Game game) {
        interactor.newGame(game);
    }
}
