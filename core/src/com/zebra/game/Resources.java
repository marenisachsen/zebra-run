package com.zebra.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.HashMap;


public class Resources {

    private static Resources instance = new Resources();

    //system overview of how to download all files
    private HashMap<String, Texture> images;
    private ArrayList<String> backgrounds;
    private ArrayList<String> grounds;
    private String currentBg;
    private String currentGround;
    private HashMap<String, Sound> sounds;


    private Resources() {
        images = new HashMap<>();
        sounds = new HashMap<>();
        backgrounds = new ArrayList<>();
        grounds = new ArrayList<>();

        loadImages();
        loadSounds();
    }


    public static Resources getInstance(){
        if (instance == null) {
            return new Resources();
        }
        return instance;
    }

    //uploads the image
    public void loadImages() {

        //backgrounds and their grounds
        loadImage("forest", "background/background_foresttrees.png");
        loadImage("forestground", "background/background_forestground.png");
        loadImage("jungle", "background/background_jungle.png");
        loadImage("jungleground", "background/background_jungleground.png");
        loadImage("moon", "background/background_moon.png");
        loadImage("moonground", "background/background_moonground.png");
        loadImage("sunset", "background/background_sunset.png");
        loadImage("swamp", "background/background_swamp.png");
        loadImage("curtainL", "background/curtain.png");
        loadImage("curtainR", "background/curtain 2.png");
        loadImage("question", "background/question_mark.png");
        loadImage("tutorial", "background/tutorial.png");

        //sound buttom
        loadImage("sound_on", "music/sound_on.png");
        loadImage("sound_off", "music/sound_off.png");

        //obstacles
        loadImage("bush", "obstacle/bush.png");
        loadImage("cloud", "obstacle/cloud.png");
        loadImage("treestump", "obstacle/treestump.png");
        loadImage("poof", "obstacle/poof.png");

        //zebra
        loadImage("walk", "zebra/walk.png");
        loadImage("jump", "zebra/jump.png");
        loadImage("dizzy", "zebra/dizzy.png");
        loadImage("slide", "zebra/slide.png");
        loadImage("die", "zebra/die.png");
        loadImage("run", "zebra/run.png");

        backgrounds.add("forest");
        grounds.add("forestground");
        backgrounds.add("jungle");
        grounds.add("jungleground");
        backgrounds.add("moon");
        grounds.add("moonground");
    }

    //uploading the sounds
    public void loadSounds(){
        loadSound("background_music", Gdx.audio.newSound(Gdx.files.internal("music/backgroundMusic.mp3")));
        loadSound("collide", Gdx.audio.newSound(Gdx.files.internal("music/collide.wav")));
        loadSound("gameOver",  Gdx.audio.newSound(Gdx.files.internal("music/gameOver.mp3")));
        loadSound("jump",  Gdx.audio.newSound(Gdx.files.internal("music/jump.mp3")));
        loadSound("slide", Gdx.audio.newSound(Gdx.files.internal("music/slide.mp3")));
    }


    //submethods for image and sound uploading
    public void loadImage(String name, String path) {
        images.put(name, new Texture(path));
    }
    public void loadSound(String name, Sound path) {
        sounds.put(name, path);
    }

    // getters and setters

    //Returns the image saved in 'name'
    public Texture getImage(String name) {
        return images.get(name);
    }

    //Returns the sound saved in 'name'
    public Sound getSound(String name) {
        return sounds.get(name);
    }

    public String getBackground(int i){
        return backgrounds.get(i);
    }

    public String getGround(int i){ return grounds.get(i);}

    public int getBackgroundSize(){
        return backgrounds.size();
    }

    public String getCurrentBg() {
        return currentBg;
    }

    public String getCurrentGround() {
        return currentGround;
    }

    public void setCurrentBg(String currentBg) {
        this.currentBg = currentBg;
    }

    public void setCurrentGround(String currentGround) {
        this.currentGround = currentGround;
    }
}
