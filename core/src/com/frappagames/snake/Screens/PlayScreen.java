package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;
import com.frappagames.snake.Objects.Apple;
import com.frappagames.snake.Objects.SnakeBody;
import com.frappagames.snake.Objects.SnakePart;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;

/**
 * Created by gfp on 23/03/17.
 */

public class PlayScreen extends GameScreen {
    private static final int MOVE_SPEED = 100;
    private SnakeBody snake;
    private Apple apple;
    private Snake game;
    private long lastMoveTime;

    public PlayScreen(Snake game) {
        super(game);

        this.game = game;
        this.snake = new SnakeBody(SnakePart.Direction.RIGHT, 10, 10);
        this.apple = new Apple();
        this.lastMoveTime = 0;
    }

    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            this.snake.turn(SnakePart.Direction.TOP);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            this.snake.turn(SnakePart.Direction.DOWN);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            this.snake.turn(SnakePart.Direction.LEFT);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            this.snake.turn(SnakePart.Direction.RIGHT);
        }

        // Refresh screen
        if (TimeUtils.millis() > lastMoveTime + MOVE_SPEED) {
            lastMoveTime = TimeUtils.millis();
            moveSnake();
        }
    }

    private void moveSnake() {
        this.snake.move();

        // Vérification des colisions avec la pomme
        if (snake.intersect(apple.getX(), apple.getY())) {
            snake.addPart();

            do {
                apple.reset();
            } while(snake.inSnake(apple.getX(), apple.getY()));
        }
    }

    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.gameBackground, 0, 0);
        this.snake.draw(game.batch);
        this.apple.draw(game.batch);
        game.batch.end();
    }
}
