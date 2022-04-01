package com.tanvir.dollartouch.Api;

import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.LoginModel;
import com.tanvir.dollartouch.DataModel.QuestionListModel;
import com.tanvir.dollartouch.DataModel.RetrofitModel;
import com.tanvir.dollartouch.DataModel.SettingModel;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.DataModel.WithdrawList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface UserRetrofitApi {
   @GET("user/")
    Call<User> getCurrentUser(@Header("Authorization") String token);
   @GET("user/quiz-category")
    Call<CategoryList> getQuizCategoryList(@Header("Authorization") String token);
   @GET("user/withdraw")
    Call<WithdrawList> getWithdrawList(@Header("Authorization") String token);
    @POST("user/signin")
    Call<LoginModel> loginUser(@Body Map<String,Object> user);
    @POST("user/signup")
    Call<LoginModel> registerUser(@Body Map<String,Object> user);
    @POST("user/withdraw")
    Call<RetrofitModel> sentWithdrawRequest(@Header("Authorization") String token,@Body Map<String,Object> withdraw);
    @GET("user/setting")
    Call<SettingModel> getApplicationSetting(@Header("Authorization") String token);
    @GET("user/quiz-question/{categoryId}")
    Call<QuestionListModel> getQuestion(@Header("Authorization") String token, @Path("categoryId") String categoryId);
}
