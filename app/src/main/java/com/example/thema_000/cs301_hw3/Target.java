package com.example.thema_000.cs301_hw3;

import java.util.Random;

/**
 * Created by thema_000 on 4/5/2017.
 */

public class Target {

    //radius and pos
    private float radius = 50.0f;
    private float xCenter, yCenter;
    private float velX, velY;

    private float maxX, maxY;

    public Target()
    {
        //make a target at a random pos
        Random rand = new Random();

        //make random velocity and position
        velX = rand.nextFloat()*15.0f;
        velY = rand.nextFloat()*15.0f;
        xCenter = rand.nextFloat()*1400.0f + 200;
        yCenter = rand.nextFloat()*1200.0f + 100;

    }

    public void tick()
    {
        xCenter += velX;
        yCenter +=velY;

        //bouncing
        if ( xCenter < 200.0f ) velX = Math.abs(velX);
        if ( xCenter > 1600.0f ) velX = -Math.abs(velX);
        if ( yCenter < 100.0f ) velY = Math.abs(velY);
        if ( yCenter > 1300.0f ) velY = -Math.abs(velY);
    }

    //getters

    public float getLeft()
    {
        return xCenter-radius;
    }

    public float getTop()
    {
        return yCenter-radius;
    }

    public float getRight()
    {
        return xCenter+radius;
    }

    public float getBot()
    {
        return yCenter+radius;
    }

    public float getRadius() {
        return radius;
    }

    public float getxCenter() {
        return xCenter;
    }

    public float getyCenter() {
        return yCenter;
    }
}
