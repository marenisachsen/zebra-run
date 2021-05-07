package com.zebra.game.controller.Animation;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.zebra.game.MyZebraGame;

public class Poof {

    Texture sheet = MyZebraGame.resources.getImage("poof");
    Array<TextureRegion> frames = makeFrames(sheet, 6,5);
    TextureRegion currentFrame;
    float stateTime;
    Animation<TextureRegion> animation;


    public Poof(){
        super();
        animation = new Animation<TextureRegion>(0.008f , frames);
    }

    //Takes a texture split it with the inserted columm and row, and makes it into an array of TextureRegions
    public Array<TextureRegion> makeFrames(Texture sheet, int col, int row) {
        TextureRegion region = new TextureRegion(sheet);
        Array<TextureRegion>frames = new Array<TextureRegion>();
        int frameWidth=region.getRegionWidth()/col;
        int frameHeight = region.getRegionHeight()/row;

        for(int i=0;i< row;i++){
            for(int j = 0 ; j<col;j++) {
                frames.add(new TextureRegion(region, j * frameWidth, i * frameHeight, frameWidth, frameHeight));
            }
        }

        return frames;
    }

    //gets the state time at any given time
    public float getStateTime(){
        return stateTime;
    }

    //set the state time
    public void setStateTime(){
        this.stateTime += Gdx.graphics.getDeltaTime();
    }

    //updates the current frame of the animation
    public void updateCurrentFrame() {
        animation = new Animation<TextureRegion>(0.08f, frames);
        currentFrame =  animation.getKeyFrame(getStateTime(),true);
    }

    //gets the current frame at any given time
    public TextureRegion getCurrentFrame() { return currentFrame; }

}
