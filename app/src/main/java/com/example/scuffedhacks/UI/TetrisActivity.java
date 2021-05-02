package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scuffedhacks.Model.Grid;
import com.example.scuffedhacks.Model.OptionsData;
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
    private OptionsData data;
    ImageView spots[][];
    private Timer timer = new Timer();
    private TetrisActivity tetrisActivity;
    private Random random = new Random();
    public boolean running = true;
    long tickrate;
    private int scoreTotal = 0;
    private int slideBuffer = 0;

    private ImageButton rotateButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris);
        tetrisActivity = TetrisActivity.this;
        gameGrid = new Grid();
        pieceList = gameGrid.getPieces();
        spots = new ImageView[16][10];

        data = OptionsData.getInstance();
        if (data.getDifficulty() == 1) {
            tickrate = 500;
        } else if (data.getDifficulty() == 2) {
            tickrate = 250;
        } else {
            tickrate = 100;
        }

        populateGrid();
        updateGrid();

        gameloop();

//        ImageButton btnLoop = findViewById(R.id.btnRotate);
//        btnLoop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameloop();
//
//            }
//        });

        rotateButton = findViewById(R.id.btnRotate);
        downButton = findViewById(R.id.btnDrop);
        leftButton = findViewById(R.id.btnLeft);
        rightButton = findViewById(R.id.btnRight);


        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameGrid.rotatePiece(gameGrid.getCurrentPiece());
                updateGrid();
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameGrid.moveRight(gameGrid.getCurrentPiece());
                updateGrid();
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameGrid.hardDrop(gameGrid.getCurrentPiece());
                updateGrid();
            }
        });
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameGrid.moveLeft(gameGrid.getCurrentPiece());
                updateGrid();
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
                                if (slideBuffer < 3) {
                                    slideBuffer++;
                                } else {

                                    gameGrid.placeTetrimino(gameGrid.getCurrentPiece());
                                    slideBuffer = 0;
                                    int deletedRows = gameGrid.clearRows();
//                                gameGrid.clearRows();

                                    pieceList.remove(gameGrid.getCurrentPiece());
                                    pieceList.add(new Tetrimino(random.nextInt(7)));

                                    if (deletedRows > 0) {
                                        addscore(deletedRows);
                                        if (data.scuffed) {
                                            randomGrid();
                                            updateGrid();
                                        }
                                    }

                                    if (gameGrid.checkGameOver(gameGrid.getCurrentPiece())) {
                                        timer.cancel();
                                        pieceList.clear();
                                        gameGrid.clearGrid();
                                        //                                  Intent intent = new Intent(TetrisActivity.this, GameOverScreen.class);
                                        Intent intent = new Intent(TetrisActivity.this, GameOverActivity.class);
                                        finish();
                                        startActivity(intent);
                                    } else {

                                        timer.cancel();
                                        timer = new Timer();
                                        gameloop();
                                    }
                                }

                            }

                        }
                    }
                });
            }
        }, 0, tickrate);

    }

    public void addscore(int lines) {
    int value = data.getScore();
        switch (lines) {
            case 1:
                data.setScore((int) (value + 40 / (tickrate/100))); ;
                break;
            case 2:
                data.setScore((int) (value + 100 / (tickrate/100)));
                break;
            case 3:
                data.setScore((int) (value + 300 / (tickrate/100)));
                break;
            case 4:
                data.setScore((int) (value + 1200 / (tickrate/100)));
                break;
        }
        TextView scoreText = (TextView) findViewById(R.id.txtScore);
        scoreText.setText("Score: " + data.getScore());
    }

    private void randomGrid() {
        for (int row = 10; row < 16; row++) {
            for (int col = 0; col < 10; col++) {
                int value = random.nextInt(3);
                ImageView spot = spots[row][col];
                if (value == 1) {
                    gameGrid.getGridBoard()[row][col] = 1;
                    int tmp = random.nextInt(7);
                    if (tmp == 1) {
                        spot.setImageResource(R.drawable.blue);
                    } else if (tmp == 2) {
                        spot.setImageResource(R.drawable.purple);
                    } else if (tmp == 3) {
                        spot.setImageResource(R.drawable.ska);
                    } else if (tmp == 4) {
                        spot.setImageResource(R.drawable.orange);
                    } else if (tmp == 5) {
                        spot.setImageResource(R.drawable.yellow);
                    } else if (tmp == 6) {
                        spot.setImageResource(R.drawable.red);
                    } else {
                        spot.setImageResource(R.drawable.green);
                    }
                } else {
                    gameGrid.getGridBoard()[row][col] = 0;
                    spot.setImageResource(R.drawable.filthy_square_vol_2);
                }
            }
        }
    }

    private void updateGrid() {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 10; col++) {
                if (gameGrid.getGridBoard()[row][col] > 0) {
                    ImageView spot = spots[row][col];
                    int tmp = gameGrid.getGridBoard()[row][col];
                    if (tmp == 1) {
                        spot.setImageResource(R.drawable.blue);
                    } else if (tmp == 2) {
                        spot.setImageResource(R.drawable.purple);
                    } else if (tmp == 3) {
                        spot.setImageResource(R.drawable.ska);
                    } else if (tmp == 4) {
                        spot.setImageResource(R.drawable.orange);
                    } else if (tmp == 5) {
                        spot.setImageResource(R.drawable.yellow);
                    } else if (tmp == 6) {
                        spot.setImageResource(R.drawable.red);
                    } else {
                        spot.setImageResource(R.drawable.green);
                    }

                } else {
                    ImageView spot = spots[row][col];
                    spot.setImageResource(R.drawable.filthy_square_vol_2);
                }
            }
        }
        Tetrimino current = gameGrid.getCurrentPiece();
        ImageView spot = spots[current.y1][current.x1];
        ImageView spot2 = spots[current.y2][current.x2];
        ImageView spot3 = spots[current.y3][current.x3];
        ImageView spot4 = spots[current.y4][current.x4];

        int tmp = current.color;
        if (tmp == 1) {
            spot.setImageResource(R.drawable.blue);
            spot2.setImageResource(R.drawable.blue);
            spot3.setImageResource(R.drawable.blue);
            spot4.setImageResource(R.drawable.blue);
        } else if (tmp == 2) {
            spot.setImageResource(R.drawable.purple);
            spot2.setImageResource(R.drawable.purple);
            spot3.setImageResource(R.drawable.purple);
            spot4.setImageResource(R.drawable.purple);
        } else if (tmp == 3) {
            spot.setImageResource(R.drawable.ska);
            spot2.setImageResource(R.drawable.ska);
            spot3.setImageResource(R.drawable.ska);
            spot4.setImageResource(R.drawable.ska);
        } else if (tmp == 4) {
            spot.setImageResource(R.drawable.orange);
            spot2.setImageResource(R.drawable.orange);
            spot3.setImageResource(R.drawable.orange);
            spot4.setImageResource(R.drawable.orange);
        } else if (tmp == 5) {
            spot.setImageResource(R.drawable.yellow);
            spot2.setImageResource(R.drawable.yellow);
            spot3.setImageResource(R.drawable.yellow);
            spot4.setImageResource(R.drawable.yellow);
        } else if (tmp == 6) {
            spot.setImageResource(R.drawable.red);
            spot2.setImageResource(R.drawable.red);
            spot3.setImageResource(R.drawable.red);
            spot4.setImageResource(R.drawable.red);
        } else {
            spot.setImageResource(R.drawable.green);
            spot2.setImageResource(R.drawable.green);
            spot3.setImageResource(R.drawable.green);
            spot4.setImageResource(R.drawable.green);
        }
    }

}