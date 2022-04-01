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

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt,phoneEt,passwordEt,inviteCodeEt;
    private Button signupButton,loginButton;
    private ProgressDialog progressDialog;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameEt.getText().toString();
                String phone=phoneEt.getText().toString();
                String password=passwordEt.getText().toString().trim();
                String inviteCode=inviteCodeEt.getText().toString();
                if(name.isEmpty()){
                    nameEt.setError("Enter Your Name");
                    nameEt.requestFocus();
                }else if(phone.isEmpty()){
                    phoneEt.setError("Enter Your Phone number");
                    phoneEt.requestFocus();
                }else if(password.isEmpty()){
                    passwordEt.setError("Enter Your Password");
                    passwordEt.requestFocus();
                }else if(password.length()<6){
                    passwordEt.setError("Password Length Minimum 6 Character Long");
                    passwordEt.requestFocus();
                }else{
                    registerUser(name,phone,password,inviteCode);
                }
            }
        });






    }
    private void init(){
        nameEt=findViewById(R.id.r_nameEt);
        phoneEt=findViewById(R.id.r_phoneEt);
        passwordEt=findViewById(R.id.r_PasswordEt);
        inviteCodeEt=findViewById(R.id.r_InviteCodeEt);
        signupButton=findViewById(R.id.r_RegisterButton);
        loginButton=findViewById(R.id.r_LoginButton);
        userApi=new UserApi(this);
        progressDialog=new ProgressDialog(this);

    }

    private void registerUser(String name, String phone, String password, String inviteCode) {
        progressDialog.setMessage("Please Wait..");
        progressDialog.setTitle("We Are Creating Your Account.");
        progressDialog.setCancelable(false);

        @SuppressLint("HardwareIds") String deviceID= Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
        HashMap<String,Object> userMap=new HashMap<>();
        userMap.put("name",name);
        userMap.put("phone",phone);
        userMap.put("password",password);
        userMap.put("referCode",inviteCode);
        userMap.put("deviceId",deviceID);

        userApi.registerUser(userMap, progressDialog, new LoginResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, LoginModel loginModel) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                sendUserToMainActivity();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });






    }



    private void sendUserToMainActivity(){
        Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}