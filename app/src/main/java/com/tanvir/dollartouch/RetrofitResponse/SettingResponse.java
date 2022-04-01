package com.tanvir.dollartouch.RetrofitResponse;


import com.tanvir.dollartouch.DataModel.SettingModel;

public interface SettingResponse {
    void onSuccess(String message, SettingModel settingModel);
    void onError(String message);
}
