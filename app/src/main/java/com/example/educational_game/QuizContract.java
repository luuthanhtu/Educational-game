package com.example.educational_game;

import android.provider.BaseColumns;
public final class QuizContract {
    private QuizContract() {
    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_ANSWER = "answer_nr";
        public static final String COLUMN_IMAGE_DESCRIPTION = "Images";
    }
}