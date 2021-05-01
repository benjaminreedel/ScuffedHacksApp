package com.example.scuffedhacks.Model;

public class Tetrimino {
    private int x1;
    private int x2;
    private int x3;
    private int x4;
    private int y1;
    private int y2;
    private int y3;
    private int y4;
    public Tetrimino(int pieceNum){
        pieceGenerator(pieceNum);
    }
    public Tetrimino(Tetrimino oldTetrimino){
        x1 = oldTetrimino.x1;
        x2 = oldTetrimino.x2;
        x3 = oldTetrimino.x3;
        x4 = oldTetrimino.x4;
        y1 = oldTetrimino.y1;
        y2 = oldTetrimino.y2;
        y3 = oldTetrimino.y3;
        y4 = oldTetrimino.y4;
    }
    private void pieceGenerator(int pieceNum) {
        if (pieceNum == 0) {
            //Long-block
            x1 = 5;
            x2 = 5;
            x3 = 5;
            x4 = 5;
            y1 = 16;
            y2 = 15;
            y3 = 14;
            y4 = 13;
        } else if (pieceNum == 1) {
            //T-block
            x1 = 4;
            x2 = 5;
            x3 = 6;
            x4 = 5;
            y1 = 16;
            y2 = 16;
            y3 = 16;
            y4 = 15;
        } else if (pieceNum == 2) {
            //L-block
            x1 = 4;
            x2 = 4;
            x3 = 4;
            x4 = 5;
            y1 = 16;
            y2 = 15;
            y3 = 14;
            y4 = 14;
        } else if (pieceNum == 3) {
            //J-block
            x1 = 5;
            x2 = 5;
            x3 = 5;
            x4 = 4;
            y1 = 16;
            y2 = 15;
            y3 = 14;
            y4 = 14;
        } else if (pieceNum == 4) {
            //Box-block
            x1 = 4;
            x2 = 5;
            x3 = 4;
            x4 = 5;
            y1 = 16;
            y2 = 16;
            y3 = 15;
            y4 = 15;
        } else if (pieceNum == 5) {
            //Z-Block
            x1 = 5;
            x2 = 5;
            x3 = 4;
            x4 = 4;
            y1 = 16;
            y2 = 15;
            y3 = 15;
            y4 = 14;
        } else if (pieceNum == 6) {
            //S-block
            x1 = 4;
            x2 = 4;
            x3 = 5;
            x4 = 5;
            y1 = 16;
            y2 = 15;
            y3 = 15;
            y4 = 14;
        }
    }
    private void rotate(){

    }

}
