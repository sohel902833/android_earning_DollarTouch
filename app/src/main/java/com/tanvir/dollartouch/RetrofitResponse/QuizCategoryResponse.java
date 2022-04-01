package com.tanvir.dollartouch.RetrofitResponse;

import android.app.ProgressDialog;

import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.User;

public interface QuizCategoryResponse {
    void onSuccess(String message, ProgressDialog progressDialog, CategoryList categoryList);
    void onError(String message, ProgressDialog progressDialog);
}
