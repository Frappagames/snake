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
import com.frappagames.snake.Tools.Settings;

/**
 * Snake game play screen
 */

class PlayScreen extends GameScreen {
    private static final int DEFAULT_MOVE_SPEED = 375;
    private static final int SPEED_STEP = 50;
    private SnakeBody snake;
    private Apple apple;
    private Snake game;
    private long lastMoveTime;
    private int currentSpeed, currentMap;

    @Override
    public void show() {
        super.show();

        // Play Music ♫
        if (Settings.soundEnabled) Assets.music.play();
    }

    PlayScreen(Snake game, int currentSpeed, int currentMap) {
        super(game);

        System.out.println(currentSpeed);
        System.out.println(currentMap);

        this.game = game;
        this.currentSpeed = currentSpeed;
        this.currentMap = currentMap;
        this.snake = new SnakeBody(SnakePart.Direction.RIGHT, 10, 10);
        this.apple = new Apple();
        this.lastMoveTime = 0;
    }

    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Assets.playSound(Assets.clickSound);
            game.setScreen(new MenuScreen(game));
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
        if (TimeUtils.millis() > lastMoveTime + (DEFAULT_MOVE_SPEED - (SPEED_STEP * currentSpeed))) {
            lastMoveTime = TimeUtils.millis();
            moveSnake();
        }
    }

    private void moveSnake() {
        this.snake.move();

        // Vérification des colisions avec la pomme
        if (snake.intersect(apple.getX(), apple.getY())) {
            Assets.playSound(Assets.powerupSound);
            snake.addPart();

            do {
                apple.reset();
            } while(snake.inSnake(apple.getX(), apple.getY()));
        }

        // Check colision with himself
        if (snake.eatHimself()) {
            Assets.playSound(Assets.loseSound);
            game.setScreen(new PlayScreen(game, currentSpeed, currentMap)); // restart
        }
    }

    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.gameBackground, 0, 0);
        this.snake.draw(game.batch);
        this.apple.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void pause() {
        super.pause();
        if (Settings.soundEnabled) Assets.music.pause();
    }

    @Override
    public void resume() {
        super.resume();
        if (Settings.soundEnabled) Assets.music.play();
    }

    @Override
    public void hide() {
        super.hide();
        if (Settings.soundEnabled) Assets.music.stop();
    }
}
