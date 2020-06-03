package com.example.educational_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class display_bestScore extends AppCompatActivity {


    TextView tv_score;


    int lastScore;

    int best1,best2,best3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_best_score);
        tv_score = findViewById(R.id.tv_score_2);

        SharedPreferences preferences = getSharedPreferences("PRES",0);

        lastScore = preferences.getInt("lastScore", 0);

        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        if (lastScore > best3) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3",best3);
            editor.apply();
        }
        if (lastScore > best2) {
            SharedPreferences.Editor editor = preferences.edit();
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            editor.putInt("best2",best2);
            editor.putInt("best3",best3);
            editor.apply();
        }
        if (lastScore > best1) {
            SharedPreferences.Editor editor = preferences.edit();
            int temp = best1;
            best1 = lastScore;
            best2 = temp;
            editor.putInt("best1",best1);
            editor.putInt("best2",best2);
            editor.apply();
        }


        tv_score.setText("LAST SCORE: " + lastScore + "\n" + "BEST1: " + best1 + "\n" + "BEST2: " + best2 + "\n" + "BEST3: " + best3 + "\n");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
