package com.example.educational_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.educational_game.QuizContract.*;
import java.util.ArrayList;
import java.util.List;
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quizbank.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db = this.getWritableDatabase();
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER + " INTEGER, " +
                QuestionsTable.COLUMN_IMAGE_DESCRIPTION + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question(R.string.Question_1, 1, R.drawable.cyclone);
        Question q2 = new Question(R.string.Question_2, 0, R.drawable.goldfish);
        Question q3 = new Question(R.string.Question_3, 0, R.drawable.water);
        Question q4 = new Question(R.string.Question_4, 1, R.drawable.brazil);
        Question q5 = new Question(R.string.Question_5, 0, R.drawable.tunnel);
        Question q6 = new Question(R.string.Question_6, 0, R.drawable.electrons);
        Question q7 = new Question(R.string.Question_7, 0, R.drawable.ocen);
        Question q8 = new Question(R.string.Question_8, 1, R.drawable.goldfish);
        Question q9 = new Question(R.string.Question_9, 0, R.drawable.goldfish);
        Question q10 = new Question(R.string.Question_10, 0, R.drawable.goldfish);
        Question q11 = new Question(R.string.Question_11, 1, R.drawable.goldfish);
        Question q12 = new Question(R.string.Question_12, 1, R.drawable.goldfish);
        Question q13 = new Question(R.string.Question_13, 1, R.drawable.goldfish);
        Question q14 = new Question(R.string.Question_14, 1, R.drawable.goldfish);
        addQuestion(q1);
        addQuestion(q2);
        addQuestion(q3);
        addQuestion(q4);
        addQuestion(q5);
        addQuestion(q6);
        addQuestion(q7);
        addQuestion(q8);
        addQuestion(q9);
        addQuestion(q10);
        addQuestion(q11);
        addQuestion(q12);
        addQuestion(q13);
        addQuestion(q14);

    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestionID());
        cv.put(QuestionsTable.COLUMN_ANSWER, question.getAnserTrue());
        cv.put(QuestionsTable.COLUMN_IMAGE_DESCRIPTION, question.getImageDescription());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setAnserTrue(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                question.setImageDescription(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_IMAGE_DESCRIPTION)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}