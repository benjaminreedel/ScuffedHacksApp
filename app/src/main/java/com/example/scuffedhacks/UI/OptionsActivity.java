package com.example.scuffedhacks.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scuffedhacks.Model.OptionsData;
import com.example.scuffedhacks.R;

public class OptionsActivity extends AppCompatActivity {

    private OptionsData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        data = OptionsData.getInstance();
        createRadioButtons();

        Switch switch1 = findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    data.scuffed = true;
                } else {
                    data.scuffed = false;
                }
            }
        });

    }

    private void createRadioButtons() {
        RadioGroup group = findViewById(R.id.radioGroupDifficulty);

        RadioButton btnEasy = new RadioButton(this);
        btnEasy.setText("Beginner");
//        btnEasy.setTextColor(Color.parseColor("#FFFFFF"));

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    data.setDifficulty(1);
            }
        });

        group.addView(btnEasy);

        if (data.getDifficulty() == 1) {
            btnEasy.setChecked(true);
        }

        RadioButton btnMed = new RadioButton(this);
        btnMed.setText("Intermediate");
//        btnMed.setTextColor(Color.parseColor("#FFFFFF"));

        btnMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setDifficulty(2);
            }
        });

        group.addView(btnMed);

        if (data.getDifficulty() == 2) {
            btnMed.setChecked(true);
        }

        RadioButton btnHard = new RadioButton(this);
        btnHard.setText("Master");
//        btnHard.setTextColor(Color.parseColor("#FFFFFF"));

        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setDifficulty(3);
            }
        });

        group.addView(btnHard);

        if (data.getDifficulty() == 3) {
            btnHard.setChecked(true);
        }


    }


}