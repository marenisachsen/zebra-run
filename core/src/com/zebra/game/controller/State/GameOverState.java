package com.zebra.game.controller.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.GameStateManager;
import com.zebra.game.model.ZebraModel;
import com.zebra.game.model.ZebraState;
import com.zebra.game.view.GameOverView;

public class GameOverState extends BaseState{

    public GameOverState(GameStateManager gsm){
        super(gsm);
        gsm.getMyZebraGame().setScreen(new GameOverView(gsm.getMyZebraGame()));
    }


    @Override
    public void update(float dt) {
        super.update(dt);
        ZebraModel.getInstance().setZebraState(ZebraState.DIE);
        gsm.getMyZebraGame().getZebracontroller().update();
    }


}
