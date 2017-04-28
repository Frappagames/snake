package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.GameScreen;
import com.frappagames.snake.Tools.Settings;

/**
 * Display best scores by map
 */

class ScoreScreen extends GameScreen {
    private final Label levelLbl, score1, score2, score3, score4, score5;
    private int currentMap = 1;
    private Image currentMapImage;

    ScoreScreen(final Snake game) {
        super(game);

        // Define Page table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);    // Add table to stage for display

        // Display page title
        Image titleImg = new Image(Assets.titleScores);
        table.add(titleImg).colspan(5).row();


        // Select Map
        // Decrease currentMap button
        ImageButton decreaseMapBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnLeft),
                new TextureRegionDrawable(Assets.btnLeftOver)
        );
        decreaseMapBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                decreaseMap();
            }
        });
        table.add(decreaseMapBtn);

        // current currentMap
        VerticalGroup mapGroup = new VerticalGroup();
        mapGroup.pad(0, 2, 0, 2);
        mapGroup.space(2);
        mapGroup.align(Align.center);

        Label level = new Label("LEVEL", Assets.labelStyleBold);
        mapGroup.addActor(level);

        currentMapImage = new Image();
        currentMapImage.setSize(44, 22);
        mapGroup.addActor(currentMapImage);

        levelLbl = new Label(String.valueOf(currentMap), Assets.labelStyleBold);
        mapGroup.addActor(levelLbl);
        table.add(mapGroup);

        table.add().expandX();


        // Scores
        VerticalGroup scoreGroup = new VerticalGroup();
        scoreGroup.space(0);
        scoreGroup.align(Align.left);
        score1 = new Label("1-8888", Assets.labelStyleNormal);
        scoreGroup.addActor(score1);
        score2 = new Label("2-8888", Assets.labelStyleNormal);
        scoreGroup.addActor(score2);
        score3 = new Label("3-8888", Assets.labelStyleNormal);
        scoreGroup.addActor(score3);
        score4 = new Label("4-8888", Assets.labelStyleNormal);
        scoreGroup.addActor(score4);
        score5 = new Label("5-8888", Assets.labelStyleNormal);
        scoreGroup.addActor(score5);

        table.add(scoreGroup);

        // Increase currentMap button
        ImageButton increaseMapBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnRight),
                new TextureRegionDrawable(Assets.btnRightOver)
        );
        increaseMapBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                increaseMap();
            }
        });
        table.add(increaseMapBtn).row();

        table.add().colspan(5).expand().row();

        // Back button : back to menu
        ImageButton backBtn = new ImageButton(
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
        table.add(backBtn).colspan(5).row();

        // Récupération des scores
        updateScores(currentMap);
    }

    private void increaseMap() {
        currentMap++;
        if (currentMap > Assets.miniMap.size()) {
            currentMap = 1;
        }
        Assets.playSound(Assets.clickSound);
        updateScores(currentMap);
    }

    private void decreaseMap() {
        currentMap--;
        if (currentMap <= 1) {
            currentMap = Assets.miniMap.size();
        }
        Assets.playSound(Assets.clickSound);
        updateScores(currentMap);
    }

    private void updateScores(int currentMap) {
        Array scores = Settings.getScore(currentMap);
        score1.setText("1-" + formatScore(scores.get(0)));
        score2.setText("2-" + formatScore(scores.get(1)));
        score3.setText("3-" + formatScore(scores.get(2)));
        score4.setText("4-" + formatScore(scores.get(3)));
        score5.setText("5-" + formatScore(scores.get(4)));
    }

    private String formatScore(Object value) {
        String score;

        if (value.getClass() == Float.class) {
            score = String.valueOf(Math.round((Float) value));
        } else {
            score = String.valueOf(value);
        }

        while (score.length() < 4) {
            score = score + " ";
        }

        return score;
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.setScreen(new MenuScreen(game));
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            Assets.playSound(Assets.clickSound);
            decreaseMap();
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            Assets.playSound(Assets.clickSound);
            increaseMap();
        }

        levelLbl.setText(String.valueOf(currentMap));
        currentMapImage.setDrawable(new TextureRegionDrawable(Assets.miniMap.get(currentMap - 1)));
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }
}
