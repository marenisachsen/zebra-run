package com.zebra.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.zebra.game.model.Obstacle.ObstacleModel;

import java.util.List;

public class ObstacleController {

    public List<ObstacleModel> obstacles;

    public ObstacleController(List<ObstacleModel> obstacles) {
        this.obstacles = obstacles;
    }


    //returns the velocity of the obstacles
    public Vector2 getVelocity() {
        return obstacles.get(0).getVelocity();
    }

    //updates the velocity of all of the obsacles with the given velocity in the input
    public void updateVelocity(Vector2 velocity){
        for (ObstacleModel obstaclemodel : obstacles) {
            obstaclemodel.setVelocity(velocity);
        }
    }

    //updates the position of all of the obstacles with the given obstacle velocity
    public void updatePosition() {
        for (ObstacleModel obstaclemodel : obstacles) {
            obstaclemodel.getPosition().add(obstaclemodel.getVelocity());
        }
    }

    //updates the rectangle of all the obstacles
    public void updateRectangle(){
        for (ObstacleModel obstaclemodel : obstacles) {
            obstaclemodel.getRectangle().setPosition(obstaclemodel.getPosition().x, obstaclemodel.getPosition().y);
        }
    }

    //main update function
    public void update() {
        updatePosition();
        updateRectangle();
    }

}
