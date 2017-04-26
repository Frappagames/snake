package com.frappagames.snake.Tools;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.frappagames.snake.Snake;

/**
 * Created by gfp on 25/04/17.
 */

public class Map {
    private Snake game;
    private boolean[] map;
    private TextureRegion miniMap;

    public Map(Snake game) {
        this.game = game;
        map = new boolean[180];
        miniMap = Assets.miniMap.get(0);

        map[0] = true;

        for (int i = 0; i < 180; i++) {
            if (i % 20 == 0) {
                System.out.println();
            }

            if (map[i]) {
                System.out.print("#");
            } else {
                System.out.print(" ");
            }
        }
    }

}
