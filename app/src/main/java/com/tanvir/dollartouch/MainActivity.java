package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tanvir.dollartouch.Adapter.CategoryAdapter;
import com.tanvir.dollartouch.ApiCall.DataApi;
import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.Category;
import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.SettingModel;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.QuizCategoryResponse;
import com.tanvir.dollartouch.RetrofitResponse.SettingResponse;
import com.tanvir.dollartouch.RetrofitResponse.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserApi userApi;
    private DataApi dataApi;
    private UserDb userDb;
    private ProgressDialog progressDialog;
    TextView appBarBalanceTv;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fillupCategoryList();

        categoryAdapter.setOnItemClickListner(new CategoryAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                Category category=categoryList.get(position);
                Intent intent=new Intent(MainActivity.this,QuizActivity.class);
                intent.putExtra("categoryId",String.valueOf(category.getCategoryId()));
                startActivity(intent);
            }
        });


    }

    private void fillupCategoryList() {
        categoryList.clear();
        categoryList.add(new Category(1, "Blogging","Complete Surveys",78,5,R.drawable.ct_item_1));
        categoryList.add(new Category(2, "Dropshipping","Complete Surveys",78,5,R.drawable.ct_item_2));
        categoryList.add(new Category(3, "Cryptocurrency","Complete Surveys",78,5,R.drawable.ct_item_3));
        categoryList.add(new Category(4, "Forex Trading","Complete Surveys",78,5,R.drawable.ct_item_4));
        categoryList.add(new Category(5, "Youtube Visit","Complete Surveys",78,5,R.drawable.ct_item_5));
        CategoryList list=new CategoryList(categoryList);
        categoryAdapter=new CategoryAdapter(this,list);
        recyclerView.setAdapter(categoryAdapter);


    }

    private  void init(){
        userApi=new UserApi(this);
        dataApi=new DataApi(this);
        userDb=new UserDb(MainActivity.this);
        progressDialog=new ProgressDialog(this);

        recyclerView=findViewById(R.id.categoryRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setup Toolbar
        Toolbar toolbar = findViewById(R.id.appBarId);
        ImageView profileImageButton = (ImageView)toolbar.findViewById(R.id.appBarProfileImageViewid);
         appBarBalanceTv=toolbar.findViewById(R.id.appBarBalanceTvId);
        ImageView menuButton=toolbar.findViewById(R.id.appBarMenuIcn);

        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();
        String token=userDb.getToken();
        if(token.isEmpty()){
            sendUserToLoginActivity();
        }else{

            new Thread(new Runnable() {
                @Override
                public void run() {
                    userApi.getApplicationSetting(new SettingResponse() {
                        @Override
                        public void onSuccess(String message, SettingModel settingModel) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    double cVersion=Double.parseDouble(BuildConfig.VERSION_NAME);
                                    double dVersion=settingModel.getVersionNo();
                                    if(cVersion<dVersion){
                                        //customDiolouge.upgradeDialog(MainActivity.this);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError(String message) {

                        }
                    });
                }
            }).start();


            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            userApi.getCurrentUser(progressDialog, new UserResponse() {
                @Override
                public void onSuccess(String message, ProgressDialog progressDialog, User user) {
                    progressDialog.dismiss();
                    appBarBalanceTv.setText(Currency.getApplicationCurrency(MainActivity.this)+user.getBalance());
                }

                @Override
                public void onError(String message, ProgressDialog progressDialog) {
                    progressDialog.dismiss();
                    userDb.removeUserData();
                    sendUserToLoginActivity();
                }
            });
        }
    }/*
    private void getCategoryList(){
        progressDialog.setMessage("Loading..");
        dataApi.getCategoryList(progressDialog, new QuizCategoryResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, CategoryList categoryList) {
                progressDialog.dismiss();
                categoryAdapter=new CategoryAdapter(MainActivity.this,categoryList);
                recyclerView.setAdapter(categoryAdapter);

                categoryAdapter.setOnItemClickListner(new CategoryAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(int position) {
                        Category category=categoryList.getCategorys().get(position);

                        Intent intent=new Intent(MainActivity.this,QuizActivity.class);
                        intent.putExtra("categoryId",category.getCategoryId());
                        startActivity(intent);


                    }
                });


            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }*/
    private void sendUserToLoginActivity(){
        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}