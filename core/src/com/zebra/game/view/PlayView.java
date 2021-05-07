package com.zebra.game.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.zebra.game.model.Obstacle.ObstacleModel;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.State.MenuState;


public class PlayView extends BaseView {


    private int playerScore;

    //for the pause window
    private boolean paused = false;
    private Window pauseWindow;


    public PlayView(MyZebraGame game) {
        super(game);

        //sets the stage that handles the viewport and distributes input events
        stage = new Stage(viewPort, super.spriteBatch);
        Gdx.input.setInputProcessor(stage);

        //subrutines
        loadMusic();
        loadContent();
        addListenerButton();

        //game controllers intialized
        game.getZebracontroller().zebraRun();
        game.getGamecontroller().setScore(0);
        game.getGamecontroller().setLives(3);
        game.getObstaclecontroller().updateVelocity(new Vector2(-4,0));
        game.getZebracontroller().controllCurtain = false;
    }


    @Override
    public void loadMusic() {
        super.loadMusic();
    }

    //the actors for this view is loaded and initialized
    public void loadContent(){
        pauseBtn = new TextButton("PAUSE", skin);;
        score = new Label("Score:  ", skin, "half-tone");
        yourScore = new Label(String.format("%06d", playerScore), skin, "half-tone");
        pauseWindow = new Window("PAUSE", skin);
        backBtn = new TextButton("QUIT", skin);
        settings = new TextButton("SETTINGS", skin);
        resumeBtn = new TextButton("RESUME", skin);

    }

    //the actors are initialized with a listener which contains the logic to execute the action when activated
    public void addListenerButton(){
        pauseBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                paused = true;
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        resumeBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                stage.getActors().clear();
                paused = false;
                myzebragame.getTouchhandler().setButtonCliked(false);
                show();
                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new MenuState(gsm));
            }
        });
    }

    @Override
    public void show() {
        if(!paused){
            Table table = new Table();
            table.top();
            table.setFillParent(true);
            table.add(soundBtn).size(60,80).expandX();
            table.add(score);
            table.add(yourScore);
            table.add(pauseBtn).expandX();
            table.row();

            table.row().pad(5,0,0,10);
            stage.addActor(table);
        }
    }

    //when pausing the game, this submethod will set up a window
    public void showPause(boolean isPaused) {
        pauseWindow = new Window("", skin, "alt");
        pauseWindow.setVisible(paused);

        if(isPaused){
            pauseWindow.padTop(64);
            pauseWindow.add(resumeBtn).size(40,60).row();
            pauseWindow.add(backBtn).size(40,60).row();
            pauseWindow.setSize(stage.getWidth()/1.5f, stage.getHeight()/1.5f);
            pauseWindow.setPosition(stage.getWidth()/2 - pauseWindow.getWidth()/2, stage.getHeight()/2 - pauseWindow.getHeight()/2);
            pauseWindow.setMovable(false);
            stage.addActor(pauseWindow);
        }
    }

    public boolean getPaused(){
        return paused;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        //when the player pause the game
        if(paused){
            stage.getBatch().begin();
            showPause(paused);
            stage.getBatch().end();
            stage.draw();
        }
        else{
            showPause(paused);
            //updates and renders zebra & obstacle
            myzebragame.getZebramodel().getZebra().setStateTime();
            myzebragame.getPoof().setStateTime();
            //Updates score
            playerScore = (int) myzebragame.getGamecontroller().getScore();
            yourScore.setText(playerScore);
            myzebragame.getGamecontroller().update();
            super.spriteBatch.begin();

            //updating and draws the ground
            updateGround();
            super.spriteBatch.draw(background, bgPos1.x, bgPos1.y);
            super.spriteBatch.draw(background, bgPos2.x, bgPos1.y);

            //draws the obstacles
            for (ObstacleModel obstaclemodel : myzebragame.getObstacles()) {
                super.spriteBatch.draw(obstaclemodel.getTexture(), obstaclemodel.getPosition().x, obstaclemodel.getPosition().y, obstaclemodel.getWidth(), obstaclemodel.getHeight());
                myzebragame.getTouchhandler().handleTouch(obstaclemodel, viewPort);
            }
            super.spriteBatch.draw(myzebragame.getZebramodel().getZebra().getCurrentFrame(), myzebragame.getZebramodel().getPosition().x, myzebragame.getZebramodel().getPosition().y, myzebragame.getZebramodel().ZEBRA_WIDTH, myzebragame.getZebramodel().ZEBRA_HEIGHT);

            //draws the poofs
            if (myzebragame.getZebracontroller().controllPoof) {
                myzebragame.getPoof().updateCurrentFrame();
                super.spriteBatch.draw(myzebragame.getPoof().getCurrentFrame(), myzebragame.getGamecontroller().poofPosX, myzebragame.getGamecontroller().poofPosY, 150, 150);
            }

            //draws the current with its current position to the stage
            stage.getBatch().draw(myzebragame.getCurtainmodel().getLCurtain(), myzebragame.getCurtainmodel().getCurtainLeftPos().x,0, myzebragame.getCurtainmodel().getWidth(), MyZebraGame.WORLD_HEIGHT);
            stage.getBatch().draw(myzebragame.getCurtainmodel().getRCurtain(), myzebragame.getCurtainmodel().getCurtainRightPos().x,0, myzebragame.getCurtainmodel().getWidth(), MyZebraGame.WORLD_HEIGHT);

            super.spriteBatch.draw(backgroundGround, groundPos1.x, groundPos1.y);
            super.spriteBatch.draw(backgroundGround, groundPos2.x, groundPos2.y);

            super.spriteBatch.end();
            stage.draw();
        }
    }


    public void dispose() {
        stage.dispose();
    }

}
