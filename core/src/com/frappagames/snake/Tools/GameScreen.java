package com.frappagames.snake.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.frappagames.snake.Snake;

/**
 * Default game screen
 * Created by jmoreau on 12/01/16.
 */
abstract public class GameScreen implements Screen {
    protected final OrthographicCamera camera;
    private final Viewport viewport;
    protected     Snake               game;
    protected Stage stage;

    public GameScreen(Snake game) {
        this.game = game;
        camera    = new OrthographicCamera();
        viewport  = new FitViewport(Snake.WIDTH, Snake.HEIGHT, camera);
        stage     = new Stage(viewport);
        camera.position.set(Snake.WIDTH / 2, Snake.HEIGHT / 2, 0);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    abstract protected void update(float delta);
    abstract protected void draw(float delta);

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        update(delta);
        draw(delta);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
