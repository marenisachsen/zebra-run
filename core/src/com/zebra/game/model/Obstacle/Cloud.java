package com.zebra.game.model.Obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.Animation.Poof;

public class Cloud implements ObstacleModel {

    public static final int CLOUD_WIDTH = 100;
    public static final int CLOUD_HEIGHT = 70;

    private Texture cloudTexture;
    private Vector2 cloudPosition, cloudVelocity;
    private Rectangle cloudRectangle;


    public Cloud(float x) {
        cloudTexture = MyZebraGame.resources.getImage("cloud");
        cloudPosition = new Vector2(x,370);
        cloudVelocity = new Vector2(-2,0);
        cloudRectangle = new Rectangle(cloudPosition.x, cloudPosition.y, CLOUD_WIDTH, CLOUD_HEIGHT);
    }


    @Override
    public Texture getTexture() {
        return cloudTexture;
    }

    @Override
    public float getHeight() {
        return CLOUD_HEIGHT;
    }

    @Override
    public float getWidth() {
        return CLOUD_WIDTH;
    }

    @Override
    public Vector2 getPosition() { return cloudPosition; }

    @Override
    public void setPosition(Vector2 vector2) { cloudPosition = vector2; }

    @Override
    public Vector2 getVelocity() {
        return cloudVelocity;
    }

    @Override
    public void setVelocity(Vector2 vector2) { this.cloudVelocity = vector2; }

    @Override
    public Rectangle getRectangle() { return cloudRectangle; }

}

