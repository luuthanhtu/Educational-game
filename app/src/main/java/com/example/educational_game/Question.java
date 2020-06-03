package com.example.educational_game;

public class Question {
    private int QuestionID;
    private int answerTrue;
    private int imageDescription;




    public Question(int questionID, int answerTrue, int imageDescription) {
        QuestionID = questionID;

        this.answerTrue = answerTrue;

        this.imageDescription = imageDescription;
    }

    public Question() {

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
}
