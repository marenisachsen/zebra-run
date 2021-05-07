package com.zebra.game.model.Obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.controller.Animation.Poof;

public interface ObstacleModel {

    //gets the texture to the obstacle
    Texture getTexture();

    //gets the height to the obstacle
    float getHeight();

    //gets the width to the obstacle
    float getWidth();

    //gets the position to the obstacle
    Vector2 getPosition();

    //sets the position to the obstacle to the given Vector2
    void setPosition(Vector2 vector2);

    //gets the velocity to the obstacle
    Vector2 getVelocity();

    //sets the height to the obstacle to the given Vector2
    void setVelocity(Vector2 vector2);

    //gets the rectangle to the obstacle
    Rectangle getRectangle();

}
