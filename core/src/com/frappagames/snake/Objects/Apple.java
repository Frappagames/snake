package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;

/**
 * Created by gfp on 23/03/17.
 */

public class Apple {
    public static final int APPLE_SIZE = 5;
    private int x, y;

    public Apple(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void draw(Batch batch) {
        batch.draw(Assets.apple, this.x * APPLE_SIZE, this.y * APPLE_SIZE);
    }

    public void reset() {
        this.setX(MathUtils.random(0, Snake.GRID_WIDTH - 1));
        this.setY(MathUtils.random(0, Snake.GRID_HEIGHT - 1));
    }
}
