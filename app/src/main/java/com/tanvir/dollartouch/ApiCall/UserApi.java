package com.tanvir.dollartouch.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;


import com.tanvir.dollartouch.Api.UserRetrofitApi;
import com.tanvir.dollartouch.DataModel.LoginModel;
import com.tanvir.dollartouch.DataModel.QuestionListModel;
import com.tanvir.dollartouch.DataModel.RetrofitModel;
import com.tanvir.dollartouch.DataModel.SettingModel;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.LocalDb.SettingDb;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.LoginResponse;
import com.tanvir.dollartouch.RetrofitResponse.QuestionListResponse;
import com.tanvir.dollartouch.RetrofitResponse.RetrofitResponse;
import com.tanvir.dollartouch.RetrofitResponse.SettingResponse;
import com.tanvir.dollartouch.RetrofitResponse.UserResponse;
import com.tanvir.dollartouch.RetrofitResponse.WitdrawListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApi {

    UserRetrofitApi userRetrofitApi;
    UserDb userDb;
    SettingDb settingDb;
    private Activity context;
    public UserApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);
        settingDb=new SettingDb(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofitApi = retrofit.create(UserRetrofitApi.class);

    }
    public void loginUser(Map<String, Object> userMap, ProgressDialog progressDialog, LoginResponse retrofitResponses) {
        progressDialog.show();
        Call<LoginModel> call=userRetrofitApi.loginUser(userMap);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setToken(customResponse.getToken());
                            String message=customResponse==null?"Login Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog,customResponse);
                        }catch (Exception e){
                            String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Login Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                retrofitResponses.onError("Login Failed",progressDialog);

            }
        });
    }
    public void registerUser(Map<String, Object> userMap, ProgressDialog progressDialog, LoginResponse retrofitResponses) {
        progressDialog.show();
        Call<LoginModel> call=userRetrofitApi.registerUser(userMap);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                LoginModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setToken(customResponse.getToken());
                            String message=customResponse==null?"Register Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog,customResponse);
                        }catch (Exception e){
                            String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Register Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                retrofitResponses.onError("Register Failed",progressDialog);

            }
        });
    }
    public void getCurrentUser(ProgressDialog progressDialog, UserResponse retrofitResponses) {
        progressDialog.show();
        Call<User> call=userRetrofitApi.getCurrentUser(userDb.getToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User currentUser=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            userDb.setUserData(currentUser);
                            retrofitResponses.onSuccess("Success",progressDialog,currentUser);
                        }catch (Exception e){
                            retrofitResponses.onError("User Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("User Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("User Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                retrofitResponses.onError("User Not Found",progressDialog);

            }
        });
    }
    public void sentWithdrawRequest(Map<String, Object> withdrawMap, ProgressDialog progressDialog, RetrofitResponse retrofitResponses) {
        progressDialog.show();
        Call<RetrofitModel> call=userRetrofitApi.sentWithdrawRequest(userDb.getToken(),withdrawMap);
        call.enqueue(new Callback<RetrofitModel>() {
            @Override
            public void onResponse(Call<RetrofitModel> call, Response<RetrofitModel> response) {
                RetrofitModel customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            String message=customResponse==null?"Withdraw Request Sent Successful.":customResponse.getMessage();
                            retrofitResponses.onSuccess(message,progressDialog);
                        }catch (Exception e){
                            String message=customResponse==null?"Withdraw Request Sent Failed.":customResponse.getMessage();
                            retrofitResponses.onError(message,progressDialog);
                        }
                    }else{
                        String message=customResponse==null?"Withdraw Request Sent Failed.":customResponse.getMessage();
                        retrofitResponses.onError(message,progressDialog);
                    }
                }else{
                    String message=customResponse==null?"Withdraw Request Sent Failed.":customResponse.getMessage();
                    retrofitResponses.onError(message,progressDialog);
                }
            }
            @Override
            public void onFailure(Call<RetrofitModel> call, Throwable t) {
                retrofitResponses.onError("Withdraw Request Sent Failed.",progressDialog);

            }
        });
    }
    public void getWithdrawList(ProgressDialog progressDialog, WitdrawListResponse retrofitResponses) {
        progressDialog.show();
        Call<WithdrawList> call=userRetrofitApi.getWithdrawList(userDb.getToken());
        call.enqueue(new Callback<WithdrawList>() {
            @Override
            public void onResponse(Call<WithdrawList> call, Response<WithdrawList> response) {
                WithdrawList customResponse=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            retrofitResponses.onSuccess("Success",progressDialog,customResponse);
                        }catch (Exception e){
                            retrofitResponses.onError("Not Found",progressDialog);
                        }
                    }else{
                        retrofitResponses.onError("Not Found",progressDialog);
                    }
                }else{
                    retrofitResponses.onError("Not Found",progressDialog);
                }
            }
            @Override
            public void onFailure(Call<WithdrawList> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }
    public void getApplicationSetting(SettingResponse settingResponse) {
        Call<SettingModel> call=userRetrofitApi.getApplicationSetting(userDb.getToken());
        call.enqueue(new Callback<SettingModel>() {
            @Override
            public void onResponse(Call<SettingModel> call, Response<SettingModel> response) {
                SettingModel settingModel=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            settingResponse.onSuccess("Success",settingModel);
                            settingDb.setSetting(settingModel);
                        }catch (Exception e){
                            settingResponse.onError("Setting Not Found");
                        }
                    }else{
                        settingResponse.onError("Setting Not Found");
                    }
                }else{
                    settingResponse.onError("Setting Not Found");
                }
            }
            @Override
            public void onFailure(Call<SettingModel> call, Throwable t) {
                settingResponse.onError("Setting Not Found");

            }
        });
    }
    public void getQuestions(String categoryId,QuestionListResponse questionListResponse) {
       Call<QuestionListModel> call=userRetrofitApi.getQuestion(userDb.getToken(),categoryId);
        call.enqueue(new Callback<QuestionListModel>() {
            @Override
            public void onResponse(Call<QuestionListModel> call, Response<QuestionListModel> response) {
                QuestionListModel questions=response.body();
                if(response.isSuccessful()){
                    if(response.code()==201){
                        try{
                            questionListResponse.onSuccess("Success",questions);

                        }catch (Exception e){
                            questionListResponse.onError("Question Not Found");
                        }
                    }else{
                        questionListResponse.onError("Question Not Found");
                    }
                }else{
                    questionListResponse.onError("Question Not Found");
                }
            }
            @Override
            public void onFailure(Call<QuestionListModel> call, Throwable t) {
                questionListResponse.onError("Question Not Found");

            }
        });
    }


}
