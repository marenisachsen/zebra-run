package com.zebra.game.controller.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.zebra.game.MyZebraGame;


public class Run extends Animate {

    Texture sheet = MyZebraGame.resources.getImage("run");
    Array<TextureRegion> frames = makeFrames(sheet, 8,1) ;


    public Run(){
        super();
        setAnimation(animation);
        getStateTime();
    }


    @Override
    public void updateCurrentFrame() {
        animation = new Animation<TextureRegion>(0.1f , frames);
        currentFrame =  animation.getKeyFrame(getStateTime(),true);
        setCurrentFrame(currentFrame);
    }

    @Override
    public TextureRegion getCurrentFrame(){
        return currentFrame;
    }

}
