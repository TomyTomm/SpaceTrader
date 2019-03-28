package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.model.FlightInteractor;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.PlanetInteractor;

public class PlanetViewModel extends AndroidViewModel {

    PlanetInteractor planetInteractor;
    FlightInteractor flightInteractor;

    public PlanetViewModel(@NonNull Application application) {
        super(application);

        planetInteractor = Model.getInstance().getPlanetInteractor();
        flightInteractor = Model.getInstance().getFlightInteractor();
    }

    public boolean verifyFly(Planet planet, double distance) throws IllegalFlyException {

        double currentFuel = flightInteractor.getRemainingFuel();
        boolean canFly = false;

        if (distance > currentFuel) {
            throw new IllegalFlyException(String.format("You cannot fly to %s, you do not have enough fuel", planet.getName()));
        } else {
            canFly = true;
            flightInteractor.flyTo(planet, distance);
        }
        return canFly;
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
