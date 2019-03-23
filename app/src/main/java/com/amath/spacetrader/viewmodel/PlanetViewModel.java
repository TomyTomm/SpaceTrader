package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.FlightInteractor;

public class PlanetViewModel extends AndroidViewModel {

    private FlightInteractor interactor;
    private Planet viewedPlanet;

    public PlanetViewModel(@NonNull Application application) {
        super(application);

        interactor = Model.getInstance().getFlightInteractor();

    }
}
