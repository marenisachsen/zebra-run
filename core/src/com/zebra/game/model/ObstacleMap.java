package com.zebra.game.model;

import com.zebra.game.model.Obstacle.ObstacleModel;

import com.zebra.game.model.Obstacle.ObstacleFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObstacleMap {

    public static final int OBSTACLES_COUNT = 10;


    private List<ObstacleModel> obstacles;
    private ObstacleFactory obstaclefactory;

    private Random random;

    public ObstacleMap() {
        obstacles = new ArrayList<>();
        obstaclefactory = new ObstacleFactory();
        random = new Random();
        creatingObstacles();
    }

    //creates random obstacles and add them to the obstacles list
    public void creatingObstacles() {
        for (int i = 0; i < OBSTACLES_COUNT; i++) {
            obstacles.add(obstaclefactory.getObstacle(random.nextInt(3),i*1000 + 2000));
        }
    }

    //gets the obstacles list
    public List<ObstacleModel> getObstacles() {
        return obstacles;
    }
}
