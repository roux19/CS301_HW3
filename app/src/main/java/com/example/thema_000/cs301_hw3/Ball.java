package com.example.thema_000.cs301_hw3;

/**
 * Created by thema_000 on 4/5/2017.
 */

public class Ball {

    //total velocity of all balls
    private float totalVel = 40.0f;

    //varibles for pos velocity and radius
    private float curX, curY;
    private float velX, velY;
    private float radius = 25.0f;

    //limits (where to bounse)
    private float maxX, maxY;

    public Ball( float initX, float initY, double angle, float initMaxY, float initMaxX )
    {
        //set pos and maxes
        curX = initX;
        curY = initY;

        this.maxX = initMaxX;
        this.maxY = initMaxY;

        //set velocity with respect to angle
        velX = (float)Math.cos(angle)*totalVel;
        velY = -(float)Math.sin(angle)*totalVel;
    }

    public void tick()
    {
        //move according to vellocity
        curX += velX;
        curY += velY;

        //gravity
        velY += 1.0f;

        //bounsing
        if ( curX < radius ) velX = Math.abs(velX);
        if ( curX > maxX-radius ) velX = -Math.abs(velX);
        if ( curY < radius ) velY = Math.abs(velY);
        if ( curY > maxY-radius ) velY = -Math.abs(velY);

    }


    //getters
    public float getLeft()
    {
        return curX-radius;
    }

    public float getTop()
    {
        return curY-radius;
    }

    public float getRight()
    {
        return curX+radius;
    }

    public float getBot()
    {
        return curY+radius;
    }

    public float getRadius() {
        return radius;
    }

    public float getCurX() {
        return curX;
    }

    public float getCurY() {
        return curY;
    }
}
