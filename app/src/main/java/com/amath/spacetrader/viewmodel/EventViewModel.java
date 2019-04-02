package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.model.EventInteractor;
import com.amath.spacetrader.model.Model;

public class EventViewModel extends AndroidViewModel {

    private EventInteractor interactor;

    public EventViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getEventInteractor();
    }
}
