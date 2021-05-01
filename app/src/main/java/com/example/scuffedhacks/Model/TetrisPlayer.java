package com.example.scuffedhacks.Model;

import android.os.Bundle;
import android.widget.Button;

import com.example.scuffedhacks.R;

import java.lang.*;

public class TetrisPlayer {


    Grid grid = new Grid();



    public int addscore(int lines) {
        int _score = 0;
        switch (lines) {
            case 1:
                _score += 40;
                break;
            case 2:
                _score += 100;
                break;
            case 3:
                _score += 300;
                break;
            case 4:
                _score += 1200;
                break;
        }
        return _score;
    }

}
