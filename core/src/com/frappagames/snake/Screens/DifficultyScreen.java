package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;
import com.frappagames.snake.Tools.Map;

/**
 * Choose a difficulty and a map
 */

class DifficultyScreen extends GameScreen {
    DifficultyScreen(Snake game) {
        super(game);

        Map map = new Map(game);
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new PlayScreen(game));
        }
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }
}
