package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Universe;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.UniverseInteractor;

import java.util.List;

public class UniverseViewModel extends AndroidViewModel {

    private final UniverseInteractor interactor;

    public UniverseViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getUniverseInteractor();
    }

    public Universe getUniverse() {
        return interactor.getUniverse();
    }

    public String getCurrentSolarSystemName() {
        return interactor.getCurrentSolarSystemName();
    }
}
