package com.zebra.game.controller.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.GameStateManager;
import com.zebra.game.view.OpponentView;

public class OpponentState extends BaseState{


    public OpponentState(GameStateManager gsm){
        super(gsm);
        gsm.getMyZebraGame().setScreen(new OpponentView(gsm.getMyZebraGame()));
    }


    @Override
    public void update(float dt) {
        super.update(dt);
    }

}
