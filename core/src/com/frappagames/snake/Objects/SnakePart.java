package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;

/**
 * Snake element
 */

public class SnakePart {
    public enum Direction {LEFT, RIGHT, TOP, DOWN};
    public static final int PART_SIZE = 5;

    private Direction direction;
    private int x, y;

    public SnakePart(Direction direction, int x, int y) {
        this.setDirection(direction);
        this.setX(x);
        this.setY(y);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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
        batch.draw(Assets.snakePart, this.x * PART_SIZE, this.y * PART_SIZE);
    }

    public void move() {
        switch (this.direction) {
            case LEFT:  this.x--; break;
            case RIGHT: this.x++; break;
            case TOP:   this.y++; break;
            case DOWN:  this.y--; break;
        }

        if (this.x < 0) this.x = Snake.GRID_WIDTH - 1;
        if (this.x >= Snake.GRID_WIDTH) this.x = 0;
        if (this.y < 0) this.y = Snake.GRID_HEIGHT - 1;
        if (this.y >= Snake.GRID_HEIGHT) this.y = 0;
    }
}
