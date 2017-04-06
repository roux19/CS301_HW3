package com.example.thema_000.cs301_hw3;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;

import java.util.Random;

/**
 * Created by thema_000 on 4/5/2017.
 */

public class Cannon implements Animator {
    //instance variables for touch events
    private float origX, origY;
    private boolean isMove;

    //ball color will be midified
    private int ballColor = 0xFF000000;

    //variables to keep track of cannon placement
    private float topLeftX, topLeftY, bottomRightX, bottomRightY;

    //varibles for the size of the canvas
    private float canvasHegiht, canvasWidth;

    //varibles for cannon pos
    private float cannonWidth = 50.0f;
    private float cannonLength = 500.0f;
    private double cannonAngle = 3.1459/4;

    //paints
    Paint cannonPaint = new Paint();
    Paint ballPaint = new Paint();
    Paint targetPaint = new Paint();

    //targets
    private Target[] targets = new Target[2];

    //ball
    private Ball ball;

    @Override
    public int interval() {
        return 30;
    }

    @Override
    public int backgroundColor() {
        return 0xFF00AAFF;
    }

    @Override
    public boolean doPause() {
        return false;
    }

    @Override
    public boolean doQuit() {
        return false;
    }

    @Override
    public void tick(Canvas canvas) {

        //go through the targets
        for( int i = 0; i < 2; i++) {

            //if this target exists and there is a ball
            if (targets[i] != null && ball != null) {
                //get the distance between them
                float dist = (float) Math.sqrt((ball.getCurX()-targets[i].getxCenter()) * (ball.getCurX()-targets[i].getxCenter())
                        + (ball.getCurY()-targets[i].getyCenter()) * (ball.getCurY()-targets[i].getyCenter()));

                //if the distance is less than their combined radius
                if (dist < ball.getRadius()+targets[i].getRadius())
                {
                    Random rand = new Random();
                    targets[i] = null; //nullify target
                    ballColor = rand.nextInt(0xFFFFF) + 0xFF000000; //set random ball collor
                }
            }

            //if the target is null (just started or target was hit)
            if (targets[i] == null) {
                targets[i] = new Target(); //make a new target
            }
        }

        //set canvas height and width
        canvasHegiht = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        //set paint colors
        cannonPaint.setColor(0xFF333333);
        ballPaint.setColor(ballColor);
        targetPaint.setColor(0xFFFF0000);


        //calculate cannon positions
        topLeftX = (float)Math.cos(3.1459/2 + cannonAngle)*cannonWidth;
        topLeftY = (float)Math.sin(3.1459/2 + cannonAngle)*cannonWidth;
        bottomRightX = (float)Math.cos(cannonAngle)*cannonLength;
        bottomRightY = (float)Math.sin(cannonAngle)*cannonLength;

        //make a shape for the cannon
        Path cannon = new Path();
        cannon.moveTo(0.0f, canvas.getHeight());
        cannon.lineTo(topLeftX, canvas.getHeight() - topLeftY);
        cannon.lineTo(bottomRightX+topLeftX, canvas.getHeight() - (bottomRightY + topLeftY));
        cannon.lineTo(bottomRightX, canvas.getHeight() - bottomRightY);
        cannon.lineTo(0.0f, canvas.getHeight());

        //if there's a ball
        if ( ball != null ) {
            //move it and draw it
            ball.tick();
            canvas.drawOval(ball.getLeft(), ball.getTop(), ball.getRight(), ball.getBot(), ballPaint);
        }
        for ( Target t : targets )
        {
            //draw the targets
            canvas.drawOval(t.getLeft(), t.getTop(), t.getRight(), t.getBot(), targetPaint);
        }
        //draw the cannon
        canvas.drawPath(cannon, cannonPaint);


    }

    private void fire() {

        //make a ball at the tip of the cannon
        ball = new Ball( bottomRightX+topLeftX/2, canvasHegiht-bottomRightY-topLeftY/2, cannonAngle,
                canvasHegiht, canvasWidth);

    }

    @Override
    public void onTouch(MotionEvent e) {

        float curX, curY;

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN: //if we are first touching the screen
                //record varibles into instance variables
                origX = e.getX();
                origY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE: //we have movement!!
                //if there is more than 50 movement from the original location, set isMove to true
                //this signifies that it is a move instead of a tap
                if (Math.abs(e.getX() - origX) > 50 || Math.abs(e.getY() - origY) > 50) {
                    isMove = true;
                }
                //record the current values
                curX = e.getX();
                curY = e.getY();
                //set cannon angle to the angle of the touch
                cannonAngle = Math.atan((canvasHegiht-curY)/curX);
                break;
            case MotionEvent.ACTION_UP: //if relaese of screen
                if (isMove) { //if we are a move
                    isMove = false; //reset is move to false
                }
                else
                {
                    //if we arn't a move then make a ball
                    fire();
                }
        }

    }
}
