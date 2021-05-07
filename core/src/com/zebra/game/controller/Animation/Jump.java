package com.zebra.game.controller.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zebra.game.MyZebraGame;

public class Jump extends Animate {

    Texture sheet = MyZebraGame.resources.getImage("jump");
    Array<TextureRegion> frames = makeFrames(sheet, 6,1) ;


    public Jump(){
        super();
        setAnimation(animation);
        getStateTime();
    }


    @Override
    public void updateCurrentFrame() {
        animation = new Animation<TextureRegion>(0.15f , frames);
        currentFrame =  animation.getKeyFrame(getStateTime(),false);
        setCurrentFrame(currentFrame);
    }

    @Override
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

}
