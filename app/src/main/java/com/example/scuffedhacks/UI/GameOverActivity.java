package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.scuffedhacks.Model.OptionsData;
import com.example.scuffedhacks.R;

public class GameOverActivity extends AppCompatActivity {
    private OptionsData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        data = OptionsData.getInstance();
        TextView finalScore = findViewById(R.id.txtFinalScore);

        finalScore.setText("Final Score: "+ data.getScore());

        Button btnAgain = findViewById(R.id.btnAgain);

    btnAgain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
}