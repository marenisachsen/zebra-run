package com.zebra.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private static SoundManager instance = new SoundManager(Resources.getInstance());

    // setting up the background music
    public static Music music = Gdx.audio.newMusic(Gdx.files.internal("music/backgroundMusic.mp3"));

    public static Resources resources;

    private SoundManager(Resources res){
        resources = res;
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            return new SoundManager(Resources.getInstance());
        }
        return instance;
    }

    //sound effect for jump
    public static void playJumpSound()
    {
        Sound sound = resources.getSound("jump");
        if(music.isPlaying()){
            sound.play();
        }
    }
    //sound effect for slide
    public static void playSlideSound(){
        if(music.isPlaying()){
            Sound sound = resources.getSound("slide");
            sound.play();
        }    }

    //sound effect for collide
    public static void playCollideSound(){
        if(music.isPlaying()){
            Sound sound = resources.getSound("collide");
            sound.play();
        }
    }

    //sound effect when the game is over
    public static void playGameOverSound(){
        if(music.isPlaying()){
            Sound sound = resources.getSound("gameOver");
            sound.play();
        }
    }

    // managing the music for on/off
    public void music(boolean soundOn){
        if(soundOn){
            turnOffMusic();
        }
        else{
            turnOnMusic();
        }
    }

    // sub methods for on/off functionality, external methods for further need for access
    public static void turnOnMusic(){
        music.setLooping(true);
        music.play();
        music.isPlaying();
    }
    public static void turnOffMusic(){
        if(music.isPlaying()) {
            music.stop();
        }
    }

}
