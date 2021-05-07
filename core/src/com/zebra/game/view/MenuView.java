package com.zebra.game.view;


import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.zebra.game.FireBaseInterface;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.State.ChallengerState;
import com.zebra.game.controller.State.HighScoreState;
import com.zebra.game.controller.State.OpponentState;
import com.zebra.game.controller.ZebraController;
import com.zebra.game.model.ZebraModel;

public class MenuView extends BaseView{

    FireBaseInterface FBIC;

    //variable to keep track on the current background
    private static int currentBackground = 1;

    //for the tutorial
    private Window questionWindow;
    private boolean questionOn = false;


    public MenuView(MyZebraGame game) {
        super(game);
        FBIC = game.get_FBIC();

        stage = new Stage(viewPort, super.spriteBatch);
        Gdx.input.setInputProcessor(stage);
        myzebragame.get_FBIC().setHSList(dataholder);

        loadMusic();
        loadContent();
        addListenerButton();
    }

    //the actors for this view is loaded and initialized
    public void loadContent(){
        challengerBtn = new TextButton("MAKE A CHALLENGE",  skin, "default");
        opponentBtn  = new TextButton("ACCEPT A CHALLENGE", skin, "default");
        highscoreBtn = new TextButton("HIGHSCORE LIST", skin, "default");
        prevBtn = new TextButton("<<", skin);
        nextBtn = new TextButton(">>", skin);
        theme = new Label(myzebragame.resources.getBackground(currentBackground -1), skin, "title");
        question = new ImageButton(new TextureRegionDrawable(new TextureRegion(myzebragame.resources.getImage("question"))));
        backBtn = new TextButton("BACK", skin, "default");
    }

    //the actors are initialized with a listener which contains the logic to execute the action when activated
    public void addListenerButton(){
        question.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                questionOn = true;
                show();
                super.clicked(event, x, y);
            }
        });

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getActors().clear();
                questionOn = false;
                show();
            }
        });


        challengerBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new ChallengerState(gsm));
                dataholder.setGamePin(null);
            }
        });

        opponentBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new OpponentState(gsm));
            }
        });

        highscoreBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new HighScoreState(gsm));
            }
        });

        //looping through and changing the background from resources
        //when right arrow is clicked, next background theme is shown
        nextBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            if(currentBackground == myzebragame.resources.getBackgroundSize()) {
                currentBackground = 1;
            }
            else{
                currentBackground += 1;
            }
            changeBackground(currentBackground,currentBackground);
            theme.setText(myzebragame.resources.getBackground(currentBackground-1));
            }

        });

        //when left arrow is clicked, previous background theme is shown
        prevBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            if(currentBackground == 1){
                currentBackground = myzebragame.resources.getBackgroundSize();
            }
            else{
                currentBackground -= 1;
            }
            changeBackground(currentBackground, currentBackground);
            theme.setText(myzebragame.resources.getBackground(currentBackground - 1));
            }
        });
    }

    //submethod for changing the background
    public void changeBackground(int currentBackground, int changeGround){
        super.setBackground(myzebragame.resources.getBackground(currentBackground-1), myzebragame.resources.getGround(currentBackground-1));
    }

    @Override
    public void show(){
        //tutorial window that occur upon the stage
        if(questionOn){
            questionWindow = new Window("", skin);
            questionWindow.setBackground(new TextureRegionDrawable(new TextureRegion(myzebragame.resources.getImage("tutorial"))));
            questionWindow.setSize(stage.getWidth()/1.5f, stage.getHeight()/1.5f);
            questionWindow.setPosition(stage.getWidth()/2 - questionWindow.getWidth()/2, stage.getHeight()/2 - questionWindow.getHeight()/2);
            questionWindow.setMovable(false);
            questionWindow.add(backBtn).padBottom(10);
            stage.addActor(questionWindow);

        }
        else{
            Table table = new Table();

            table.top();
            //The table is the same size as the stage
            table.setFillParent(true);
            table.pad(3);
            table.add(soundBtn).size(60,80);
            table.add(title).colspan(3);
            table.pad(10);
            table.add(question).size(60,80).padRight(15);
            table.row().pad(5, 0, 10, 0);
            table.row().pad(30,0,0,10);
            table.add(prevBtn);
            table.add(theme);
            table.add(nextBtn);
            table.row().pad(20,0,0,10);
            table.add(challengerBtn).colspan(3);
            table.row().padBottom(10);
            table.add(opponentBtn).colspan(3);
            table.row().padBottom(10);
            table.add(highscoreBtn).colspan(3);

            //Adding table to stage
            stage.addActor(table);
        }

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        super.spriteBatch.begin();
        super.spriteBatch.draw(myzebragame.getZebramodel().getZebra().getCurrentFrame(), myzebragame.getZebramodel().getPosition().x, myzebragame.getZebramodel().getPosition().y, myzebragame.getZebramodel().ZEBRA_WIDTH, myzebragame.getZebramodel().ZEBRA_HEIGHT);
        super.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    //To avoid memory leaks
    @Override
    public void dispose() {
        stage.dispose();
    }
}
