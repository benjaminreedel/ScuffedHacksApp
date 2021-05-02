package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scuffedhacks.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnOptions = findViewById(R.id.btnOptions);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this, TetrisActivity.class);
                startActivity(intent);
            }
        });

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intent);
            }
        });


    }
}