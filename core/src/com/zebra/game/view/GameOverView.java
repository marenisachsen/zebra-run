package com.zebra.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.zebra.game.MyZebraGame;
import com.zebra.game.controller.GameController;
import com.zebra.game.controller.State.MenuState;
import com.zebra.game.controller.ZebraController;
import com.zebra.game.model.Player;
import com.zebra.game.model.ZebraModel;
import com.zebra.game.model.ZebraState;

import java.util.ArrayList;

public class GameOverView extends BaseView{

    private ZebraController zebraController = new ZebraController(ZebraModel.getInstance());
    private TextField usernameField;
    private String gameRes = "";
    private String checkGameStatus = "";


    public GameOverView(MyZebraGame game) {
        super(game);
        stage = new Stage(viewPort, super.spriteBatch);

        //Since stage is an InputAdaptor type, we can use inputprocessor to show to screen
        Gdx.input.setInputProcessor(stage);

        //subrutines
        loadContent();
        addListenerButton();
        loadMusic();
    }

    //the actors for this view is loaded and initialized
    public void loadContent(){
        gameOver = new Label("GAME OVER",  skin, "title");
        scoreLabel = new Label(String.format("Your score:   %06d", myzebragame.getGamecontroller().getScore()), skin, "half-tone");
        username = new Label("USERNAME: ", skin, "big");
        okBtn = new TextButton("OK", skin);
        usernameField = new TextField("", skin, "default");
        menuBtn = new TextButton("MENU", skin);

        displayGamePin = new Label("Gamepin: " + dataholder.getGamePin(), skin, "big");
        gameResult = new Label(gameRes, skin, "big");


    }

    //adds the click Listener to execute the action upon clicking the buttons
    public void addListenerButton(){
        // logic for connecting and validating the user with the database
        okBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                //Sets username and score
                String userName = usernameField.getText();
                String userScore = String.format("%d", myzebragame.getGamecontroller().getScore());
                dataholder.setPlayerName(userName);
                dataholder.setPlayerScore(userScore);

                //Updates high score list in Firebase database
                myzebragame.get_FBIC().updateHighScore(new Player(userName, userScore), dataholder);

                //Checks if a player plays a new game or already have entered a gamepin
                if (dataholder.getGamePin() == null) {
                    //Create new game
                    checkGameStatus = "NewGame";

                    //Creates a new game which creates a new gamepin in Firebase and inserts player name and score form game
                    myzebragame.get_FBIC().createGameDb(new Player(userName, userScore), dataholder);
                } else {
                    checkGameStatus = "ExistingGame";


                    //Push player and score to excisting game
                    myzebragame.get_FBIC().createPlayerDb(new Player(dataholder.getChallengerName(), dataholder.getChallengerScore()), dataholder);

                    //Sets message in dataholder according to how the game went -> win/draw/loss
                    if (Integer.parseInt(dataholder.getChallengerScore()) > myzebragame.getGamecontroller().getScore()) {
                        gameRes = dataholder.getLossMsg();
                    } else if (Integer.parseInt(dataholder.getChallengerScore()) == myzebragame.getGamecontroller().getScore()) {
                        gameRes = dataholder.getDrawMsg();
                    } else {
                        gameRes = dataholder.getWinMsg();
                    }

                }

            }
        });

        menuBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gsm.set(new MenuState(gsm));
                gsm.getMyZebraGame().getCurtainmodel().setCurtainWidth(1120);
                gsm.getMyZebraGame().getCurtainmodel().setCurtainLeft(new Vector2(-gsm.getMyZebraGame().getCurtainmodel().getWidth(), 0));
                gsm.getMyZebraGame().getCurtainmodel().setCurtainRight(new Vector2(gsm.getMyZebraGame().WORLD_WIDTH, 0));
                gsm.getMyZebraGame().getZebracontroller().zebraRun();
                //gsm.getMyZebraGame().getZebracontroller().update();


                //Resets dataholder
                dataholder.setGamePin(null);
                dataholder.setPlayers(new ArrayList<Player>());
            }
        });

    }

    @Override
    public void show() {
        //puts the table on the top of the page and the table is the size of our stage and then the size of the specified value 75
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.pad(75);
        table.pad(3);
        table.add(soundBtn).size(60,80);
        table.row();
        table.add(gameOver).colspan(3).padTop(30);
        table.row();
        table.add(scoreLabel).colspan(3).padTop(50);
        table.row();

        //adds and expends the gameover label to the size of the screen
        table.add(username).colspan(3).padTop(55);
        table.row();
        table.add(usernameField).colspan(3);
        table.row();
        table.add(okBtn).expandX().padTop(58);



        //row adds a new row to the table and pad gives the size
        table.row().pad(20,0,0,10);

        if (checkGameStatus == "ExistingGame"){
            //View for new game
            table.add(new Label("Gamepin: " + dataholder.getGamePin(), skin, "big"));
            table.row().pad(20,0,0,10);

            table.add(new Label(gameRes, skin, "big")).center().padTop(10);
            table.row().pad(20,0,0,10);

            table.add(menuBtn).expandX();
            table.row().pad(20,0,0,10);

            table.removeActor(okBtn);

        }
        else if(checkGameStatus == "NewGame"){
            //View for excisting game
            table.add(displayGamePin);
            table.row().pad(20,0,0,10);
            table.add(menuBtn).expandX();
            table.removeActor(okBtn);
    }

        //adds the table to stage
        stage.addActor(table);
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        super.spriteBatch.begin();
        super.spriteBatch.draw(ZebraModel.getInstance().getZebra().getCurrentFrame(), ZebraModel.getInstance().getPosition().x,ZebraModel.getInstance().getPosition().y, ZebraModel.ZEBRA_WIDTH, ZebraModel.ZEBRA_HEIGHT );
        super.spriteBatch.end();
        show();
    }

    public void dispose() {
        stage.dispose();
    }

}
