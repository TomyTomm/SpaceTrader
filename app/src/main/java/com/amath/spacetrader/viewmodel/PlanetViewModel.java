package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.PlanetInteractor;

public class PlanetViewModel extends AndroidViewModel {

    PlanetInteractor interactor;

    public PlanetViewModel(@NonNull Application application) {
        super(application);

        interactor = Model.getInstance().getPlanetInteractor();
    }
}
