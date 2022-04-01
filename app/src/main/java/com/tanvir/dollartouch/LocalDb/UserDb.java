package com.tanvir.dollartouch.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.LoginActivity;

public class UserDb {
    private Activity activity;
    public UserDb(Activity activity) {
        this.activity = activity;
    }
    public void setUserData(User user) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

         Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user", json);

        editor.commit();
    }

    public void setToken(String token){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public User getUserData(){
        User user=null;
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("user","");
         user = gson.fromJson(json, User.class);
        return  user;
    }
    public String getToken(){
        String token="";
        SharedPreferences sharedPreferences=activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token","");
        return token;
    }

    public void removeUserData(){
        SharedPreferences userShared = activity.getSharedPreferences("userDb", Context.MODE_PRIVATE);
        setToken("");
        userShared.edit().clear().apply();
    }
    public void logoutUser(){
        removeUserData();
        Intent intent=new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

}
