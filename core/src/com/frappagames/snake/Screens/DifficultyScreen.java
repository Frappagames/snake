package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;

/**
 * Choose a difficulty and a map
 */

class DifficultyScreen extends GameScreen {
    private int currentSpeed = 3;
    private Image currentSpeedImage;
    private int currentMap = 1;
    private Image currentMapImage;
    private int currentOption;
    private ImageButton decreaseSpeedBtn, increaseSpeedBtn, decreaseMapBtn, increaseMapBtn, backBtn, startBtn;

    DifficultyScreen(final Snake game) {
        super(game);

        this.currentOption = 1;

        // Define Page table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);    // Add table to stage for display

        // Display page title
        Image titleImg = new Image(Assets.titleDifficulty);
        table.add(titleImg).pad(2).colspan(5).row();

        // Select currentSpeed
        // Label
        Label speedLabel = new Label("SPEED:", Assets.labelStyleBold);
        table.add(speedLabel).pad(2).align(Align.left);
        table.add().expandX();

        // Decrease currentSpeed button
        decreaseSpeedBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnLeft),
                new TextureRegionDrawable(Assets.btnLeftOver),
                new TextureRegionDrawable(Assets.btnLeftOver)
        );
        decreaseSpeedBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentSpeed > 1) {
                    Assets.playSound(Assets.clickSound);
                    currentSpeed--;
                }
            }
        });
        table.add(decreaseSpeedBtn).pad(2, 0, 2, 0);

        // current currentSpeed
        currentSpeedImage = new Image();
        table.add(currentSpeedImage).width(44).height(8).pad(2, 0, 2, 0);

        // Increase currentSpeed button
        increaseSpeedBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnRight),
                new TextureRegionDrawable(Assets.btnRightOver),
                new TextureRegionDrawable(Assets.btnRightOver)
        );
        increaseSpeedBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentSpeed < 5) {
                    Assets.playSound(Assets.clickSound);
                    currentSpeed++;
                }
            }
        });
        table.add(increaseSpeedBtn).pad(2, 0, 2, 0);

        table.row();

        // Select Map
        // Label
        Label mapLabel = new Label("LEVEL:", Assets.labelStyleBold);
        table.add(mapLabel).pad(2).align(Align.left);
        table.add().expandX();

        // Decrease currentMap button
        decreaseMapBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnLeft),
                new TextureRegionDrawable(Assets.btnLeftOver),
                new TextureRegionDrawable(Assets.btnLeftOver)
        );
        decreaseMapBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                decreaseMap();
            }
        });
        table.add(decreaseMapBtn).pad(2, 0, 2, 0);

        // current currentMap
        currentMapImage = new Image();
        table.add(currentMapImage).width(44).height(22).pad(2, 0, 2, 0);

        // Increase currentMap button
        increaseMapBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnRight),
                new TextureRegionDrawable(Assets.btnRightOver),
                new TextureRegionDrawable(Assets.btnRightOver)
        );
        increaseMapBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                increaseMap();
            }
        });
        table.add(increaseMapBtn).pad(2, 0, 2, 0);

        table.row();

        table.add().colspan(5).expand().row();

        // Back and start buttons : back to menu or launch the game
        backBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnBack),
                new TextureRegionDrawable(Assets.btnBackOver),
                new TextureRegionDrawable(Assets.btnBackOver)
        );
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new MenuScreen(game));
            }
        });

        startBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnStart),
                new TextureRegionDrawable(Assets.btnStartOver),
                new TextureRegionDrawable(Assets.btnStartOver)
        );
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new PlayScreen(game, currentSpeed, currentMap));
            }
        });
        table.add(backBtn);
        table.add().expandX();
        table.add(startBtn).colspan(3).row();

        checkButton();
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MenuScreen(game));
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (currentOption == 4) {
                game.setScreen(new PlayScreen(game, currentSpeed, currentMap));
            }

            if (currentOption == 3) {
                game.setScreen(new MenuScreen(game));
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentOption > 1) {
            Assets.playSound(Assets.clickSound);
            currentOption--;
            checkButton();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentOption < 4) {
            Assets.playSound(Assets.clickSound);
            currentOption++;
            checkButton();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (currentOption == 1 && currentSpeed > 1) {
                Assets.playSound(Assets.clickSound);
                currentSpeed--;
            }
            if (currentOption == 2) {
                decreaseMap();
            }
            if (currentOption == 4) {
                Assets.playSound(Assets.clickSound);
                currentOption--;
                checkButton();
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (currentOption == 1 && currentSpeed < 5) {
                Assets.playSound(Assets.clickSound);
                currentSpeed++;
            }
            if (currentOption == 2) {
                increaseMap();
            }
            if (currentOption == 3) {
                Assets.playSound(Assets.clickSound);
                currentOption++;
                checkButton();
            }
        }

        currentSpeedImage.setDrawable(new TextureRegionDrawable(Assets.speedImages.get(currentSpeed - 1)));
        currentMapImage.setDrawable(new TextureRegionDrawable(Assets.miniMap.get(currentMap - 1)));
    }

    private void increaseMap() {
        currentMap++;

        if (currentMap > Assets.miniMap.size()) {
            currentMap = 1;
        }

        Assets.playSound(Assets.clickSound);
    }

    private void decreaseMap() {
        currentMap--;

        if (currentMap <= 0) {
            currentMap = Assets.miniMap.size();
        }

        Assets.playSound(Assets.clickSound);
    }

    private void checkButton() {
        decreaseSpeedBtn.setChecked(currentOption == 1);
        increaseSpeedBtn.setChecked(currentOption == 1);
        decreaseMapBtn.setChecked(currentOption == 2);
        increaseMapBtn.setChecked(currentOption == 2);
        backBtn.setChecked(currentOption == 3);
        startBtn.setChecked(currentOption == 4);
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }
}
