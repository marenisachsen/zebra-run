package com.zebra.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.MyZebraGame;

public class CurtainModel{

    private Texture curtainLTexture;
    private Texture curtainRTexture;
    private Vector2 curtainLeft, curtainRight;
    private float curtainWidth;

    //constructor to initialize the graphical resources and values of the curtains
    public CurtainModel(){
        curtainLTexture = MyZebraGame.resources.getImage("curtainL");
        curtainRTexture = MyZebraGame.resources.getImage("curtainR");
        curtainLeft = new Vector2(-curtainLTexture.getWidth(), 0);
        curtainRight = new Vector2(MyZebraGame.WORLD_WIDTH, 0);
        curtainWidth = curtainLTexture.getWidth();
    }

    //gets the right curtain texture
    public Texture getRCurtain(){
    return curtainRTexture;
    }

    //gets the left curtain texture
    public Texture getLCurtain(){
        return curtainLTexture;
    }

    //gets the curtain width
    public float getWidth() { return curtainWidth; }

    //sets the curtain width
    public void setCurtainWidth(float curtainWidth) { this.curtainWidth = curtainWidth; }

    //gets the right curtain position
    public Vector2 getCurtainRightPos(){
        return curtainRight;
    }

    //gets the left curtain position
    public Vector2 getCurtainLeftPos(){
        return curtainLeft;
    }

    public void setCurtainLeft(Vector2 curtainLeft) { this.curtainLeft = curtainLeft; }

    public void setCurtainRight(Vector2 curtainRight) { this.curtainRight = curtainRight; }
}
