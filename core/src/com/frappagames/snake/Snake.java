/**
 * TODO : * Navigation clavier
 * TODO : * Ecran GameOver
 * TODO : * Ecran Meilleurs scores
 * TODO : * Gestion de MAP
 * TODO : * Gestion des MURS
 * TODO : * Gestion des niveaux (par MAP)
 */

package com.frappagames.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frappagames.snake.Screens.SplashScreen;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.Settings;

public class Snake extends Game {
	public static final String TITLE  = "SNAKE";
	public static final int    GRID_WIDTH  = 20;
	public static final int    GRID_HEIGHT = 9;
	public static final int    TILE_SIZE = 6;
	public static final int    DRAW_OFFSET = 4;
	public static final int    WIDTH  = 128;
	public static final int    HEIGHT = 72;
	public static final float  PAGE_TRANSITION = 0.4f;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.load();
		Settings.load();
		batch = new SpriteBatch();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
