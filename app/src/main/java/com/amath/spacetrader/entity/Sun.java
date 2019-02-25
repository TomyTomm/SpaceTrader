package com.amath.spacetrader.entity;

import java.util.Random;

public class Sun {
    private int size;
    private static final int MAX_SIZE = 15;
    public Sun() {
        this((int) (10 + Math.random() * MAX_SIZE));
    }
    public Sun(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
