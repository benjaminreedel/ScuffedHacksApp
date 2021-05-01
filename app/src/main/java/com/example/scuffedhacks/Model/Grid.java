package com.example.scuffedhacks.Model;

import android.graphics.Point;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Grid {
    private final int gridHeight = 16;
    private final int gridWidth = 10;
    private int gridBoard[][] = new int[gridHeight][gridWidth];
    private final Random rand = new Random();
    private ArrayList<Tetrimino> pieces = new ArrayList<Tetrimino>();


    public Grid() {
        //pieces.add(new Tetrimino(rand.nextInt(7)));
    }

    public void clearGrid() {
        for (int i = 0; i < gridHeight; i++) {
            for (int j = 0; j < gridWidth; j++) {
                gridBoard[i][j] = 0;
            }
        }
    }

    public ArrayList<Tetrimino> getPieces() {
        return pieces;
    }

    public Tetrimino getCurrentPiece() {
        return pieces.get(pieces.size() - 2);
    }

    public Tetrimino getNextPiece() {
        return pieces.get(pieces.size() - 1);
    }

    private void placeTetrimino(Tetrimino currentTetrimino) {
        gridBoard[currentTetrimino.x1][currentTetrimino.y1] = 1;
        gridBoard[currentTetrimino.x2][currentTetrimino.y2] = 1;
        gridBoard[currentTetrimino.x3][currentTetrimino.y3] = 1;
        gridBoard[currentTetrimino.x4][currentTetrimino.y4] = 1;
    }

    private void deletePiece(Tetrimino currentTetrimino) {
        gridBoard[currentTetrimino.x1][currentTetrimino.y1] = 0;
        gridBoard[currentTetrimino.x2][currentTetrimino.y2] = 0;
        gridBoard[currentTetrimino.x3][currentTetrimino.y3] = 0;
        gridBoard[currentTetrimino.x4][currentTetrimino.y4] = 0;
    }

    private boolean pieceMoveCheck(Tetrimino currentPiece, int x, int y) {
        int tmp =0;
    /*
    copy piece coordinates
     */
        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        Point tmp1 = new Point(currentPiece.x1+x, currentPiece.y1+y);
        Point tmp2 = new Point(currentPiece.x2+x, currentPiece.y2+y);
        Point tmp3 = new Point(currentPiece.x3+x, currentPiece.y3+y);
        Point tmp4 = new Point(currentPiece.x4+x, currentPiece.y4+y);

        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);


        for(Point p : tmpPieceCoordinates ) {

            if(p.x < gridHeight && p.y >= 0 && p.y < gridWidth && gridBoard[p.x][p.y] == 0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        return (tmp==4);
    }


}
