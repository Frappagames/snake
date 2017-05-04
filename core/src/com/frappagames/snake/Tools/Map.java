package com.frappagames.snake.Tools;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.frappagames.snake.Snake;

/**
 * Game map class
 */

public class Map {
    private char[] map;
    private int wallCount;
    private int snakeStartX;
    private int snakeStartY;

    public Map(int mapNumber) {
        this.map = Assets.maps[mapNumber - 1].toCharArray();
        this.wallCount = 0;

        // Count walls
        for (char c : map) {
            if (c == '1') {
                this.wallCount++;
            }
        }

        switch(mapNumber) {
            case 4:
            case 6:
                snakeStartX = 3;
                snakeStartY = 3;
                break;
            case 5:
                snakeStartX = 3;
                snakeStartY = 1;
                break;
            case 7:
                snakeStartX = 2;
                snakeStartY = 3;
                break;
            case 8:
                snakeStartX = 2;
                snakeStartY = 5;
                break;
            default:
                snakeStartX = 2;
                snakeStartY = 4;
        }
    }

    public boolean collideWall(Vector2 position) {
        int offset = Math.round((8 - position.y) * 20 + position.x);

        return (map[offset] == '1');
    }

    public void draw(Batch batch) {
        int i = 0;
        for (char c : map) {
            if (c == '1') {
                int x = (i % 20) * Snake.TILE_SIZE + Snake.DRAW_OFFSET;
                int y = ((int) Math.ceil(i / 20)) * Snake.TILE_SIZE;
                batch.draw(Assets.wall, x, ((9 * Snake.TILE_SIZE) - 2 - y));
            }

            i++;
        }
    }

    public int getWallCount() {
        return wallCount;
    }

    public int getSnakeStartX() {
        return snakeStartX;
    }

    public int getSnakeStartY() {
        return snakeStartY;
    }
}
