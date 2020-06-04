package com.example.educational_game;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";

    private int id;
    private int QuestionID;
    private int answerTrue;
    private int imageDescription;
    private String difficulty;
    private int categoryID;

    public Question() {

    }

    public Question(int questionID, int answerTrue, int imageDescription,String difficulty,int categoryID) {
        QuestionID = questionID;

        this.answerTrue = answerTrue;

        this.imageDescription = imageDescription;

        this.difficulty = difficulty;

        this.categoryID = categoryID;


    }




    protected Question(Parcel in) {
        id = in.readInt();
        QuestionID = in.readInt();
        answerTrue = in.readInt();
        imageDescription = in.readInt();
        difficulty = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(QuestionID);
        dest.writeInt(answerTrue);
        dest.writeInt(imageDescription);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public int getAnserTrue() {
        return answerTrue;
    }

    public void setQuestion(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public void setAnserTrue(int answerTrue) {
        this.answerTrue = answerTrue;
    }

    public int getImageDescription() {
        return imageDescription;
    }

    public void  setImageDescription(int imageDescription) {
        this.imageDescription = imageDescription;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public static String[] getAllDifficultyLevels() {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }

}
