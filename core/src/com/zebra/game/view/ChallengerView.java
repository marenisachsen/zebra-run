package com.zebra.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.State.PlayState;
import com.zebra.game.controller.State.MenuState;


public class ChallengerView extends BaseView{


    public ChallengerView(MyZebraGame game) {
        super(game);
        playerChallenger = true;

        //sets the stage that handles the viewport and distributes input events
        stage = new Stage(viewPort, super.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        //setting the zebra to run
        game.getZebracontroller().zebraRun();

        loadContent();
        addListenerButton();
        loadMusic();
    }

    //the actors for this view is loaded and initialized
    private void loadContent() {
        backBtn = new TextButton("BACK", skin);
        play = new TextButton("Start game", skin);
    }

    //the actors are initialized with a listener which contains the logic to execute the action when activated
    public void addListenerButton(){

        backBtn.addListener(new ChangeListener() {
        //må lage en boolean funksjon som forteller om difficulty
        //se ingvild
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new MenuState(gsm));
            }
        });

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new PlayState(gsm));
            }
        });
    }

    // setting up a table to structures the stage
    @Override
    public void show() {
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.pad(40);
        table.add(soundBtn).size(60,80).pad(3);
        table.add(title).colspan(3);
        table.row().pad(10,0,0,10);
        table.row();
        table.add(play).colspan(3);
        table.row().padBottom(30);
        table.add(backBtn).colspan(3);

        //Adding table to stage
        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        super.spriteBatch.begin();
        super.spriteBatch.draw(myzebragame.getZebramodel().getZebra().getCurrentFrame(), myzebragame.getZebramodel().getPosition().x, myzebragame.getZebramodel().getPosition().y, myzebragame.getZebramodel().ZEBRA_WIDTH, myzebragame.getZebramodel().ZEBRA_HEIGHT);
        super.spriteBatch.end();
    }

    public void dispose() {
        stage.dispose();
    }
}
