package com.zebra.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.MyZebraGame;
import com.zebra.game.model.CurtainModel;
import com.zebra.game.model.Obstacle.ObstacleModel;




public class GameController {

    private ZebraController zebracontroller;
    private ObstacleController obstaclecontroller;
    private CurtainModel curtainmodel;
    private ObstacleMapController obstaclemapcontroller;

    //variable for controlling the lives of the player
    public boolean poofBoolean = false;
    public float poofPosY;
    public float poofPosX;
    private int score;
    private int lives = 3;
    private int curtainScore = 10000;


    public GameController(ZebraController zebracontroller, ObstacleController obstaclecontroller, CurtainModel curtainmodel, ObstacleMapController obstaclemapcontroller) {
        this.zebracontroller = zebracontroller;
        this.obstaclecontroller = obstaclecontroller;
        this.curtainmodel = curtainmodel;
        this.obstaclemapcontroller = obstaclemapcontroller;
    }


    //updates the different controllers and methods for collide and score
    public void update() {
        zebracontroller.update();
        obstaclecontroller.update();
        obstaclemapcontroller.updateObstacles();
        collide();
        calculateScore();
        updateObstacleVelocity(score);
        updateCurtain();


    }

    //will keep updating the score as long as you have lives
    public void calculateScore(){
        if (lives != 0) {
            score += Gdx.graphics.getDeltaTime() * 100;
        }
    }

    public int getScore(){ return score; }

    public void setScore(int score) { this.score = score; }

    public void setLives(int lives) { this.lives = lives; }

    //will update the velocity of the obstacles everytime you get 500 points
    public void updateObstacleVelocity(int score) {
        if (score % 500 == 0 && obstaclecontroller.getVelocity().x < 10) {
            obstaclecontroller.updateVelocity(new Vector2((obstaclecontroller.getVelocity().x - 0.5f),0 ));
        }
    }

    //method to detect collision
    public void collide() {
        for (ObstacleModel obstaclemodel : obstaclecontroller.obstacles) {
            if (zebracontroller.getZebramodel().getRectangle().overlaps(obstaclemodel.getRectangle())) {
                poofPosY = obstaclemodel.getPosition().y;
                poofPosX = obstaclemodel.getPosition().x;
                lives--;
                curtainScore = score;
                increaseCurtain();
                obstaclemodel.setPosition(new Vector2(obstaclemapcontroller.obstaclemap.OBSTACLES_COUNT*1000 + obstaclemodel.getPosition().x, obstaclemodel.getPosition().y));
                zebracontroller.zebraDizzy();
                zebracontroller.controllJump = false;
                zebracontroller.controllSlide = false;

                if (zebracontroller.getZebramodel().getPosition().y > zebracontroller.getZebramodel().ZEBRA_Y + 5) {
                    zebracontroller.getZebramodel().setVelocity(new Vector2(0, -5));
                }

                if (zebracontroller.getZebramodel().getPosition().y <= 400) {
                    zebracontroller.getZebramodel().setPosition(new Vector2(zebracontroller.getZebramodel().ZEBRA_X, zebracontroller.getZebramodel().ZEBRA_Y));
                    zebracontroller.getZebramodel().setVelocity(new Vector2(0, 0));
                }
            }

            if (zebracontroller.getZebramodel().getRectangle().overlaps(obstaclemodel.getRectangle()) && lives == 0) {
                poofBoolean = true;
                zebracontroller.zebraDie();
                obstaclecontroller.updateVelocity(new Vector2(0,0));
                zebracontroller.getZebramodel().setPosition(new Vector2(zebracontroller.getZebramodel().ZEBRA_X, zebracontroller.getZebramodel().ZEBRA_Y));
            }
        }
    }

    //makes the curtains on the sides of the screen decrease everytime you get 1000 points without losing a live
    public void updateCurtain() {
        if (curtainScore < score - 1000 && lives < 3) {
            lives ++;
            decreaseCurtain();
            curtainScore = score;
        }
    }

    //makes the curtains increase
    public void increaseCurtain(){
        curtainmodel.getCurtainLeftPos().x += MyZebraGame.WORLD_WIDTH/8;
        curtainmodel.getCurtainRightPos().x -= MyZebraGame.WORLD_WIDTH/8;
   }

   //makes the curtains decrease
   public void decreaseCurtain(){
       curtainmodel.getCurtainLeftPos().x -= MyZebraGame.WORLD_WIDTH/8;
       curtainmodel.getCurtainRightPos().x += MyZebraGame.WORLD_WIDTH/8;
   }

   //makes the curtains close in on smooth motion
    public void curtainClose() {
        curtainmodel.setCurtainWidth(curtainmodel.getWidth() + 4);
        curtainmodel.getCurtainRightPos().x -= 4;
    }

}
