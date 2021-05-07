package com.zebra.game.controller.State;
import com.zebra.game.GameStateManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.model.ZebraModel;

    //abstract class to make states
public abstract class BaseState {

    protected GameStateManager gsm;
    protected BaseState(GameStateManager gsm){
        this.gsm = gsm;
        //dispose();
    }

    //update function that updates the zebra in all states
    public void update(float dt){
        ZebraModel.getInstance().getZebra().setStateTime();
        gsm.getMyZebraGame().getZebracontroller().update();
    }

}
