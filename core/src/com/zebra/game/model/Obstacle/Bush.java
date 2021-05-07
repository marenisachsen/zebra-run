package com.zebra.game.model.Obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.Animation.Poof;

public class Bush implements ObstacleModel {

    public final static int BUSH_WIDTH = 100;
    public final static int BUSH_HEIGHT = 90;

    private Texture bushTexture;
    private Vector2 bushPosition, bushVelocity;
    private Rectangle bushRectangle;


    public Bush(float x) {
        bushTexture = MyZebraGame.resources.getImage("bush");
        bushPosition = new Vector2(x, 210);
        bushVelocity = new Vector2(-2, 0);
        bushRectangle = new Rectangle(bushPosition.x+2, bushPosition.y, BUSH_WIDTH-4, BUSH_HEIGHT-2);
    }


    @Override
    public Texture getTexture() {
        return bushTexture;
    }

    @Override
    public float getHeight() {
        return BUSH_HEIGHT;
    }

    @Override
    public float getWidth() {
        return BUSH_WIDTH;
    }

    @Override
    public Vector2 getPosition() {
        return bushPosition;
    }

    @Override
    public void setPosition(Vector2 vector2) {
        bushPosition = vector2;
    }

    @Override
    public Vector2 getVelocity() {
        return bushVelocity;
    }

    @Override
    public void setVelocity(Vector2 vector2) { this.bushVelocity = vector2; }

    @Override
    public Rectangle getRectangle() {
        return bushRectangle;
    }

}
