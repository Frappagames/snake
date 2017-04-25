package com.frappagames.snake.Screens;

import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.InfosPageScreen;

/**
 * Display About page
 */

class AboutScreen extends InfosPageScreen {
    AboutScreen(final Snake game) {
        super(game, Assets.titleAbout, Assets.aboutImages);
    }
}
