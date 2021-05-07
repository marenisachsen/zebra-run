package com.zebra.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zebra.game.controller.Animation.Poof;
import com.zebra.game.controller.GameController;
import com.zebra.game.controller.ObstacleController;
import com.zebra.game.controller.ObstacleMapController;
import com.zebra.game.controller.State.MenuState;
import com.zebra.game.controller.TouchHandler;
import com.zebra.game.model.CurtainModel;
import com.zebra.game.model.Obstacle.ObstacleModel;
import com.zebra.game.model.ObstacleMap;
import com.zebra.game.model.ZebraModel;
import com.zebra.game.controller.ZebraController;

import java.util.List;

public class MyZebraGame extends Game {
	//Sets the width and height of the game to the values as our emulator
	public static final int WORLD_WIDTH = 2240;
	public static final int WORLD_HEIGHT = 1080;
	public static final String title = "En sebra som kan se bra";
	//creates methods to provide sound and graphics
	public static Resources resources;
	private SoundManager soundManager;
	//creates methods for game logic
	private ZebraModel zebramodel;
	private CurtainModel curtainmodel;
	//creates methods to control the game
	private GameStateManager gsm;
	private ZebraController zebracontroller;
	private GameController gamecontroller;
	private ObstacleController obstaclecontroller;
	private ObstacleMapController obstaclemapcontroller;
	private TouchHandler touchhandler;
	//creates the SpriteBatch to be used throughout the game
	private SpriteBatch batch;
	//creates game objects
	private ObstacleMap obstaclemap;
	private List<ObstacleModel> obstacles;
	private Poof poof;
	private float timeElapsed;

	//Firebase DB setup
	FireBaseInterface _FBIC;
	public MyZebraGame(FireBaseInterface FBIC){
		_FBIC = FBIC;
	}
	public FireBaseInterface get_FBIC(){
		return  _FBIC;
	}


	//initializes objects at compile time
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = GameStateManager.getInstance(this);
		resources = Resources.getInstance();
		soundManager = SoundManager.getInstance();
		timeElapsed = Gdx.graphics.getDeltaTime();
		gsm.push(new MenuState(gsm));
		SoundManager.getInstance().music(true);

		obstaclemap = new ObstacleMap();
		obstaclemapcontroller = new ObstacleMapController(obstaclemap);
		obstacles = obstaclemap.getObstacles();
		obstaclecontroller = new ObstacleController(obstacles);
		zebramodel = ZebraModel.getInstance();
		zebracontroller = new ZebraController(zebramodel);
		curtainmodel = new CurtainModel();
		touchhandler = new TouchHandler(zebracontroller);
		gamecontroller = new GameController(zebracontroller, obstaclecontroller, curtainmodel,obstaclemapcontroller);
		poof = new Poof();
	}

	//main render function of the game
	@Override
	public void render () {
		super.render();
		gsm.update(timeElapsed);
	}
	//getter to pass around the spritebatch
	public SpriteBatch getBatch(){
		return batch;
	}

	//getter to pass around the game state manager
	public GameStateManager getGsm(){ return gsm; }

	//getter to pass around the controller of the zebra model
	public ZebraController getZebracontroller() {
		return zebracontroller;
	}

	//getter to pass around the controller of the controllers
	public GameController getGamecontroller() {
		return gamecontroller;
	}

	//getter to pass around the curtain model
	public CurtainModel getCurtainmodel() {
		return curtainmodel;
	}

	//getter to pass around the zebra model
	public ZebraModel getZebramodel() {
		return zebramodel;
	}

	//getter to pass around the obstacle controller
	public ObstacleController getObstaclecontroller() {
		return obstaclecontroller;
	}

	//getter to pass around the obstacles
	public List<ObstacleModel> getObstacles() {
		return obstacles;
	}

	//getter to pass around the touch handler which handles touch input
	public TouchHandler getTouchhandler() {
		return touchhandler;
	}

	//getter to pass around the poof model
	public Poof getPoof() {
		return poof;
	}

	//dispose method
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}

}
