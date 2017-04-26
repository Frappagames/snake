package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.frappagames.snake.Tools.Settings;

/**
 * Created by gfp on 25/04/17.
 */

class OptionsScreen extends GameScreen {
    private final ImageButton onBtn;
    private final ImageButton offBtn;

    OptionsScreen(final Snake game) {
        super(game);

        // Define Page table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);    // Add table to stage for display

        // Display page title
        Image titleImg = new Image(Assets.titleOptions);
        table.add(titleImg).pad(2).colspan(3).row();

        onBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnOn),
                new TextureRegionDrawable(Assets.btnOnOver),
                new TextureRegionDrawable(Assets.btnOnOver)
        );
        onBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                enableSound();
            }
        });

        offBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnOff),
                new TextureRegionDrawable(Assets.btnOffOver),
                new TextureRegionDrawable(Assets.btnOffOver)
        );
        offBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                disableSound();
            }
        });

        if (Settings.soundEnabled) {
            onBtn.setChecked(true);
            offBtn.setChecked(false);
        } else {
            onBtn.setChecked(false);
            offBtn.setChecked(true);
        }

        Label soundTxt = new Label("SOUND:", Assets.labelStyleBold);
        table.add(soundTxt).pad(2).align(Align.left).expandX();
        table.add(onBtn).pad(2);
        table.add(offBtn).pad(2);
        table.row();
        table.add().expand().row();

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
        table.add(backBtn).colspan(3).row();
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Assets.playSound(Assets.clickSound);
            game.setScreen(new MenuScreen(game));
        }

        // Enable sound
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            enableSound();
        }

        // Disable sound
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            disableSound();
        }
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }

    private void enableSound() {
        if (!Settings.soundEnabled) {
            Settings.setSound(true);
            Assets.playSound(Assets.clickSound);
        }
        onBtn.setChecked(true);
        offBtn.setChecked(false);
    }

    private void disableSound() {
        if (Settings.soundEnabled) {
            Settings.setSound(false);
        }
        onBtn.setChecked(false);
        offBtn.setChecked(true);
    }
}
