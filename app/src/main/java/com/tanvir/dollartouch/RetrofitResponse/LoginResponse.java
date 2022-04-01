package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

import com.tanvir.dollartouch.DataModel.LoginModel;

public interface LoginResponse {
    void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel);
    void onError(String message, ProgressDialog progressDialog);
}
