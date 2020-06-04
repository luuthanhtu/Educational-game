package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton b_quiz_1;
    TextView nameview;
    ImageButton b_leaderBoard;
    ImageButton b_setting;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    public int categoryID;
    public String categoryName;
    public String difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_quiz_1 = findViewById(R.id.quizgame);
        nameview = findViewById(R.id.nameview);
        b_leaderBoard = findViewById(R.id.leaderBoard);
        b_setting = findViewById(R.id.setting);
        final MediaPlayer mp1 = MediaPlayer.create(this, R.raw.quiz_game);
        final MediaPlayer mp2 = MediaPlayer.create(this, R.raw.leaderboard);


        Intent intent = getIntent();
        categoryID = intent.getIntExtra(SettingActivity.EXTRA_CATEGORY_ID, 0);
        categoryName = intent.getStringExtra(SettingActivity.EXTRA_CATEGORY_NAME);
        difficulty = intent.getStringExtra(SettingActivity.EXTRA_DIFFICULTY);

        String name = intent.getStringExtra("UserName");
        nameview.setText("Welcome " + name + " to the educational game!!!");

        b_quiz_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp1.start();
                Intent intent = new Intent(getApplicationContext(),quiz_game_menu.class);
                intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
                intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
                intent.putExtra(EXTRA_DIFFICULTY,difficulty);
                startActivity(intent);


            }
        });

        b_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);

            }
        });

        b_leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp2.start();
                Intent intent = new Intent(getApplicationContext(),display_bestScore.class);
                startActivity(intent);

            }
        });
    }

}
