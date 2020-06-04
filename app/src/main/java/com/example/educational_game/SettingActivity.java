package com.example.educational_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

public class SettingActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String EXTRA_DIFFICULTY = "extraDifficulty";

    private Spinner spinnerDifficulty;
    private Spinner spinnerCategory;
    ImageButton b_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);

        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        b_back = findViewById(R.id.b_back);

        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
                int categoryID = selectedCategory.getId();
                String categoryName = selectedCategory.getName();
                String difficulty = spinnerDifficulty.getSelectedItem().toString();

                SharedPreferences preferences_1 = getSharedPreferences("PRES_1",0);
                SharedPreferences.Editor editor1 = preferences_1.edit();
                editor1.putInt("categoryID", categoryID);
                editor1.apply();

                SharedPreferences preferences_2 = getSharedPreferences("PRES_2",0);
                SharedPreferences.Editor editor2 = preferences_2.edit();
                editor2.putString("categoryName", categoryName);
                editor2.apply();

                SharedPreferences preferences_3 = getSharedPreferences("PRES_3",0);
                SharedPreferences.Editor editor3 = preferences_3.edit();
                editor3.putString("difficulty", difficulty);
                editor3.apply();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
                intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
                intent.putExtra(EXTRA_DIFFICULTY,difficulty);
                startActivity(intent);
            }
        });

        loadCategories();
        loadDifficultyLevels();



    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category> categories = dbHelper.getAllCategories();
        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }
    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();
        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

//    public void onBackPressed() {
//        super.onBackPressed();
//
//
//    }

}
