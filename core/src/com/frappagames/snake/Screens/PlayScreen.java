package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.frappagames.snake.Objects.Apple;
import com.frappagames.snake.Objects.SnakeBody;
import com.frappagames.snake.Objects.SnakePart;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameGestureListener;
import com.frappagames.snake.Tools.GameInputProcessor;
import com.frappagames.snake.Tools.GameScreen;
import com.frappagames.snake.Tools.Map;
import com.frappagames.snake.Tools.Settings;

import java.util.Locale;

/**
 * Snake game play screen
 */

public class PlayScreen extends GameScreen {
    private static final int DEFAULT_MOVE_SPEED = 380;
    private static final int SPEED_STEP = 60;
    private final Label scoreLabel;
    private SnakeBody snake;
    private Image gameOverImage;
    private Apple apple;
    private Snake game;
    private long lastMoveTime;
    private int currentSpeed, currentMap, currentScore;
    private boolean isPlaying = true;
    private Map map;
    private int areaSize = 0;


    @Override
    public void show() {
        super.show();

        // Play Music ♫
        if (Settings.soundEnabled) Assets.music.play();
        this.isPlaying = true;
        gameOverImage.setVisible(false);
    }

    public PlayScreen(final Snake game, int currentSpeed, int currentMap) {
        super(game);

        this.game = game;
        this.currentSpeed = currentSpeed;
        this.currentMap = currentMap;
        this.map = new Map(currentMap);
        this.currentScore = 0;
        this.snake = new SnakeBody(SnakePart.Direction.RIGHT, map.getSnakeStartX(), map.getSnakeStartY());
        this.apple = new Apple();
        this.areaSize = (Snake.GRID_WIDTH * Snake.GRID_HEIGHT) - this.map.getWallCount();

        // Define Page table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.align(Align.top);        // Displays UI to the top of the screen
        table.pad(0, 1, 0, 1);         // Add padding
        this.stage.addActor(table);    // Add table to stage for display


        // Score Label
        scoreLabel = new Label("000", Assets.labelStyleNormal);
        table.add(scoreLabel).align(Align.left);

        // Separator
        table.add().expandX();

        // Menu Button
        ImageButton menuBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnMenu),
                new TextureRegionDrawable(Assets.btnMenuOver)
        );
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MenuScreen(game));
            }
        });
        table.add(menuBtn).row();

        gameOverImage = new Image();
        table.add(gameOverImage).colspan(3).width(124).height(60).padTop(1).row();

        this.lastMoveTime = 0;

        do {
            apple.reset();
        } while (snake.inSnake(apple.getX(), apple.getY())
                || map.collideWall(new Vector2(apple.getX(), apple.getY())));


        // Define input and gesture processors
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(new GestureDetector(new GameGestureListener(this)));
        inputMultiplexer.addProcessor(new GameInputProcessor(this));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Assets.playSound(Assets.clickSound);
            game.setScreen(new MenuScreen(game));
        }

        if (this.isPlaying) {
            // Refresh screen
            if (TimeUtils.millis() > lastMoveTime + (DEFAULT_MOVE_SPEED - (SPEED_STEP * currentSpeed))) {
                lastMoveTime = TimeUtils.millis();
                moveSnake();
            }
        }
    }

    public void move(Snake.Direction direction) {
        switch (direction) {
            case UP:
                this.snake.turn(SnakePart.Direction.TOP);
                break;
            case DOWN:
                this.snake.turn(SnakePart.Direction.DOWN);
                break;
            case LEFT:
                this.snake.turn(SnakePart.Direction.LEFT);
                break;
            case RIGHT:
                this.snake.turn(SnakePart.Direction.RIGHT);
                break;
        }
    }

    private void moveSnake() {
        this.snake.move();

        // check collision with apple
        if (snake.intersect(apple.getX(), apple.getY())) {
            Assets.playSound(Assets.powerupSound);
            snake.addPart();
            currentScore += currentSpeed;

            String scoreLabelText = String.valueOf(currentScore);
            while (scoreLabelText.length() < 3) {
                scoreLabelText = "0" + scoreLabelText;
            }
            scoreLabel.setText(scoreLabelText);

            // Check if player as win
            if (snake.getSize() == this.areaSize) {
                winScreen();
            } else {
                // Otherwise continue adding apples
                do {
                    apple.reset();
                } while (snake.inSnake(apple.getX(), apple.getY())
                            || map.collideWall(new Vector2(apple.getX(), apple.getY())));
            }
        }

        // Check collision with himself
        if (snake.eatHimself()) {
            gameOverScreen();
        }

        // Check collision with walls
        if (map.collideWall(snake.getHeadPosition())) {
            gameOverScreen();
        }
    }

    private void winScreen() {
        Assets.playSound(Assets.winSound);
        gameOverImage.setVisible(true);
        gameOverImage.setDrawable(new TextureRegionDrawable(Assets.imgWin));
        this.isPlaying = false;
        Settings.setScore(currentMap, currentScore);
    }

    private void gameOverScreen() {
        Assets.music.stop();
        Assets.playSound(Assets.loseSound);
        gameOverImage.setVisible(true);
        gameOverImage.setDrawable(new TextureRegionDrawable(Assets.imgGameOver));
        this.isPlaying = false;
        Settings.setScore(currentMap, currentScore);
    }

    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.gameBackground, 0, 0);
        this.map.draw(game.batch);
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
