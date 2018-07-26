package org.academiadecodigo.hackathon.golf.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.academiadecodigo.hackathon.golf.TheGame;
import org.academiadecodigo.hackathon.golf.ToyGame;
import org.academiadecodigo.hackathon.golf.Grid;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "toyGame";
		config.width = 1024;
		config.height = 768;
		new LwjglApplication(new TheGame(), config);
	}
}
