package com.tanvir.dollartouch.Handler;

import android.app.Activity;

import com.tanvir.dollartouch.DataModel.SettingModel;
import com.tanvir.dollartouch.LocalDb.SettingDb;

public class Currency {
    public static String getApplicationCurrency(Activity activity){
        String cs="$";
        SettingDb settingDb=new SettingDb(activity);
        if(settingDb.getSetting()!=null){
            String currency=settingDb.getSetting().getCurrency();
            if(currency.equals("bd")){
                cs="\u09F3";
            }else if(currency.equals("uk")){
                cs="$";
            }else {
                cs="$";
            }
        }

        return cs;
    }
}
