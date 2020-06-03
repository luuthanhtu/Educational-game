package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start_screen extends AppCompatActivity {

    Button b_play;
    Button b_login;
    Button b_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        b_play = findViewById(R.id.play_button);
        b_login = findViewById(R.id.login);
        b_register = findViewById(R.id.register);

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start_screen.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
