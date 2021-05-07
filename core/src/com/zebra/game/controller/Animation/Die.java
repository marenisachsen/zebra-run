package com.zebra.game.controller.Animation;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.zebra.game.MyZebraGame;

public class Die extends Animate{

    Texture sheet = MyZebraGame.resources.getImage("die");
    Array<TextureRegion> frames = makeFrames(sheet, 6,1) ;


    public Die() {
        super();
        setAnimation(animation);
        getStateTime();
    }


    @Override
    public void updateCurrentFrame(){
        animation = new Animation<TextureRegion>(0.15f , frames);
        currentFrame =  animation.getKeyFrame(getStateTime(),false);
        setCurrentFrame(currentFrame);
    }

    @Override
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

}
