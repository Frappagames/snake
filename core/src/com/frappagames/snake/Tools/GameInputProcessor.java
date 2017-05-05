package com.frappagames.snake.Tools;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.frappagames.snake.Snake.Direction;
import com.frappagames.snake.Screens.PlayScreen;

/**
 * Input processor for keyboard
 */
public class GameInputProcessor implements InputProcessor {
    private PlayScreen playScreen;

    public GameInputProcessor(PlayScreen playScreen) {
        this.playScreen = playScreen;
    }

    public boolean keyDown (int keycode) {
        return false;
    }

    public boolean keyUp (int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                playScreen.move(Direction.LEFT);
                break;
            case Input.Keys.RIGHT:
                playScreen.move(Direction.RIGHT);
                break;
            case Input.Keys.UP:
                playScreen.move(Direction.UP);
                break;
            case Input.Keys.DOWN:
                playScreen.move(Direction.DOWN);
                break;
        }

        return false;
    }

    public boolean keyTyped (char character) {
        return false;
    }

    public boolean touchDown (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp (int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged (int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved (int x, int y) {
        return false;
    }

    public boolean scrolled (int amount) {
        return false;
    }
}
