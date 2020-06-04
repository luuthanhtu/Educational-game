package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Start_screen extends AppCompatActivity {

    Button b_play;
    Button b_login;
    EditText editText;
    public String name;
    public static final String EXTRA_NAME = "extraName";

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        b_play = findViewById(R.id.play_button);
        b_login = findViewById(R.id.Login_fac);
        editText = findViewById(R.id.entername);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.start_screen);



        System.out.println(name);
        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editText.getText().toString();
                Intent intent = new Intent(Start_screen.this, MainActivity.class);
                intent.putExtra("UserName", name);
                mp.start();
                startActivity(intent);

            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_screen.this, LoginActivity.class);
                mp.start();
                startActivity(intent);
            }
        });





        Category c1 = new Category("CHEMICAL");
        QuizDbHelper.getInstance(this).addCategory(c1);

    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//        editText.setText(name);
//
//    }
}
