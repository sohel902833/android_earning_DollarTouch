package com.tanvir.dollartouch.DataModel;

import java.util.List;

public class QuestionListModel {
    List<QuestionModel> questions=null;
    public  QuestionListModel(){}
    public QuestionListModel(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }
}
