package com.frappagames.snake.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

/**
 * Settings class provide an interface for manage preferences
 *
 * Created by jmoreau on 19/08/15.
 */
public class Settings {
    private static Preferences settings;

    public static boolean soundEnabled;
    public static String scores;

    public static void load () {
        settings 	 = Gdx.app.getPreferences("com.frappagames.snake.settings");
        soundEnabled = settings.getBoolean("sound", true);
        scores       = settings.getString("scores", "");
        if (scores.equals("")) {
            initialiseScores();
        }
    }

    public static void setSound(Boolean active) {
        soundEnabled = active;
        settings.putBoolean("sound", active);
        settings.flush();
    }

    private static void initialiseScores() {
        int[][] scoresTab = new int[8][5];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                scoresTab[i][j] = 0;
            }
        }

        Json json = new Json();
        String jsonString = json.toJson(scoresTab);

        scores = jsonString;

        settings.putString("scores", jsonString);
        settings.flush();
    }

    public static Array getScore(int map) {
        Json json = new Json();
        Array bestScores = json.fromJson(Array.class, scores);

        return (Array) bestScores.get(map - 1);
    }

    public static void setScore(int map, int score) {
        Json json = new Json();
        Array bestScores = json.fromJson(Array.class, scores);
        Array mapScores = (Array) bestScores.get(map - 1);

        for (int i = 0; i < 5; i++) {
            Integer currentScore;
            if (mapScores.get(i).getClass() == Float.class) {
                currentScore = Math.round((Float) mapScores.get(i));
            } else {
                currentScore = (Integer) mapScores.get(i);
            }

            if (currentScore < score) {
                mapScores.set(i, score);
                score = currentScore;
            }
        }

        bestScores.set(map - 1, mapScores.toArray());

        String jsonString = json.toJson(bestScores.toArray());
        scores = jsonString;

        settings.putString("scores", jsonString);
        settings.flush();
    }
}
