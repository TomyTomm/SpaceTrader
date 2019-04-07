package com.amath.spacetrader.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.Locale;

public abstract class SpaceBody implements Serializable {
    protected String name;
    protected double radius;
    protected Coordinate location;

    public Coordinate getLocation() { return this.location; }
    public double getRadius() { return this.radius; }

    public SpaceBody(Coordinate location, double radius) {
        if (radius < 0) radius = 0;
        this.location = location;
        this.radius = radius;
    }

    protected boolean overlap(Coordinate tempLocation, SpaceBody body) {
        //get coordinate of system
        if (body == null || tempLocation == null) return false;
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
        //Log.d("oopsie!", String.format(Locale.US, "(%f, %f), R:%f \n(%f, %f), R:%f%n", solarX,
        //solarY, solarRad, x, y, radius));
        if (distance < solarRad + radius) {
            Log.d("oopsie!", "overlapped haha");
            Log.d("oopsie!", String.format(Locale.US,
                    "(%f, %f), R:%f \n(%f, %f), R:%f%n", solarX, solarY, solarRad,
                    x, y, radius));
            return true;
        }
        return false;
    }
}
