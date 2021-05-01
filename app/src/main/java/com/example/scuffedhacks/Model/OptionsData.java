package com.example.scuffedhacks.Model;

public class OptionsData {
    private int difficulty;

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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
