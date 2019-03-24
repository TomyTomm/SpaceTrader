package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.SolarSystem;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.SolarSystemInteractor;

import java.util.List;

public class SolarSystemViewModel extends AndroidViewModel {

    private SolarSystemInteractor interactor;

    public SolarSystemViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getSolarSystemInteractor();
    }

    public SolarSystem getSolarSystem() {return interactor.getSolarSystem(); }
}
