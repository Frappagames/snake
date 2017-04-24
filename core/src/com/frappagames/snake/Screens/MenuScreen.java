package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;

/**
 * Created by gfp on 24/04/17.
 */

public class MenuScreen extends GameScreen {
    private Snake game;
    protected Table table;

    public MenuScreen(Snake game) {
        super(game);

        this.game = game;

        this.table = new Table();
        this.table.debug();
        this.stage.addActor(table);

        ImageButton startBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnStart),
                new TextureRegionDrawable(Assets.btnStartOver),
                new TextureRegionDrawable(Assets.btnStartOver)
        );
        this.table.add(startBtn).row();

        ImageButton scoresBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnScores),
                new TextureRegionDrawable(Assets.btnScoresOver),
                new TextureRegionDrawable(Assets.btnScoresOver)
        );
        this.table.add(scoresBtn).row();

        ImageButton optionsBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnOptions),
                new TextureRegionDrawable(Assets.btnOptionsOver),
                new TextureRegionDrawable(Assets.btnOptionsOver)
        );
        this.table.add(optionsBtn).row();

        ImageButton helpBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnHelp),
                new TextureRegionDrawable(Assets.btnHelpOver),
                new TextureRegionDrawable(Assets.btnHelpOver)
        );
        this.table.add(helpBtn).row();

        ImageButton aboutBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnAbout),
                new TextureRegionDrawable(Assets.btnAboutOver),
                new TextureRegionDrawable(Assets.btnAboutOver)
        );
        this.table.add(aboutBtn).row();

        ImageButton exitBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnExit),
                new TextureRegionDrawable(Assets.btnExitOver),
                new TextureRegionDrawable(Assets.btnExitOver)
        );
        this.table.add(exitBtn).row();

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
