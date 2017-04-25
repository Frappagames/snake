package com.frappagames.snake.Screens;

import com.frappagames.snake.Snake;
import com.frappagames.snake.Tools.Assets;
import com.frappagames.snake.Tools.InfosPageScreen;

/**
 * Display Help page
 */

class HelpScreen extends InfosPageScreen {
    HelpScreen(final Snake game) {
        super(game, Assets.titleHelp, Assets.helpImages);
    }
}
