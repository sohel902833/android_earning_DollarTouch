package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

import com.tanvir.dollartouch.DataModel.LoginModel;
import com.tanvir.dollartouch.DataModel.QuestionListModel;

public interface QuestionListResponse {
    void onSuccess(String message, QuestionListModel questions);
    void onError(String message);
}
