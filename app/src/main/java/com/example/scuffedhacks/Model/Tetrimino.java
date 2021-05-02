package com.example.scuffedhacks.Model;

public class Tetrimino {
    public int x1, x2, x3, x4, y1, y2, y3, y4;
    public Tetrimino(int pieceNum){
        // Constructor function that takes a number(0-6) and sets the values for a type of tetrimino
        pieceGenerator(pieceNum);
    }
    public Tetrimino(Tetrimino oldTetrimino){
        // Takes a Tetrimino and sets another one to have identical x and y coordinates
        x1 = oldTetrimino.x1;
        x2 = oldTetrimino.x2;
        x3 = oldTetrimino.x3;
        x4 = oldTetrimino.x4;
        y1 = oldTetrimino.y1;
        y2 = oldTetrimino.y2;
        y3 = oldTetrimino.y3;
        y4 = oldTetrimino.y4;
    }

    public void rotate() {
        // Rotates a tetrimino based on the input given
        int tmp_x1, tmp_y1;
        int tmp_x2, tmp_y2;
        int tmp_x3, tmp_y3;

        tmp_x1 = turnAroundX1(y2);
        tmp_y1 = turnAroundY1(x2);
        x2 = tmp_x1;
        y2 = tmp_y1;

        tmp_x2 = turnAroundX1(y3);
        tmp_y2 = turnAroundX1(x3);
        x3 = tmp_x2;
        y3 = tmp_y2;

        tmp_x3 = turnAroundX1(y4);
        tmp_y3 = turnAroundX1(x4);
        x4 = tmp_x3;
        y4 = tmp_y3;
    }

    private int turnAroundX1(int y){
        return x1 - y + y1;
    }

    private int turnAroundY1(int x){
        return y1 + x - x1;
    }


    public void move(int x,int y) {
        // Adds the x or y value given to all x or y values
        if(x != 0){
            x1+=x;
            x2+=x;
            x3+=x;
            x4+=x;
        } else {
            y1+=y;
            y2+=y;
            y3+=y;
            y4+=y;
        }
    }
    private void pieceGenerator(int pieceNum) {
        // Generates a piece based on a given number
        if (pieceNum == 0) {
            //Long-block
            x1 = 5;
            x2 = 5;
            x3 = 5;
            x4 = 5;
            y1 = 0;
            y2 = 1;
            y3 = 2;
            y4 = 3;
        } else if (pieceNum == 1) {
            //T-block
            x1 = 4;
            x2 = 5;
            x3 = 6;
            x4 = 5;
            y1 = 0;
            y2 = 0;
            y3 = 0;
            y4 = 1;
        } else if (pieceNum == 2) {
            //L-block
            x1 = 4;
            x2 = 4;
            x3 = 4;
            x4 = 5;
            y1 = 0;
            y2 = 1;
            y3 = 2;
            y4 = 2;
        } else if (pieceNum == 3) {
            //J-block
            x1 = 5;
            x2 = 5;
            x3 = 5;
            x4 = 4;
            y1 = 0;
            y2 = 1;
            y3 = 2;
            y4 = 2;
        } else if (pieceNum == 4) {
            //Box-block
            x1 = 4;
            x2 = 5;
            x3 = 4;
            x4 = 5;
            y1 = 0;
            y2 = 0;
            y3 = 1;
            y4 = 1;
        } else if (pieceNum == 5) {
            //Z-Block
            x1 = 5;
            x2 = 5;
            x3 = 4;
            x4 = 4;
            y1 = 0;
            y2 = 1;
            y3 = 1;
            y4 = 2;
        } else if (pieceNum == 6) {
            //S-block
            x1 = 4;
            x2 = 4;
            x3 = 5;
            x4 = 5;
            y1 = 0;
            y2 = 1;
            y3 = 1;
            y4 = 2;
        }
    }


}
