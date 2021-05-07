package com.zebra.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zebra.game.FireBaseInterface;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.State.MenuState;

public class HighScoreView extends BaseView {


    FireBaseInterface FBIC;

    public HighScoreView(MyZebraGame game) {
        super(game);
        FBIC = game.get_FBIC();

        //sets the stage that handles the viewport and distributes input events
        stage = new Stage(viewPort, super.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        game.getZebracontroller().zebraRun();

        loadContent();
        addListenerButton();
        loadMusic();
    }

    //the actors for this view is loaded and initialized
    private void loadContent(){
        title = new Label("HIGHSCORE", skin, "title");

        //global realtime the highscore list
        highscoreName1 = new Label(dataholder.getHsScore1(), skin, "big");
        highscoreName2 = new Label(dataholder.getHsScore2(), skin, "big");
        highscoreName3 = new Label(dataholder.getHsScore3(), skin, "big");

        backBtn = new TextButton("Back", skin);
    }

    //the actors are initialized with a listener which contains the logic to execute the action when activated
    public void addListenerButton(){
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new MenuState(gsm));
                myzebragame.getZebracontroller().zebraRun();
            }
        });
    }


    // setting up a table to structures the stage
    @Override
    public void show(){
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.pad(75);
        table.add(soundBtn).size(60,80).left();
        table.add(title).colspan(3);
        table.row().pad(20,0,0,10);
        table.add(highscoreName1).colspan(3);
        table.row().pad(20,0,0,10);
        table.add(highscoreName2).colspan(3);
        table.row().pad(20,0,0,10);
        table.add(highscoreName3).colspan(3);
        table.row().pad(20,0,0,10);


        table.add(backBtn).colspan(3);

        //Add the table to stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        super.spriteBatch.begin();
        super.spriteBatch.draw(myzebragame.getZebramodel().getZebra().getCurrentFrame(), myzebragame.getZebramodel().getPosition().x, myzebragame.getZebramodel().getPosition().y, myzebragame.getZebramodel().ZEBRA_WIDTH, myzebragame.getZebramodel().ZEBRA_HEIGHT);        super.spriteBatch.end();
    }

    //to avoid memory leaks
    @Override
    public void dispose() {
        stage.dispose();
    }
}
