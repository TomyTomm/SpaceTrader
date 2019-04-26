package com.amath.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Weapon;
import com.amath.spacetrader.model.Model;
import com.amath.spacetrader.model.PlayerInteractor;

public class PlayerViewModel extends AndroidViewModel {

    PlayerInteractor interactor;

    public PlayerViewModel(@NonNull Application application) {
        super(application);

        interactor = Model.getInstance().getPlayerInteractor();
    }

    public boolean inventoryStatusCheckAndChange(Weapon weapon) {
        int changeInInventorySize = interactor.getPlayerWeapon().getCost() - weapon.getCost();
        int proposed = interactor.getInventoryCapacity() + changeInInventorySize;
        if (proposed < interactor.getInventorySize()) {
            interactor.setPlayerWeapon(interactor.getPlayerWeapon());
            return false;
        } else {
            interactor.setPlayerWeapon(weapon);
            return true;
        }
    }

    public String getPlayerName() {
        return interactor.getPlayerName();
    }

    public String getCurrentPlanetName() {
        return interactor.getCurrentPlanetName();
    }

    public String getCurrentSolarSystemName() {
        return interactor.getCurrentSolarSystemName();
    }

    public double getFuelRemaining() {
        return interactor.getFuelRemaining();
    }

    public int getInventorySize() { return interactor.getInventorySize(); }

    public int getInventoryCapacity() { return interactor.getInventoryCapacity(); }

}
