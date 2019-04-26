package com.amath.spacetrader.model;

import com.amath.spacetrader.entity.Planet;
import com.amath.spacetrader.entity.Player;
import com.amath.spacetrader.entity.Weapon;

public class PlayerInteractor extends Interactor {
    protected PlayerInteractor(Repository repo) {
        super(repo);
    }

    public String getPlayerName() {
        Model model = Model.getInstance();
        return model.getPlayer().getName();
    }

    public String getCurrentPlanetName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getName();
    }

    public String getCurrentSolarSystemName() {
        Model model = Model.getInstance();
        return model.getGame().getCurrentPlanet().getSolarSystem();
    }

    public double getFuelRemaining() {
        Model model = Model.getInstance();
        return model.getPlayer().getShip().getFuel();
    }
    public int getInventorySize() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        return player.getInventorySize();
    }

    public int getInventoryCapacity() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        return player.getInventoryCapacity();
    }

    public Weapon getPlayerWeapon() {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        return player.getChosenWeapon();
    }

    public Weapon setPlayerWeapon(Weapon weapon) {
        Model model = Model.getInstance();
        Player player = model.getPlayer();
        Weapon ret = player.getChosenWeapon();
        player.setChosenWeapon(weapon);
        return ret;
    }
}
