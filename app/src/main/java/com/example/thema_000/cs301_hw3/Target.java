package com.example.thema_000.cs301_hw3;

import java.util.Random;

/**
 * Created by thema_000 on 4/5/2017.
 */

public class Target {

    //radius and pos
    private float radius = 50.0f;
    private float xCenter, yCenter;

    public Target()
    {
        //make a target at a random pos
        Random rand = new Random();

        xCenter = rand.nextFloat()*1400.0f + 200;
        yCenter = rand.nextFloat()*1200.0f + 100;

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
