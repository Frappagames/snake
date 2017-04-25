package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;

/**
 * Created by gfp on 25/04/17.
 */

class OptionsScreen extends GameScreen {
    OptionsScreen(Snake game) {
        super(game);
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }
}
