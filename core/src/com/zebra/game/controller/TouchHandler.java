package com.zebra.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zebra.game.MyZebraGame;
import com.zebra.game.model.Obstacle.ObstacleModel;
import com.zebra.game.view.PlayView;


public class TouchHandler {
    private ZebraController zebracontroller;
    private Rectangle touchRectangle;
    //booleans to control touch actions
    private boolean buttonCliked = false;
    private boolean soundBtnClick = false;
    private boolean obstacleTouched = false;

    public TouchHandler(ZebraController zebraController) {
        zebracontroller = zebraController;
    }

    //calls action if user touch is registered
    public void handleTouch(ObstacleModel obstaclemodel, Viewport viewport){
        //if the screen is touched and not in the area of a button --> do this
        if (Gdx.input.justTouched() && !buttonCliked){
            obstacleTouched = false;
            //registers the coordinates of the touch and unprojects it to our screen
            Vector2 touchPoint = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            touchPoint = viewport.unproject(touchPoint);
            //creates a rectangle at the size of a finger touch
            touchRectangle = new Rectangle(touchPoint.x-20, touchPoint.y-20, 40, 40);

            //registers button area touches
            if(touchPoint.x < 70){
                if(touchPoint.y > MyZebraGame.WORLD_HEIGHT-80){
                    buttonCliked = true;
                    soundBtnClick = true;
                }
            }

            if(touchPoint.x > 2050){
                if(touchPoint.y > MyZebraGame.WORLD_HEIGHT-80){
                    buttonCliked = true;
                }
            }
            //denies touches if button areas are touched
            if (touchRectangle.overlaps(obstaclemodel.getRectangle()) && !buttonCliked) {
                buttonCliked = true;
                soundBtnClick = true;
                obstacleTouched = true;
            }
            //if the left side is touched, the zebra jumps
            if (touchPoint.x < MyZebraGame.WORLD_WIDTH / 2 && !buttonCliked) {
                zebracontroller.zebraJump();
            }
            //if the right side is touched, the zebra slides
            if (touchPoint.x >= MyZebraGame.WORLD_WIDTH / 2 && !buttonCliked) {
                zebracontroller.zebraSlide();
            }
            //resets button touch logic
            if(soundBtnClick){
                soundBtnClick = false;
                buttonCliked = false;
            }
        }
    }

    //sets the boolean ButtonCliked
    public void setButtonCliked(boolean buttonCliked) {
        this.buttonCliked = buttonCliked;
    }

}


