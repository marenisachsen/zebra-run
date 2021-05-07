package com.zebra.game.controller.Animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zebra.game.MyZebraGame;

public class Dizzy extends Animate{

    Texture sheet = MyZebraGame.resources.getImage("dizzy");
    Array<TextureRegion> frames = makeFrames(sheet, 8,1) ;


    public Dizzy(){
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
