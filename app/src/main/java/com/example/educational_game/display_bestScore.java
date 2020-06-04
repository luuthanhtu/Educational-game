package com.example.educational_game;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.InputStream;



public class display_bestScore extends AppCompatActivity {

    private ShareDialog shareDialog;
    TextView tv_score;


    int lastScore;

    int best1,best2,best3;
    Intent intent = getIntent();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_best_score);
        tv_score = findViewById(R.id.tv_score_2);
        FacebookSdk.sdkInitialize(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences("PRES",0);

        lastScore = preferences.getInt("lastScore", 0);
        Button logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent login = new Intent(display_bestScore.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

         Bundle inBundle = getIntent().getExtras();
         String name = inBundle.get("name").toString();
         String surname = inBundle.get("surname").toString();
         String imageUrl = inBundle.get("imageUrl").toString();

        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);
        TextView nameView = (TextView)findViewById(R.id.nameAndSurname);
        nameView.setText("" + name + " " + surname);


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


        tv_score.setText("LAST SCORE: " + lastScore + "\n" + "BEST1: "
                + best1 + "\n" + "BEST2: " + best2 + "\n" + "BEST3: " + best3 + "\n");

        new DownloadImage((ImageView)findViewById(R.id.profileImage)).execute(imageUrl);

        shareDialog = new ShareDialog(this);
        ShareLinkContent content = new ShareLinkContent.Builder().build();
        shareDialog.show(content);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
