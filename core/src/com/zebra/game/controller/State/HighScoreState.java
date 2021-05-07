package com.zebra.game.controller.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.GameStateManager;
import com.zebra.game.view.HighScoreView;

public class HighScoreState extends BaseState {

    public HighScoreState(GameStateManager gsm){
        super(gsm);
        gsm.getMyZebraGame().setScreen(new HighScoreView(gsm.getMyZebraGame()));
    }


    @Override
    public void update(float dt) {
        super.update(dt);
    }


}
