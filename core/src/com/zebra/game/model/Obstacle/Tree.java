package com.zebra.game.model.Obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.MyZebraGame;

public class Tree implements ObstacleModel {

    public static final int TREE_WIDTH = 80;
    public static final int TREE_HEIGHT = 80;

    private Texture treeTexture;
    private Vector2 treePosition, treeVelocity;
    private Rectangle treeRectangle;


    public Tree(float x) {
        treeTexture = MyZebraGame.resources.getImage("treestump");
        treePosition = new Vector2(x, 210);
        treeVelocity = new Vector2(-2, 0);
        treeRectangle = new Rectangle(treePosition.x + 2, treePosition.y, TREE_WIDTH - 4, TREE_HEIGHT - 2);

    }


    @Override
    public Texture getTexture() { return treeTexture; }

    @Override
    public float getHeight() {
        return TREE_HEIGHT;
    }

    @Override
    public float getWidth() {
        return TREE_WIDTH;
    }

    @Override
    public Vector2 getPosition() {
        return treePosition;
    }

    @Override
    public void setPosition(Vector2 vector2) {
        treePosition = vector2;
    }

    @Override
    public Vector2 getVelocity() {
        return treeVelocity;
    }

    @Override
    public void setVelocity(Vector2 vector2) { this.treeVelocity = vector2; }

    @Override
    public Rectangle getRectangle() {
        return treeRectangle;
    }

}

