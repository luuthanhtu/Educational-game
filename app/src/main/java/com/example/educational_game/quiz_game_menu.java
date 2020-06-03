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

import java.util.List;

public class quiz_game_menu extends AppCompatActivity implements View.OnClickListener {

    private Button BtnTrue;
    private Button BtnFalse;
    private TextView question_index;
    private TextView QuestionTag;
    private ImageButton nextBtn;
    private int CurrentQuestionIndex = 0;
    private ImageView imgDescrip;
    private int CurrentPointIndex = 0;
    private TextView PlayerPoint;
    private Question[] questions_Array = new Question[] {
            new Question(R.string.Question_1, 1, R.drawable.cyclone),
            new Question(R.string.Question_2, 0, R.drawable.goldfish),
            new Question(R.string.Question_3, 0, R.drawable.water),
            new Question(R.string.Question_4, 1, R.drawable.brazil),
            new Question(R.string.Question_5, 0, R.drawable.tunnel),
            new Question(R.string.Question_6, 0, R.drawable.electrons),
            new Question(R.string.Question_7, 0, R.drawable.ocen),
            new Question(R.string.Question_8, 1, R.drawable.chemicals),
            new Question(R.string.Question_9, 0, R.drawable.shark),
            new Question(R.string.Question_10, 0, R.drawable.body),
            new Question(R.string.Question_11, 1, R.drawable.atom),
            new Question(R.string.Question_12, 1, R.drawable.filtration),
            new Question(R.string.Question_13, 1, R.drawable.conductors),
            new Question(R.string.Question_14, 1, R.drawable.molecules)
    };

    private List<Question> questionList;

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
        BtnTrue.setOnClickListener(this);
        BtnFalse.setOnClickListener(this);
        nextBtn.setOnClickListener(this);


        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.true_answer:
                checkAnswer(1);
                break;
            case R.id.false_answer:
                checkAnswer(0);
                break;
            case R.id.nextBtn:
                CurrentQuestionIndex = (CurrentQuestionIndex + 1) % questions_Array.length;
//                Log.d("Current", "onClick " + CurrentQuestionIndex);
                QuestionTag.setText(questions_Array[CurrentQuestionIndex].getQuestionID());
                imgDescrip.setImageResource(questions_Array[CurrentQuestionIndex].getImageDescription());
                String x = Integer.toString(CurrentQuestionIndex);
                question_index.setText(x + "/10");
        }

    }

    private void checkAnswer(int userChoose) {
        int answerTrue = questions_Array[CurrentQuestionIndex].getAnserTrue();

        String correct_state;
        CurrentPointIndex = CurrentQuestionIndex + 1;

        if (userChoose == answerTrue) {
            correct_state = "Correct";
            String s = Integer.toString(CurrentPointIndex);
            PlayerPoint.setText("Your score: " + s);

                SharedPreferences preferences = getSharedPreferences("PRES",0);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", CurrentPointIndex);
                editor.apply();
        }
        else {
            correct_state = "Incorrect";
        }
        Toast.makeText(quiz_game_menu.this,correct_state,Toast.LENGTH_SHORT).show();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        PlayerPoint.setText(0);
//    }
}
