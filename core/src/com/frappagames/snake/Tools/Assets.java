package com.frappagames.snake.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

/**
 * Assets management class
 *
 * Created by Jérémy MOREAU on 19/08/15.
 */
public class Assets {
    private static TextureAtlas itemsAtlas;

    public static TextureRegion snakePart, apple, snakeHead, snakeTail;
    public static TextureRegion gameBackground, pageBackground, imgMenu;

    public static TextureRegion btnStart, btnAbout, btnExit, btnHelp, btnOptions, btnScores, btnBack, btnLeft, btnRight,
        btnStartOver, btnAboutOver, btnExitOver, btnHelpOver, btnOptionsOver, btnScoresOver, btnBackOver, btnLeftOver, btnRightOver;

    public static TextureRegion titleHelp, titleAbout, titleDifficulty, titleGameOver,
            titleOptions, titleScores;

    public static ArrayList<TextureRegion> aboutImages, helpImages;


//    public static Animation standAnimation, walkAnimation, jumpAnimation;
//
//    public static Sound clickSound;
//    public static Music music;
//
//    public static Label.LabelStyle fontScore;

    public static void load() {
        // Load Textures
        itemsAtlas = new TextureAtlas(Gdx.files.internal("snake.pack"));

        // Snake parts
        snakePart  = new TextureRegion(itemsAtlas.findRegion("snakePart"));
        snakeHead  = new TextureRegion(itemsAtlas.findRegion("snakeHead"));
        snakeTail  = new TextureRegion(itemsAtlas.findRegion("snakeTail"));
        apple      = new TextureRegion(itemsAtlas.findRegion("apple"));

        // Background and pictures
        gameBackground = new TextureRegion(itemsAtlas.findRegion("gameBackground"));
        pageBackground = new TextureRegion(itemsAtlas.findRegion("pageBackground"));
        imgMenu        = new TextureRegion(itemsAtlas.findRegion("imgMenu"));

        // Buttons
        btnStart = new TextureRegion(itemsAtlas.findRegion("btnStart"));
        btnAbout = new TextureRegion(itemsAtlas.findRegion("btnAbout"));
        btnExit = new TextureRegion(itemsAtlas.findRegion("btnExit"));
        btnHelp = new TextureRegion(itemsAtlas.findRegion("btnHelp"));
        btnOptions = new TextureRegion(itemsAtlas.findRegion("btnOptions"));
        btnScores = new TextureRegion(itemsAtlas.findRegion("btnScores"));
        btnBack = new TextureRegion(itemsAtlas.findRegion("btnBack"));
        btnLeft = new TextureRegion(itemsAtlas.findRegion("btnLeft"));
        btnRight = new TextureRegion(itemsAtlas.findRegion("btnRight"));

        btnStartOver = new TextureRegion(itemsAtlas.findRegion("btnStartOver"));
        btnAboutOver = new TextureRegion(itemsAtlas.findRegion("btnAboutOver"));
        btnExitOver = new TextureRegion(itemsAtlas.findRegion("btnExitOver"));
        btnHelpOver = new TextureRegion(itemsAtlas.findRegion("btnHelpOver"));
        btnOptionsOver = new TextureRegion(itemsAtlas.findRegion("btnOptionsOver"));
        btnScoresOver = new TextureRegion(itemsAtlas.findRegion("btnScoresOver"));
        btnBackOver = new TextureRegion(itemsAtlas.findRegion("btnBackOver"));
        btnLeftOver = new TextureRegion(itemsAtlas.findRegion("btnLeftOver"));
        btnRightOver = new TextureRegion(itemsAtlas.findRegion("btnRightOver"));

        // Help page
        titleHelp = new TextureRegion(itemsAtlas.findRegion("titleHelp"));
        helpImages = new ArrayList<TextureRegion>();
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips1")));
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips2")));
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips3")));
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips4")));
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips5")));
        helpImages.add(new TextureRegion(itemsAtlas.findRegion("imgTips6")));

        // About Page
        titleAbout = new TextureRegion(itemsAtlas.findRegion("titleAbout"));
        aboutImages = new ArrayList<TextureRegion>();
        aboutImages.add(new TextureRegion(itemsAtlas.findRegion("imgAbout1")));
        aboutImages.add(new TextureRegion(itemsAtlas.findRegion("imgAbout2")));
        aboutImages.add(new TextureRegion(itemsAtlas.findRegion("imgAbout3")));
        aboutImages.add(new TextureRegion(itemsAtlas.findRegion("imgAbout4")));
        aboutImages.add(new TextureRegion(itemsAtlas.findRegion("imgAbout5")));


//        // Load Music and sounds
//        music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
//        music.setLooping(true);
//        music.setVolume(0.5f);
//
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("sound-click.mp3"));
//
//        // Load animations
//
//        // Stand animation
//        Texture sheet;
//        sheet = new Texture(Gdx.files.internal("stand.png"));
//        TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/4, sheet.getHeight()/2);
//        TextureRegion[] frames = new TextureRegion[8];
//        int index = 0;
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 4; j++) {
//                frames[index++] = tmp[i][j];
//            }
//        }
//        standAnimation = new Animation(0.15f, frames);

    }

//    public static void playSound(Sound sound) {
//        if (Settings.soundEnabled) sound.play(1);
//    }

    public static void dispose() {
        itemsAtlas.dispose();
//        clickSound.dispose();
//        music.dispose();
    }
}
