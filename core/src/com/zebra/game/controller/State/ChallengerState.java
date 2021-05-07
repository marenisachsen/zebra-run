package com.zebra.game.controller.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.GameStateManager;
import com.zebra.game.view.ChallengerView;

public class ChallengerState extends BaseState {


    public ChallengerState(GameStateManager gsm){
        super(gsm);
        gsm.getMyZebraGame().setScreen(new ChallengerView(gsm.getMyZebraGame()));
    }


    @Override
    public void update(float dt) {
        super.update(dt);
    }



}
