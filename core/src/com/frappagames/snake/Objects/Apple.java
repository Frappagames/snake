package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;

/**
 * Apple object
 */

public class Apple {
    private static final int APPLE_SIZE = 6;
    private int x, y;

    public Apple() {
        this.reset();
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void draw(Batch batch) {
        batch.draw(
            Assets.apple,
            (this.x * Snake.TILE_SIZE) + Snake.DRAW_OFFSET,
            (this.y * Snake.TILE_SIZE) + Snake.DRAW_OFFSET
        );
    }

    public void reset() {
        this.setX(MathUtils.random(0, Snake.GRID_WIDTH - 1));
        this.setY(MathUtils.random(0, Snake.GRID_HEIGHT - 1));
    }
}
