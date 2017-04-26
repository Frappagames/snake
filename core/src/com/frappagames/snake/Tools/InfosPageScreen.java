package com.frappagames.snake.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.frappagames.snake.Screens.MenuScreen;
import com.frappagames.snake.Snake;

import java.util.ArrayList;

/**
 * Created by gfp on 25/04/17.
 */

public class InfosPageScreen extends GameScreen {
    private Image currentPage;
    private static final float initialX = 10;
    private int currentPageNumber = 0;
    private ArrayList<TextureRegion> pagesImages;
    private TextureRegion pageTitle;

    public InfosPageScreen(final Snake game, TextureRegion title, ArrayList<TextureRegion> images) {
        super(game);

        pageTitle = title;
        pagesImages = images;

        // Define Page table
        Table tablePage = new Table();
        tablePage.setFillParent(true);     // Display on all screen
        tablePage.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(tablePage);    // Add table to stage for display

        currentPage = new Image();
        currentPage.setDrawable(new TextureRegionDrawable(pagesImages.get(currentPageNumber)));
        tablePage.add(currentPage).align(Align.top).expand().padTop(12);

        // Define UI table
        Table table = new Table();
        table.setFillParent(true);     // Display on all screen
        table.pad(Snake.DRAW_OFFSET);  // Add padding
        this.stage.addActor(table);    // Add table to stage for display

        Image titleImg = new Image(pageTitle);
        table.add(titleImg).colspan(3).row();

        ImageButton leftBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnLeft),
                new TextureRegionDrawable(Assets.btnLeftOver)
        );
        leftBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                changeLeft();
            }
        });
        ImageButton rightBtn = new ImageButton(
                new TextureRegionDrawable(Assets.btnRight),
                new TextureRegionDrawable(Assets.btnRightOver)
        );
        rightBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Assets.playSound(Assets.clickSound);
                changeRight();
            }
        });

        table.add(leftBtn);
        table.add().expand();
        table.add(rightBtn);
        table.row();

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

    private void changeLeft() {
        currentPageNumber--;
        if (currentPageNumber < 0) {
            currentPageNumber = pagesImages.size() - 1;
        }

        currentPage.addAction(Actions.sequence(
                Actions.moveTo(Snake.WIDTH, currentPage.getY(), Snake.PAGE_TRANSITION, Interpolation.circleIn),
                Actions.moveTo(-Snake.WIDTH, currentPage.getY()),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        currentPage.setDrawable(new TextureRegionDrawable(pagesImages.get(currentPageNumber)));
                    }
                }),
                Actions.moveTo(initialX, currentPage.getY(), Snake.PAGE_TRANSITION, Interpolation.circleOut)
        ));
    }

    private void changeRight() {
        currentPageNumber++;
        if (currentPageNumber >= pagesImages.size()) {
            currentPageNumber = 0;
        }

        currentPage.addAction(Actions.sequence(
                Actions.moveTo(-Snake.WIDTH, currentPage.getY(), Snake.PAGE_TRANSITION, Interpolation.circleIn),
                Actions.moveTo(Snake.WIDTH, currentPage.getY()),
                Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        currentPage.setDrawable(new TextureRegionDrawable(pagesImages.get(currentPageNumber)));
                    }
                }),
                Actions.moveTo(initialX, currentPage.getY(), Snake.PAGE_TRANSITION, Interpolation.circleOut)
        ));
    }

    @Override
    protected void update(float delta) {
        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)
                || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Assets.playSound(Assets.clickSound);
            game.setScreen(new MenuScreen(game));
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            Assets.playSound(Assets.clickSound);
            changeLeft();
        }

        // Check for inputs
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            Assets.playSound(Assets.clickSound);
            changeRight();
        }
    }

    @Override
    protected void draw(float delta) {
        game.batch.begin();
        game.batch.draw(Assets.pageBackground, 0, 0);
        game.batch.end();
    }
}
