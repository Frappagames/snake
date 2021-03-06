package com.frappagames.snake.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;

/**
 * Snake element
 */

public class SnakePart {
    public enum Direction {LEFT, RIGHT, TOP, DOWN}
    enum PartType {HEAD, BODY, TAIL}

    private Direction direction;
    private int x, y;

    SnakePart(Direction direction, int x, int y) {
        this.setDirection(direction);
        this.setX(x);
        this.setY(y);
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    void draw(Batch batch, PartType type) {
        TextureRegion texture;
        int rotation;

        // Define texture to draw
        switch (type) {
            case HEAD:  texture = Assets.snakeHead; break;
            case TAIL:  texture = Assets.snakeTail; break;
            default:    texture = Assets.snakePart; break;
        }

        // Define rotation to apply depending on the direction
        switch (direction) {
            case TOP:   rotation = 0;   break;
            case DOWN:  rotation = 180; break;
            case LEFT:  rotation = 90;  break;
            default:    rotation = -90;
        }

        batch.draw(
                texture,
                (this.x * Snake.TILE_SIZE) + Snake.DRAW_OFFSET,
                (this.y * Snake.TILE_SIZE) + Snake.DRAW_OFFSET,
                Snake.TILE_SIZE / 2,
                Snake.TILE_SIZE / 2,
                Snake.TILE_SIZE,
                Snake.TILE_SIZE,
                1,
                1,
                rotation,
                false
        );
    }

    void move() {
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
