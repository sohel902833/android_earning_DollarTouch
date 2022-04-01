package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

public interface RetrofitResponse {
    void onSuccess(String message, ProgressDialog progressDialog);
    void onError(String message, ProgressDialog progressDialog);
}
