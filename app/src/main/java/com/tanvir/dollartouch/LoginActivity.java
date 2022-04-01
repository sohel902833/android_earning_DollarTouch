package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.LoginModel;
import com.tanvir.dollartouch.RetrofitResponse.LoginResponse;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneEt,passwordEt;
    private Button signupButton,loginButton;
    private ProgressDialog progressDialog;
    private UserApi userApi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneEt.getText().toString();
                String password=passwordEt.getText().toString().trim();
                 if(phone.isEmpty()){
                    phoneEt.setError("Enter Your Phone number");
                    phoneEt.requestFocus();
                }else if(password.isEmpty()){
                    passwordEt.setError("Enter Your Password");
                    passwordEt.requestFocus();
                }else{
                    loginUser(phone,password);
                }
            }
        });


    }


    private void init(){
        phoneEt=findViewById(R.id.l_poneEt);
        passwordEt=findViewById(R.id.l_passwordEt);
        signupButton=findViewById(R.id.l_createAccountButton);
        loginButton=findViewById(R.id.l_loginButton);
        userApi=new UserApi(this);
        progressDialog=new ProgressDialog(this);

    }

    private void loginUser(String phone, String password) {
        progressDialog.setMessage("Please Wait..");
        progressDialog.setTitle("We Are Creating Your Account.");
        progressDialog.setCancelable(false);

        HashMap<String,Object> userMap=new HashMap<>();
        userMap.put("phone",phone);
        userMap.put("password",password);

        userApi.loginUser(userMap, progressDialog, new LoginResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                sendUserToMainActivity();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void sendUserToMainActivity(){
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}