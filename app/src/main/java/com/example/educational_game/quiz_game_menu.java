package com.example.educational_game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class quiz_game_menu extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private ColorStateList textColorDefaultCd;

    private Button BtnTrue;
    private Button BtnFalse;
    private TextView question_index;
    private TextView QuestionTag;
    private Button nextBtn;
    private ImageView imgDescrip;
    private TextView textViewDifficulty;
    private TextView PlayerPoint;
    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score;
    private boolean answered;
    private TextView tv_countdown;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    private long backPressedTime;
    private TextView textViewCategory;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_game_menu);



        BtnTrue = findViewById(R.id.true_answer);
        BtnFalse = findViewById(R.id.false_answer);
        QuestionTag = findViewById(R.id.game_question);
        nextBtn = findViewById(R.id.nextBtn);
        PlayerPoint = findViewById(R.id.score);
        imgDescrip = findViewById(R.id.img_question);
        question_index = findViewById(R.id.currentqus);
        tv_countdown = findViewById(R.id.count_time);
        textViewDifficulty = findViewById(R.id.text_view_difficulty);
        textViewCategory = findViewById(R.id.text_view_category);

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(SettingActivity.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(SettingActivity.EXTRA_CATEGORY_NAME);
        String difficulty = intent.getStringExtra(SettingActivity.EXTRA_DIFFICULTY);
        textViewDifficulty.setText("Difficulty: " + difficulty);
        textViewCategory.setText("Catergory:" + categoryName);
        textColorDefaultCd = tv_countdown.getTextColors();

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(categoryID,difficulty);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if (questionList == null) {
                finish();
            }
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                if (questionCounter < questionCountTotal) {
                    nextBtn.setText("Next");
                } else {
                    nextBtn.setText("Finish");
                }
            }
        }


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (BtnTrue.isPressed()) {
                        checkAnswer(1);
                    }
                    else if (BtnFalse.isPressed()) {
                        checkAnswer(0);
                    }
                    else {
                        Toast.makeText(quiz_game_menu.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

        BtnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        BtnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

    }




    private void showNextQuestion() {

        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            QuestionTag.setText(currentQuestion.getQuestionID());
            imgDescrip.setImageResource(currentQuestion.getImageDescription());
            questionCounter++;
            question_index.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            nextBtn.setText("Confirm");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                if (BtnTrue.isPressed()) {
                    checkAnswer(1);
                }
                else if (BtnFalse.isPressed()) {
                    checkAnswer(0);
                }
                else {
                    Toast.makeText(quiz_game_menu.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
                score--;
                PlayerPoint.setText("Score: " + score);
            }
        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tv_countdown.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            tv_countdown.setTextColor(Color.RED);
        } else {
            tv_countdown.setTextColor(textColorDefaultCd);
        }
    }


    private void checkAnswer(int userChoose) {
        answered = true;

        countDownTimer.cancel();


        if (userChoose == currentQuestion.getAnserTrue()) {
            Toast.makeText(quiz_game_menu.this, "Correct", Toast.LENGTH_SHORT).show();
            score++;
            PlayerPoint.setText("Score: " + score);


        }
        else {
            Toast.makeText(quiz_game_menu.this, "Incorrect", Toast.LENGTH_SHORT).show();
            score--;
            PlayerPoint.setText("Score: " + score);
        }

        if (questionCounter < questionCountTotal) {
            nextBtn.setText("Next");
        } else {
            nextBtn.setText("Finish");
        }

        SharedPreferences preferences = getSharedPreferences("PRES",0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("lastScore", score);
        editor.apply();
    }

    private void finishQuiz() {
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 1000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}
