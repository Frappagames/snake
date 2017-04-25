package com.frappagames.snake.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.frappagames.snake.Snake;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Snake.WIDTH * 4, Snake.HEIGHT * 4);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new Snake();
        }
}