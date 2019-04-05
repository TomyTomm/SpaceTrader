package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.RandomEvent;
import com.amath.spacetrader.model.RandomEventInteractor;
import com.amath.spacetrader.model.Model;

import java.util.Random;

public class RandomEventViewModel extends AndroidViewModel {

    private final RandomEventInteractor interactor;

    public RandomEventViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getRandomEventInteractor();
    }

    public String executeEvent(RandomEvent randomEvent) {
        if (randomEvent == RandomEvent.BLACK_HOLE) {
            return interactor.doBlackHole();
        }
        if (randomEvent == RandomEvent.CREW_MUTINY) {
            return interactor.doCrewMutiny();
        }
        if (randomEvent == RandomEvent.SHIP_MALFUNCTION) {
            return interactor.doShipMalfunction();
        }
        if (randomEvent == RandomEvent.ROBBERY) {
            return interactor.doRobbery();
        }
        return "could not find the correct event";
    }

    public String executeResultEvent(RandomEvent randomEvent) {
        if (randomEvent == RandomEvent.CREW_MUTINY) {
            return interactor.displayCrewMutiny();
        }
        if (randomEvent == RandomEvent.SHIP_MALFUNCTION) {
            return interactor.displayMalfunction();
        }
        if (randomEvent == RandomEvent.ROBBERY) {
            return interactor.displayRobbery();
        }
        return "could not find the correct event";
    }
}
