package com.example.scuffedhacks.Model;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grid {
    private final int gridHeight = 16;
    private final int gridWidth = 10;
    private int gridBoard[][] = new int[gridHeight][gridWidth];
    private final Random rand = new Random();
    private ArrayList<Tetrimino> pieces = new ArrayList<Tetrimino>();


    public Grid() {
        clearGrid();
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

    public int[][] getGridBoard() {
        return gridBoard;
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

        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        // Create a copy of the current piece with the move applied
        Point tmp1 = new Point(currentPiece.x1+x, currentPiece.y1+y);
        Point tmp2 = new Point(currentPiece.x2+x, currentPiece.y2+y);
        Point tmp3 = new Point(currentPiece.x3+x, currentPiece.y3+y);
        Point tmp4 = new Point(currentPiece.x4+x, currentPiece.y4+y);

        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);

        // Does this copied piece stay within the boundaries
        for(Point p : tmpPieceCoordinates ) {

            if(p.x < gridHeight && p.y >= 0 && p.y < gridWidth && gridBoard[p.x][p.y] == 0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        return (tmp == 4);
    }

    private boolean pieceRotateCheck(Tetrimino currentPiece) {
        int tmp =0;
        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        Tetrimino tmpStein = new Tetrimino(currentPiece);

        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        tmpStein.rotate();

        Point tmp1 = new Point(tmpStein.x1, tmpStein.y1);
        Point tmp2 = new Point(tmpStein.x2, tmpStein.y2);
        Point tmp3 = new Point(tmpStein.x3, tmpStein.y3);
        Point tmp4 = new Point(tmpStein.x4, tmpStein.y4);

        tmpPieceCoordinates .add(tmp1);
        tmpPieceCoordinates .add(tmp2);
        tmpPieceCoordinates .add(tmp3);
        tmpPieceCoordinates .add(tmp4);

        for(Point p : tmpPieceCoordinates  ) {

            if(p.x < gridHeight && p.x >= 0 && p.y >= 0 && p.y < gridWidth && gridBoard[p.x][p.y] == 0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        return (tmp == 4);
    }

    private  boolean canMoveLeft(Tetrimino currentPiece) {
        if(pieceMoveCheck(currentPiece, 0, -1) == true) {
            return true;
        }
        return false;
    }

    private boolean canMoveRight(Tetrimino currentPiece){
        if(pieceMoveCheck(currentPiece, 0,1) == true) {
            return true;
        }
        return false;
    }

    public boolean canMoveDown(Tetrimino currentPiece) {
        if(pieceMoveCheck(currentPiece, 1,0) == true) {
            return true;
        }
        return false;
    }


    private void movePiece(Tetrimino currentPiece, int x, int y)   {
        deletePiece(currentPiece);
        currentPiece.move(x, y);
        placeTetrimino(currentPiece);
    }

    public void moveRight(Tetrimino currentPiece){
        if(canMoveRight(currentPiece)==true) {
            movePiece(currentPiece, 0, 1);
        }
    }

    public  void moveLeft(Tetrimino currentPiece){
        if(canMoveLeft(currentPiece)==true) {
            movePiece(currentPiece, 0, -1);
        }
    }

    public  void moveDown(Tetrimino currentPiece) {
        if(canMoveDown(currentPiece)==true) {
            movePiece(currentPiece, 1, 0);
        }
    }

    public void rotatePiece(Tetrimino currentPiece) {

        if(pieceRotateCheck(currentPiece)==true) {
            deletePiece(currentPiece);
            currentPiece.rotate();
            placeTetrimino(currentPiece);
        }
        placeTetrimino(currentPiece);
    }

    public int clearRows() {

        int deletedRowIndex;
        int deletedRows=0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; i < gridHeight; i++) {
            for (int j = gridWidth - 1; j >= 0; j--) {

                if (gridBoard[i][j]==0) { // Row not full
                    break;
                }
                if (j == 0) {
                    deletedRowIndex = i;
                    arrayList.add(deletedRowIndex);
                    deletedRows++;
                    deleteRow(deletedRowIndex);
                }
            }
        }

        if (deletedRows >= 1) {
            int highestRow = Collections.min(arrayList); // highest Row which can be cleared
            int[][] gameBoardCopy = new int[highestRow][gridWidth];

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoardCopy[i][j] = gridBoard[i][j];
                }
            }

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gridBoard[i+deletedRows][j] = gameBoardCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public void deleteRow(int r){
        for (int i = 0; i < gridWidth; i++) {
            gridBoard[r][i] =0;
        }
    }

    private int getMinXCoordinate(Tetrimino piece) {
     return Math.min(Math.min(piece.x1, piece.x2), Math.min(piece.x3, piece.x4));
    }

    public boolean checkGameOver(Tetrimino spielStein) {
        if(canMoveDown(spielStein) == false && getMinXCoordinate(spielStein)<=1) {
            return true;
        }
        return false;
    }

    public  int getBoardHeight() {
        return this.gridHeight;
    }

    public int getBoardWidth() {
        return this.gridWidth;
    }

}
