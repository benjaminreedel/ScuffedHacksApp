package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

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
import com.example.scuffedhacks.Model.Tetrimino;
import com.example.scuffedhacks.Model.TetrisPlayer;
import com.example.scuffedhacks.R;

public class TetrisActivity extends AppCompatActivity {
    private Grid gameGrid;

    ImageView spots[][];
    public boolean running = true;
    long tickrate = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tetris);

        gameGrid = new Grid();
        spots = new ImageView[16][10];

        populateGrid();
        updateGrid();

        ImageButton btnLoop = findViewById(R.id.btnRotate);
        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameGrid.moveDown(gameGrid.getCurrentPiece());
                updateGrid();

//                try {
//                    gameloop();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        });




//        try {
//            gameloop();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

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

    public void gameloop() throws InterruptedException {
        Log.d("error","banana");
        for (int i = 0; i < 4; i++) {
            //Thread.sleep(tickrate);
            gameGrid.moveDown(gameGrid.getCurrentPiece());
            updateGrid();
        }
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