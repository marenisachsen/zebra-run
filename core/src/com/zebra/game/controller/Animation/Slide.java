package com.zebra.game.controller.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.zebra.game.MyZebraGame;

public class Slide extends Animate {

    Texture sheet = MyZebraGame.resources.getImage("slide");
    Array<TextureRegion> frames = makeFrames(sheet, 6,1) ;


    public Slide(){
        super();
        setAnimation(animation);
        getStateTime();
    }


    @Override
    public void updateCurrentFrame() {
        animation = new Animation<TextureRegion>(0.3f , frames);
        currentFrame =  animation.getKeyFrame(getStateTime(),false);
        setCurrentFrame(currentFrame);
    }

    @Override
    public TextureRegion getCurrentFrame(){
        return currentFrame;
    }

}

