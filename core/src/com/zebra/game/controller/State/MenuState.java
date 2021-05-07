package com.zebra.game.controller.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zebra.game.GameStateManager;
import com.zebra.game.MyZebraGame;
import com.zebra.game.model.ZebraModel;
import com.zebra.game.model.ZebraState;
import com.zebra.game.view.MenuView;

public class MenuState extends BaseState{

    //constructor to initialize the state and set the coherent screen
    public MenuState(GameStateManager gsm){
        super(gsm);
        gsm.getMyZebraGame().setScreen(new MenuView(gsm.getMyZebraGame()));
    }


    @Override
    public void update(float dt) {
        super.update(dt);
    }


}

