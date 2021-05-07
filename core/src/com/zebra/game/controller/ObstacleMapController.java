package com.zebra.game.controller;


import com.badlogic.gdx.math.Vector2;
import com.zebra.game.model.Obstacle.ObstacleModel;
import com.zebra.game.model.ObstacleMap;

public class ObstacleMapController {

    ObstacleMap obstaclemap;


    public ObstacleMapController(ObstacleMap obstaclemap) {
        this.obstaclemap = obstaclemap;
    }


    //updates the obstacles and will move them to the back when off screen
    public void updateObstacles(){
        for (ObstacleModel obstaclemodel : obstaclemap.getObstacles()) {
            if (obstaclemodel.getPosition().x < -obstaclemodel.getWidth()) {
                obstaclemodel.setPosition(new Vector2(obstaclemap.OBSTACLES_COUNT*1000 + obstaclemodel.getPosition().x, obstaclemodel.getPosition().y));
            }
        }
    }

}
