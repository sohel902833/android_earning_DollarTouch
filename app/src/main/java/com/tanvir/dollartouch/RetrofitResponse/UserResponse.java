package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

import com.tanvir.dollartouch.DataModel.User;


public interface UserResponse {
    void onSuccess(String message, ProgressDialog progressDialog, User user);
    void onError(String message, ProgressDialog progressDialog);
}
