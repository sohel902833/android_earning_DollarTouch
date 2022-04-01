package com.tanvir.dollartouch.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tanvir.dollartouch.DataModel.SettingModel;

public class QuestionDb {
    private Activity activity;
    public QuestionDb(Activity activity) {
        this.activity = activity;
    }

    public void setCurrentState(int state,String dbName) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("Category"+dbName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("currentState", state);
        editor.commit();
    }
    public int getCurrentState(String dbName){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("Category"+dbName, Context.MODE_PRIVATE);
        int state = sharedPreferences.getInt("currentState",0);
         return  state;
    }
}
