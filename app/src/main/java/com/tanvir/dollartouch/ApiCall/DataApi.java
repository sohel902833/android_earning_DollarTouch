package com.tanvir.dollartouch.ApiCall;

import android.app.Activity;
import android.app.ProgressDialog;

import com.tanvir.dollartouch.Api.UserRetrofitApi;
import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.LoginModel;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.LocalDb.SettingDb;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.LoginResponse;
import com.tanvir.dollartouch.RetrofitResponse.QuizCategoryResponse;
import com.tanvir.dollartouch.RetrofitResponse.UserResponse;
import com.tanvir.dollartouch.RetrofitResponse.WitdrawListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataApi {

    UserRetrofitApi userRetrofitApi;
    UserDb userDb;
    SettingDb settingDb;
    private Activity context;
    public DataApi(Activity context) {
        this.context = context;

        userDb = new UserDb(context);
        settingDb=new SettingDb(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        userRetrofitApi = retrofit.create(UserRetrofitApi.class);

    }
    public void getCategoryList(ProgressDialog progressDialog, QuizCategoryResponse retrofitResponses) {
        progressDialog.show();
        Call<CategoryList> call=userRetrofitApi.getQuizCategoryList(userDb.getToken());
        call.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                CategoryList customResponse=response.body();
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
            public void onFailure(Call<CategoryList> call, Throwable t) {
                retrofitResponses.onError("Not Found",progressDialog);

            }
        });
    }

}
