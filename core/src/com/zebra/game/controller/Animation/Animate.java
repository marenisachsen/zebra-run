package com.zebra.game.controller.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Animate {

    protected TextureRegion currentFrame;
    protected float stateTime;
    protected Animation<TextureRegion> animation;

    public Animate() {
        super();;
        setStateTime();
        getAnimation();
    }

    //updates the current frame of the animation
    public abstract void updateCurrentFrame();

    //updates the animation
    public void updateAnimation() {
        updateCurrentFrame();
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

    //sets the state time
    public void setStateTime(){
        this.stateTime += Gdx.graphics.getDeltaTime();
    }

    //gets the current frame at any given time
    public TextureRegion getCurrentFrame(){return currentFrame;}

    //sets the current frame
    public void setCurrentFrame(TextureRegion frame){this.currentFrame = frame;}

    //gets the animation at any given time
    public Animation getAnimation() {
        return animation;
    }

    //sets the animation
    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    //will check the animation and return true when it is finished
    public boolean isAnimationFinished(){
        return this.animation.isAnimationFinished(this.stateTime);
    }

}
