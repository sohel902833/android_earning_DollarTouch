package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.WithdrawList;

public interface WitdrawListResponse {
    void onSuccess(String message, ProgressDialog progressDialog, WithdrawList withdrawList);
    void onError(String message, ProgressDialog progressDialog);
}
