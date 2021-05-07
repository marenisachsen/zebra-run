package com.zebra.game.controller.State;
import com.zebra.game.MyZebraGame;
import com.zebra.game.GameStateManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.view.PlayView;

public class PlayState extends BaseState {
    //creates an object of the playView
    private PlayView playView;

    //constructor to initialize the PlayState
    public PlayState(GameStateManager gsm){
        super(gsm);
        playView = new PlayView(gsm.getMyZebraGame());
        gsm.getMyZebraGame().setScreen(playView);
    }

    //update method for when the playView is running
    @Override
    public void update(float dt) {
        //update method from baseView
        super.update(dt);
        //updated method for when the game isn't paused
        if(!playView.getPaused()){
            //updates and renders zebra & obstacle
            gsm.getMyZebraGame().getZebramodel().getZebra().setStateTime();
            gsm.getMyZebraGame().getPoof().setStateTime();
            gsm.getMyZebraGame().getPoof().updateCurrentFrame();
            gsm.getMyZebraGame().getGamecontroller().update();
            gsm.getMyZebraGame().getGamecontroller().update();
            play();
        }
    }


    //controlls the jump(), slide() and curtain logic
    public void play() {
        if(gsm.getMyZebraGame().getZebracontroller().controllJump){
            gsm.getMyZebraGame().getZebracontroller().zebraJump();
        }
        //
        if(gsm.getMyZebraGame().getZebracontroller().controllSlide){
            gsm.getMyZebraGame().getZebracontroller().zebraSlide();
        }

        if(gsm.getMyZebraGame().getZebracontroller().controllCurtain) {
            gsm.getMyZebraGame().getGamecontroller().curtainClose();
        }

        if (gsm.getMyZebraGame().getCurtainmodel().getWidth() > MyZebraGame.WORLD_WIDTH - MyZebraGame.WORLD_WIDTH/4) {
            gsm.set(new GameOverState(gsm));
            gsm.getMyZebraGame().getZebracontroller().controllCurtain = false;
        }
    }

}
