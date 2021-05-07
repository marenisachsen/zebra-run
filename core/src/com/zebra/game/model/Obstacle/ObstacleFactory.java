package com.zebra.game.model.Obstacle;

import com.zebra.game.MyZebraGame;

import java.util.Random;

public class ObstacleFactory {

    //returns an different obstacle object for different i. Return the obstacle at position x.
    public ObstacleModel getObstacle(int i, float x) {

        if (i == 0) {
            return new Bush(x);
        }

        else if (i == 1) {
            return new Cloud(x);
        }

        else if (i == 2) {
            return new Tree(x);
        }

        else {
            return new Bush(x);
        }
    }

}