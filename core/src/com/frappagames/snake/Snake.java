/**
 * TODO : * MENU
 * TODO : * Page infos
 * TODO : * Sélection de difficulté
 * TODO : * Navigation clavier
 * TODO : * Ecran GameOver
 * TODO : * Ecran Meilleurs scores
 * TODO : * Affichage des scores in-game
 * TODO : * Lien menu in-game
 * TODO : * Gestion des colisions sur le serpent
 * TODO : * Gestion de MAP
 * TODO : * Gestion des MURS
 * TODO : * Gestion des niveaux (par MAP)
 */

package com.frappagames.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.frappagames.snake.Objects.SnakePart;
import com.frappagames.snake.Screens.SplashScreen;
import com.frappagames.snake.Tools.Assets;

public class Snake extends Game {
	public static final String TITLE  = "SNAKE";
	public static final int    GRID_WIDTH  = 40;
	public static final int    GRID_HEIGHT = 20;
	public static final int    WIDTH  = SnakePart.PART_SIZE * GRID_WIDTH;
	public static final int    HEIGHT = SnakePart.PART_SIZE * GRID_HEIGHT;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		Assets.load();
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
