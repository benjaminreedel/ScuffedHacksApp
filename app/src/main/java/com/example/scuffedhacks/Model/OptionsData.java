package com.example.scuffedhacks.Model;

public class OptionsData {
    private int difficulty;
    private int score;
    public boolean scuffed = false;

    // Singleton
    private static OptionsData instance;

    private OptionsData() {
        difficulty = 1;
    }

    public static OptionsData getInstance() {
        if (instance == null) {
            instance = new OptionsData();
        }
        return instance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
