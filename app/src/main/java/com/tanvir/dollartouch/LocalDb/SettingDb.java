package com.tanvir.dollartouch.LocalDb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.tanvir.dollartouch.DataModel.SettingModel;

public class SettingDb {
    private Activity activity;
    public SettingDb(Activity activity) {
        this.activity = activity;
    }

    public void setSetting(SettingModel setting) {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("settingDb", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

         Gson gson = new Gson();
        String json = gson.toJson(setting);
        editor.putString("setting", json);

        editor.commit();
    }




    public SettingModel getSetting(){
        SettingModel setting=null;
        SharedPreferences sharedPreferences=activity.getSharedPreferences("settingDb", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sharedPreferences.getString("setting","");
         setting = gson.fromJson(json, SettingModel.class);
        return  setting;
    }



}
