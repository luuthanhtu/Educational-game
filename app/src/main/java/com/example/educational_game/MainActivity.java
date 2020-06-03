package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton b_quiz_1;
    ImageButton b_quiz_2;
    ImageButton b_leaderBoard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_quiz_1 = findViewById(R.id.quizgame);
        b_quiz_2 = findViewById(R.id.quizgame_2);
        b_leaderBoard = findViewById(R.id.leaderBoard);

        b_quiz_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Intent intent = new Intent(getApplicationContext(),quiz_game_menu.class);
                startActivity(intent);

            }
        });

        b_leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Intent intent = new Intent(getApplicationContext(),display_bestScore.class);
                startActivity(intent);

            }
        });
    }

}
