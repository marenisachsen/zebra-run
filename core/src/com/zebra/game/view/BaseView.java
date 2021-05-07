package com.zebra.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.zebra.game.DataHolderClass;
import com.zebra.game.MyZebraGame;
import com.zebra.game.SoundManager;
import com.zebra.game.GameStateManager;
import com.zebra.game.controller.State.PlayState;


public abstract class BaseView implements Screen {
    //sets the velocity of the background and its ground
    private static final int groundSpeed = -4;
    private static final int backgroundSpeed = -1;


    public MyZebraGame myzebragame;
    public SpriteBatch spriteBatch;
    public GameStateManager gsm;
    public PlayState playState;

    //Firebase
    public DataHolderClass dataholder = DataHolderClass.getInstance();

    //Skin stores resources for UI widgets to use
    protected Skin skin;
    protected BitmapFont font;

    //Creating different actors to display
    protected Texture background, backgroundGround;
    protected Label title;
    protected ImageButton question;

    //music, turn on/off
    protected TextureRegionDrawable soundRD[];
    protected ImageButton soundBtn;
    private boolean soundOn = SoundManager.music.isPlaying();

    //Orthographic camera operates as a real world camera and follows along the gameworld
    protected OrthographicCamera gamecam;

    //handling different screensize and ascpect ratios
    public Viewport viewPort;

    //handles the viewport and distributes input events
    Stage stage;

    //Different actors for that can be displayed
    protected Label gamepin, yourScore, highscoreName1, highscoreName2, highscoreName3, displayGamePin, gameResult, score;
    protected TextButton gamepinBtn;
    protected boolean playerChallenger;
    protected TextButton challengerBtn,  opponentBtn,  play, highscoreBtn, settings, backBtn, menuBtn, pauseBtn,resumeBtn;
    protected TextField gamepinField;
    protected Label scoreLabel, gameOver, username;
    protected TextButton okBtn;
    protected TextButton nextBtn, prevBtn;
    protected Label theme;
    protected Vector2 groundPos1, groundPos2, bgPos1, bgPos2;

    public BaseView(MyZebraGame game) {
        myzebragame = game;
        spriteBatch = game.getBatch();
        gsm = game.getGsm();

        //gamecam, the orthographic camera, operates as a real world camera and renders what is in the scene exactly the size it is
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, MyZebraGame.WORLD_WIDTH, MyZebraGame.WORLD_HEIGHT);

        //setup for the skin
        skin = new Skin(Gdx.files.internal("skin/comic-ui.json"));

        //strechViewport strecthes the textures in and out, so it does not keep the aspect ratio, the world is scaled to take the whole screen.
        viewPort = new StretchViewport(MyZebraGame.WORLD_WIDTH, MyZebraGame.WORLD_HEIGHT);

        title = new Label("EN ZEBRA SOM KAN SE BRA", skin, "title");

        //initializing the ground vectors
        groundPos1 = new Vector2(0, 0);
        groundPos2 = new Vector2(MyZebraGame.WORLD_WIDTH, 0);

        //set the default background when opening the application, and if changed, set chosen current background
        if (myzebragame.resources.getCurrentBg() == null) {
            setBackground("forest", "forestground");
        } else {
            setBackground(myzebragame.resources.getCurrentBg(), myzebragame.resources.getCurrentGround());
        }
    }

    //setting the background, the separated ground and its vector
    public void setBackground(String background, String backgroundGround) {
        myzebragame.resources.setCurrentBg(background);
        myzebragame.resources.setCurrentGround(backgroundGround);
        this.background = myzebragame.resources.getImage(background);
        this.backgroundGround = myzebragame.resources.getImage(backgroundGround);
        bgPos1 = new Vector2(0, -0);
        bgPos2 = new Vector2(MyZebraGame.WORLD_WIDTH, -0);
    }

    //intializing the soundbutton and its on/off logic
    public void loadMusic() {
        soundRD = new TextureRegionDrawable[2];
        soundRD[0] = new TextureRegionDrawable(new TextureRegion(myzebragame.resources.getImage("sound_on")));
        soundRD[1] = new TextureRegionDrawable(new TextureRegion(myzebragame.resources.getImage("sound_off")));

        //setting the image button to be correct corresponding to sound on or off
        if(soundOn){
            soundBtn = new ImageButton(soundRD[0]);
        }
        else{
            soundBtn = new ImageButton(soundRD[1]);
        }
        //initializing the sound with the button, for when an user turn it on or off
        soundBtn.addListener(new ClickListener() {
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(soundOn){
                    soundBtn.getStyle().imageUp = soundRD[1];
                    SoundManager.getInstance().music(soundOn);
                    soundOn = false;
                } else {
                    soundBtn.getStyle().imageUp = soundRD[0];
                    SoundManager.getInstance().music(soundOn);
                    soundOn = true;
                }
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(gamecam.combined);

        //draw the background and its ground to the screen
        if(gsm.getState() == playState){

        }
        else{
            stage.getBatch().begin();
            stage.getBatch().draw(background, bgPos1.x, bgPos1.y);
            stage.getBatch().draw(background, bgPos2.x, bgPos1.y);
            stage.getBatch().draw(backgroundGround, groundPos1.x, groundPos1.y);
            stage.getBatch().draw(backgroundGround, groundPos2.x, groundPos2.y);
            stage.getBatch().end();
        }
        stage.draw();
    }

    //resizes the viewport by updating it
    public void resize(int width, int height) {
        viewPort.update(width, height);
    }

    @Override
    public void dispose() { stage.dispose(); }

    //Updating the ground position depending on the size of the screen
    protected void updateGround() {
        groundPos1.add(groundSpeed,0);
        groundPos2.add(groundSpeed,0);
        bgPos1.add(backgroundSpeed,0);
        bgPos2.add(backgroundSpeed,0);

        if (groundPos1.x <= -MyZebraGame.WORLD_WIDTH) {
            groundPos1.x=MyZebraGame.WORLD_WIDTH;
        }
        if (groundPos2.x <= -MyZebraGame.WORLD_WIDTH) {
            groundPos2.x=MyZebraGame.WORLD_WIDTH;
        }
        if (bgPos1.x <= -MyZebraGame.WORLD_WIDTH) {
            bgPos1.x=MyZebraGame.WORLD_WIDTH;
        }
        if (bgPos2.x <= -MyZebraGame.WORLD_WIDTH) {
            bgPos2.x=MyZebraGame.WORLD_WIDTH;
        }
    }
}
