package com.zebra.game;

import com.zebra.game.model.Player;

import java.util.ArrayList;

public class DataHolderClass {


    private static DataHolderClass instance = new DataHolderClass();

    private String playerName;
    private String playerScore;

    private String challengerName;
    private String challengerScore;

    private ArrayList<Player> players = new ArrayList<Player>();

    private String gamePin;

    private String winMsg = "You won!!";
    private String drawMsg = "It's a draw!";
    private String lossMsg = "You lost...";


    private String hsScore1 = "";
    private String hsScore2 = "";
    private String hsScore3 = "";

    //A dataholderclass which contains variables regarding the players name and score, game over-messages and high score variables
    public DataHolderClass(){
    }

    public static DataHolderClass getInstance(){
        if(instance == null){
            return new DataHolderClass();
        }
        return instance;
    }

    //Messages displayed after challenging a player
    public String getWinMsg(){
        return winMsg;
    }
    public String getDrawMsg(){
        return drawMsg;
    }
    public String getLossMsg(){
        return lossMsg;
    }


    public void addPlayer(Player player){
        if (players.size() < 1){
            players.add(player);
        }else{
            players.add(players.size(), player);
        }

    }


    //Player
    public void setPlayerName (String name){ playerName = name; }
    public String getPlayerName(){ return playerName; }

    //Player score
    public void setPlayerScore (String score){ playerScore = score; }
    public String getPlayerScore(){ return playerScore; }


    //Opponent player
    public void setChallengerName (String chName){ challengerName = chName; }
    public String getChallengerName(){ return challengerName; }

    //Opponent score
    public void setChallengerScore (String chScore){ challengerScore = chScore; }
    public String getChallengerScore(){ return challengerScore; }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    //Gamepin
    public void setGamePin(String gamePin){
        this.gamePin = gamePin;
    }
    public String getGamePin(){ return gamePin; }

    public void setHsScore1 (String score1){hsScore1 = score1;}
    public String getHsScore1(){return hsScore1;}
    public void setHsScore2 (String score2){hsScore2 = score2;}
    public String getHsScore2(){return hsScore2;}
    public void setHsScore3 (String score3){hsScore3 = score3;}
    public String getHsScore3(){return hsScore3;}

    //public void setHighScoreList(ArrayList<String> hsList){highScoreList = hsList;}
    //public ArrayList<String> getHighScoreList(){return highScoreList;}

}