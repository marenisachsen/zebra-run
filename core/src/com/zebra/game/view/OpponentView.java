package com.zebra.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.State.PlayState;
import com.zebra.game.controller.State.MenuState;
import com.zebra.game.model.ZebraModel;

public class OpponentView extends BaseView{

    //validation variable for checking the gamecode
    private boolean validGamepin = false;

    public OpponentView(MyZebraGame game) {
        super(game);
        stage = new Stage(viewPort, super.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        playerChallenger = false;
        game.getZebracontroller().zebraRun();

        loadContent();
        addListenerButton();
        loadMusic();
    }

    //the actors for this view is loaded and initialized
    private void loadContent() {
        gamepin = new Label("GAME PIN", skin, "alt");
        gamepinField = new TextField("", skin, "default");
        gamepinBtn = new TextButton("ok", skin);
        backBtn = new TextButton("BACK", skin);
        play = new TextButton("Start game", skin);
    }

    //the actors are initialized with a listener which contains the logic to execute the action when activated
    public void addListenerButton() {
        gamepinBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dataholder.setGamePin(gamepinField.getText());

                //Checks to se if code is correct, if so sets opponent name and score in dataholder from Firebase
                myzebragame.get_FBIC().checkCodeDb(dataholder.getGamePin(), dataholder);

            }
        });

        backBtn.addListener(new ChangeListener() {
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

    @Override
    public void show() {
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.pad(75);
        table.add(soundBtn).size(60,80).pad(3);
        table.add(title);
        table.row().padBottom(10);

        //gamecode
        table.add(gamepin);
        table.add(gamepinField);
        table.add(gamepinBtn);
        table.row().padBottom(20);
        table.row().pad(40,0,0,10);
        //validation of game code, if valid, then the play button will be showed
        if(validGamepin){
            table.add(play).colspan(3);
            table.row().padBottom(10);
        }
        table.add(backBtn).colspan(3);
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        super.spriteBatch.begin();
        super.spriteBatch.draw(ZebraModel.getInstance().getZebra().getCurrentFrame(), ZebraModel.getInstance().getPosition().x,ZebraModel.getInstance().getPosition().y, 100, 100 );
        super.spriteBatch.end();

        if (dataholder.getGamePin() != null){
            //playbutton visible
            validGamepin = true;
            this.show();
        }
    }

    public void dispose() {
        stage.dispose();
    }

}
