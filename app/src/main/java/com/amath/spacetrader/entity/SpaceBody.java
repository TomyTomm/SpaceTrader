package com.amath.spacetrader.entity;

import android.util.Log;

public abstract class SpaceBody {
    protected String name;
    protected double radius;
    protected Coordinate location;

    public abstract Coordinate getLocation();

    public abstract double getRadius();

    protected boolean overlap(Coordinate tempLocation, SpaceBody body) {
        //get coordinate of system
        Coordinate solarCoor = body.getLocation();
        double solarX = solarCoor.getX();
        double solarY = solarCoor.getY();
        double solarRad = body.getRadius();

        //get coordinate of temp
        double x = tempLocation.getX();
        double y = tempLocation.getY();

        //distance between centers
        double distance = Math.sqrt(Math.pow(x - solarX, 2) + Math.pow(y - solarY, 2));


        //If distance between centers is less than the sum of the two radii, then they overlap
//        Log.d("oopsie!", String.format("(%f, %f), R:%f \n(%f, %f), R:%f%n", solarX, solarY, solarRad, x, y, radius));
        if (distance < solarRad + radius) {
            Log.d("oopsie!", "overlapped haha");
            Log.d("oopsie!", String.format("(%f, %f), R:%f \n(%f, %f), R:%f%n", solarX, solarY, solarRad, x, y, radius));
            return true;
        }
        return false;
    }
}
