package com.zebra.game.model;


import com.zebra.game.DataHolderClass;

public class Player {

    private String playerName;
    private String playerScore;


    public Player(String name, String score){
        playerName = name;
        playerScore = score;
    }

    //gets the name of the player
    public String getName(){
        return playerName;
    }

    //gets the players score
    public String getScore(){
        return playerScore;
    }

    //sets the name of the player
    public void setName(String name){
        playerName = name;
    }

    //sets the score of the player
    public void setScore(String score){
        playerScore = score;
    }


}