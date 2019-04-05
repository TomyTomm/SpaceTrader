package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Game;
import com.amath.spacetrader.model.MainInteractor;
import com.amath.spacetrader.model.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MainViewModel extends AndroidViewModel {
    private final MainInteractor interactor;

    public MainViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getMainInteractor();
    }

    public boolean loadLocalGame(File file) {
        return interactor.loadGame(file);
    }
}
