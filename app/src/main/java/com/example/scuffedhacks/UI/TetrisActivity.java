package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scuffedhacks.Model.Grid;
import com.example.scuffedhacks.Model.Tetrimino;
import com.example.scuffedhacks.Model.TetrisPlayer;
import com.example.scuffedhacks.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisActivity extends AppCompatActivity {
    private Grid gameGrid;
    private ArrayList<Tetrimino> pieceList;
    ImageView spots[][];
    private Timer timer = new Timer();
    private TetrisActivity tetrisActivity;
    private Random random = new Random();
    public boolean running = true;
    long tickrate = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris);
        tetrisActivity = TetrisActivity.this;
        gameGrid = new Grid();
        pieceList = gameGrid.getPieces();
        spots = new ImageView[16][10];

        populateGrid();
        updateGrid();

        Button btnLoop = findViewById(R.id.btnLoop);
        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameloop();
            }
        });


    }

    private void populateGrid() {
        TableLayout gameDisplay = findViewById(R.id.tableForTetris);

        for (int row = 0; row < 16; row++) {

            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            gameDisplay.addView(tableRow);

            for (int col = 0; col < 10; col++) {
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                ImageView block = new ImageView(this);

                if (gameGrid.getGridBoard()[row][col] == 1) {
                    block.setImageResource(R.drawable.red);
                } else {
                    block.setImageResource(R.drawable.filthy_square_vol_2);
                }



                block.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                block.setPadding(0,0,0,0);

                tableRow.addView(block);
                spots[row][col] = block;



            }
        }

    }

    public void gameloop() {
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                tetrisActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {

                        if(true ) {
                            gameGrid.moveDown(gameGrid.getCurrentPiece());
                            updateGrid();

                            if (gameGrid.canMoveDown(gameGrid.getCurrentPiece()) == false) {
                                int deletedRows = gameGrid.clearRows();
                                gameGrid.clearRows();
                                pieceList.remove(gameGrid.getCurrentPiece());
                                pieceList.add(new Tetrimino(random.nextInt(7) + 1));

                                if (deletedRows> 0) {
                                    addscore(deletedRows);
                                }

                                if(true) {
                                    timer.cancel();
                                    timer = new Timer();
                                    gameloop();
                                }
                            }

                        }
                    }
                });
            }
        }, 0, tickrate);

    }

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

    private void updateGrid() {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 10; col++) {
                if (gameGrid.getGridBoard()[row][col] == 1) {
                    ImageView spot = spots[row][col];
                    spot.setImageResource(R.drawable.red);
                } else {
                    ImageView spot = spots[row][col];
                    spot.setImageResource(R.drawable.filthy_square_vol_2);
                }
            }
        }
        Tetrimino current = gameGrid.getCurrentPiece();

        ImageView spot = spots[current.y1][current.x1];
        spot.setImageResource(R.drawable.red);
        spot = spots[current.y2][current.x2];
        spot.setImageResource(R.drawable.red);
        spot = spots[current.y3][current.x3];
        spot.setImageResource(R.drawable.red);
        spot = spots[current.y4][current.x4];
        spot.setImageResource(R.drawable.red);


    }

}