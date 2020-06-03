package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class high_score extends AppCompatActivity {

    int score = 0;
    Button b_add,b_end;
    TextView tv_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);


        // Lookup the recyclerview in activity layout
        b_add = findViewById(R.id.b_add);
        b_end = findViewById(R.id.button_end);
        tv_score = findViewById(R.id.tv_score);




        b_add.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                score++;
                tv_score.setText("Score: " + score);
            }
        });

        b_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SharedPreferences preferences = getSharedPreferences("PRES",0);
//
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putInt("lastScore", score);
//                editor.apply();
//
//                Intent intent = new Intent(getApplicationContext(),display_bestScore.class);
//                startActivity(intent);

            }
        });
    }


}
