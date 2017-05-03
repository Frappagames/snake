package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;

/**
 * Menu Screen
 */

public class MenuScreen extends GameScreen {
    private Snake game;
    private int currentOption;
    private ImageButton startBtn, scoresBtn, optionsBtn, helpBtn, aboutBtn, exitBtn;

    public MenuScreen(final Snake game) {
        super(game);

        this.game = game;
        this.currentOption = 1;

        // Define Menu table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.align(Align.right);      // Align menu to right
        table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);         // Add table to stage for display

        // Start button : Launch "select difficulty" screen to start a new game
        startBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnStart),
                new TextureRegionDrawable(Assets.btnStartOver),
                new TextureRegionDrawable(Assets.btnStartOver)
        );
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new DifficultyScreen(game));
            }
        });
        table.add(startBtn).pad(1, 0, 0, 0).row();

        // Score button : Display scores screen
        scoresBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnScores),
                new TextureRegionDrawable(Assets.btnScoresOver),
                new TextureRegionDrawable(Assets.btnScoresOver)
        );
        scoresBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new ScoreScreen(game));
            }
        });
        table.add(scoresBtn).pad(1, 0, 0, 0).row();

        // Option button : Display options screen (for enable or disable sounds)
        optionsBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnOptions),
                new TextureRegionDrawable(Assets.btnOptionsOver),
                new TextureRegionDrawable(Assets.btnOptionsOver)
        );
        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new OptionsScreen(game));
            }
        });
        table.add(optionsBtn).pad(1, 0, 0, 0).row();

        // Help button : Display help screens (explain game rules)
        helpBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnHelp),
                new TextureRegionDrawable(Assets.btnHelpOver),
                new TextureRegionDrawable(Assets.btnHelpOver)
        );
        helpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new HelpScreen(game));
            }
        });
        table.add(helpBtn).pad(1, 0, 0, 0).row();

        // About button : Display about screens to show credits
        aboutBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnAbout),
                new TextureRegionDrawable(Assets.btnAboutOver),
                new TextureRegionDrawable(Assets.btnAboutOver)
        );
        aboutBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                game.setScreen(new AboutScreen(game));
            }
        });
        table.add(aboutBtn).pad(1, 0, 0, 0).row();

        // Exit button : Quit the game
        exitBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnExit),
                new TextureRegionDrawable(Assets.btnExitOver),
                new TextureRegionDrawable(Assets.btnExitOver)
        );
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                Gdx.app.exit();
            }
        });
        table.add(exitBtn).pad(2, 0, 2, 0).row();

        checkButton();
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Assets.playSound(Assets.clickSound);
            Gdx.app.exit();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && currentOption > 1) {
            Assets.playSound(Assets.clickSound);
            currentOption--;
            checkButton();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && currentOption < 6) {
            Assets.playSound(Assets.clickSound);
            currentOption++;
            checkButton();
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Assets.playSound(Assets.clickSound);
            switch (currentOption) {
                case 1: game.setScreen(new DifficultyScreen(game)); break;
                case 2: game.setScreen(new ScoreScreen(game));      break;
                case 3: game.setScreen(new OptionsScreen(game));    break;
                case 4: game.setScreen(new HelpScreen(game));       break;
                case 5: game.setScreen(new AboutScreen(game));      break;
                case 6: Gdx.app.exit();                             break;
            }
        }
    }

    private void checkButton() {
        startBtn.setChecked(currentOption == 1);
        scoresBtn.setChecked(currentOption == 2);
        optionsBtn.setChecked(currentOption == 3);
        helpBtn.setChecked(currentOption == 4);
        aboutBtn.setChecked(currentOption == 5);
        exitBtn.setChecked(currentOption == 6);
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.draw(Assets.imgMenu, Snake.DRAW_OFFSET, Snake.TILE_SIZE);
        game.batch.end();
    }
}
