package com.raceofroad.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.raceofroad.game.RaceOfRoadGame;

public class DesktopLauncher {
	public DesktopLauncher() {
	}
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drive away from the police";
		config.width = 800;
		config.height = 600;
		config.resizable = false;
		new LwjglApplication(new RaceOfRoadGame(), config);
	}
}
