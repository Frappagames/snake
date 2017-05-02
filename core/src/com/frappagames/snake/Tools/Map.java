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

    public Map(int mapNumber) {
        this.map = Assets.maps[mapNumber - 1].toCharArray();
        this.wallCount = 0;

        // Count walls
        for (char c : map) {
            if (c == '1') {
                this.wallCount++;
            }
        }
    }

    public boolean collideWall(Vector2 position) {
        int offset = Math.round(position.y * 20 + position.x);

        return (map[offset] == '1');
    }

    public void draw(Batch batch) {
        int i = 0;
        for (char c : map) {
            if (c == '1') {
                int x = (i % 20) * Snake.TILE_SIZE + Snake.DRAW_OFFSET;
                int y = ((int) Math.ceil(i / 20)) * Snake.TILE_SIZE + Snake.DRAW_OFFSET;
                batch.draw(Assets.wall, x, y);
            }

            i++;
        }
    }

    public int getWallCount() {
        return wallCount;
    }
}
