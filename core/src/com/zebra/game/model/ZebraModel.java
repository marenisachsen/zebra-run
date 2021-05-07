package com.zebra.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.zebra.game.controller.Animation.Animate;
import com.zebra.game.controller.Animation.Run;

public class ZebraModel extends Sprite {

    private static ZebraModel instance = new ZebraModel();

    //zebras main position
    public static final int ZEBRA_X = 1000;
    public static final int ZEBRA_Y = 210;

    //zebra animation width and height
    public static final int ZEBRA_WIDTH = 150;
    public static final int ZEBRA_HEIGHT = 200;

    private Rectangle rectangle;
    private Vector2 zebraPosition;
    private Vector2 velocity;
    private Animate zebra;
    private ZebraState zebraState;


    private ZebraModel(){
        zebraPosition = new Vector2(ZEBRA_X, ZEBRA_Y);
        velocity = new Vector2(0,0);
        rectangle = new Rectangle((int) zebraPosition.x,(int) zebraPosition.y, ZEBRA_WIDTH-25, ZEBRA_HEIGHT);

        zebra = new Run();
        zebraState = ZebraState.RUN;
        zebra.updateAnimation();
    }

    //returns the Singleton zebra object
    public static ZebraModel getInstance() {
        if (instance == null) {
            return new ZebraModel();
        }
        return instance;
    }

    //sets the zebra state
    public void setZebraState(ZebraState zebraState) {
        this.zebraState = zebraState;
    }

    //gets the zebra state
    public ZebraState getZebraState(){
        return zebraState;
    }


    //gets the zebras rectangle
    public Rectangle getRectangle(){ return rectangle; }

    //sets the zebras rectangle
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    //gets the zebras position
    public Vector2 getPosition(){
        return zebraPosition;
    }

    //sets the zebras position
    public void setPosition(Vector2 vector2){
        zebraPosition = vector2;
    }

    //gets the zebras velocity
    public Vector2 getVelocity(){
        return velocity;
    }

    //sets the zebras velocity
    public void setVelocity(Vector2 vector2){
        velocity = vector2;
    }

    //gets the zebras amimation
    public Animate getZebra() {
        return zebra;
    }

    //sets the zebras animation
    public void setZebra(Animate zebra){
        this.zebra = zebra;
        zebra.updateAnimation();
    }

}
