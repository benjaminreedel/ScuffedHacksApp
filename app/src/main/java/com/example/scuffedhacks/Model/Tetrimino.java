package com.example.scuffedhacks.Model;

public class Tetrimino {
    private void pieceGenerator(int pieceNum) {
        int x1,x2,x3,x4,y1,y2,y3,y4,y;
        if(pieceNum == 0) {
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
}
