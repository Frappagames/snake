package com.frappagames.snake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frappagames.snake.Snake;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Snake.HEIGHT * 4;
		config.width = Snake.WIDTH * 4;
		config.title = Snake.TITLE;
		new LwjglApplication(new Snake(), config);
	}
}
