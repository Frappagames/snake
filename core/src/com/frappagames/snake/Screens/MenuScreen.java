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
 * Created by gfp on 24/04/17.
 */

public class MenuScreen extends GameScreen {
    private Snake game;
    protected Table table;

    public MenuScreen(final Snake game) {
        super(game);

        this.game = game;

        // Define Menu table
        this.table = new Table();
        this.table.setFillParent(true);     // Display on all screen
        this.table.align(Align.right);      // Align menu to right
        this.table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);         // Add table to stage for display

        // Start button : Launch "select difficulty" screen to start a new game
        ImageButton startBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnStart),
                new TextureRegionDrawable(Assets.btnStartOver),
                new TextureRegionDrawable(Assets.btnStartOver)
        );
        startBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new DifficultyScreen(game));
            }
        });
        this.table.add(startBtn).pad(1, 0, 0, 0).row();

        // Score button : Display scores screen
        ImageButton scoresBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnScores),
                new TextureRegionDrawable(Assets.btnScoresOver),
                new TextureRegionDrawable(Assets.btnScoresOver)
        );
        scoresBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScoreScreen(game));
            }
        });
        this.table.add(scoresBtn).pad(1, 0, 0, 0).row();

        // Option button : Display options screen (for enable or disable sounds)
        ImageButton optionsBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnOptions),
                new TextureRegionDrawable(Assets.btnOptionsOver),
                new TextureRegionDrawable(Assets.btnOptionsOver)
        );
        optionsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game));
            }
        });
        this.table.add(optionsBtn).pad(1, 0, 0, 0).row();

        // Help button : Display help screens (explain game rules)
        ImageButton helpBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnHelp),
                new TextureRegionDrawable(Assets.btnHelpOver),
                new TextureRegionDrawable(Assets.btnHelpOver)
        );
        helpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new HelpScreen(game));
            }
        });
        this.table.add(helpBtn).pad(1, 0, 0, 0).row();

        // About button : Display about screens to show credits
        ImageButton aboutBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnAbout),
                new TextureRegionDrawable(Assets.btnAboutOver),
                new TextureRegionDrawable(Assets.btnAboutOver)
        );
        aboutBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new AboutScreen(game));
            }
        });
        this.table.add(aboutBtn).pad(1, 0, 0, 0).row();

        // Exit button : Quit the game
        ImageButton exitBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnExit),
                new TextureRegionDrawable(Assets.btnExitOver),
                new TextureRegionDrawable(Assets.btnExitOver)
        );
        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        this.table.add(exitBtn).pad(2, 0, 2, 0).row();

    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
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
        game.batch.draw(Assets.imgMenu, Snake.DRAW_OFFSET, Snake.TILE_SIZE);
        game.batch.end();
    }
}
