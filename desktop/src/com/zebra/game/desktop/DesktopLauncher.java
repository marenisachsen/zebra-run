package com.zebra.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zebra.game.FireBaseInterface;
import com.zebra.game.MyZebraGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		new LwjglApplication(new MyZebraGame(new DesktopInterfaceClass()),config);
		//new LwjglApplication(new MyZebraGame(new DesktopInterfaceClass()), config);


		config.width = MyZebraGame.WORLD_WIDTH;
		config.height = MyZebraGame.WORLD_HEIGHT;
		config.title = MyZebraGame.title;
	}
}
