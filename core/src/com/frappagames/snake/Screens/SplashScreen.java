package com.frappagames.snake.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.frappagames.snake.Snake;

/**
 * Splash screen for Frappagames
 *
 * Created by Jérémy MOREAU on 14/08/15.
 */
public class SplashScreen implements Screen {
    private final Snake game;

    private OrthographicCamera camera;

    private Texture splashTexture;
    private Stage stage;

    public SplashScreen(Snake gameApp) {
        this.game = gameApp;

        stage = new Stage();
        splashTexture = new Texture(Gdx.files.internal("SplashScreen.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 128, 80);

        Image splashImage = new Image(splashTexture);
        stage.addActor(splashImage);
        splashImage.setX(64 - (splashImage.getWidth() / 2));
        splashImage.setY(40 - (splashImage.getHeight() / 2));

        splashImage.addAction(Actions.sequence(
                Actions.alpha(0),
                Actions.fadeIn(0.5f),
                Actions.delay(1),
                Actions.fadeOut(0.5f),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new PlayScreen(game));
                    }
                })));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setCamera(camera);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        splashTexture.dispose();
        stage.dispose();
    }
}