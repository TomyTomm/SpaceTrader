package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.model.FlightInteractor;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.PlanetInteractor;

import java.io.File;

public class PlanetViewModel extends AndroidViewModel {

    PlanetInteractor planetInteractor;
    FlightInteractor flightInteractor;

    public PlanetViewModel(@NonNull Application application) {
        super(application);

        planetInteractor = Model.getInstance().getPlanetInteractor();
        flightInteractor = Model.getInstance().getFlightInteractor();
    }

    public boolean verifyFly(Planet planet, double distance) {
        return distance < flightInteractor.getRemainingFuel();
    }

    public void fly(Planet planet, double distance, File file) throws IllegalFlyException {
        if (!verifyFly(planet, distance)) {
            throw new IllegalFlyException("Not enough fuel to fly. ");
        }
        flightInteractor.flyTo(planet, distance, file);

    }

    private double getCurrentFuel() {
        return flightInteractor.getRemainingFuel();
    }

    /**
     * Please use this exception class. Look at ConfigurationViewModel
     * as an example
     *
     */
    public class IllegalFlyException extends Exception {
        private IllegalFlyException(String message) {
            super(message);
        }
    }
}
