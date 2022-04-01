package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.UserResponse;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTv,balanceTv,referCodeTv,nameShortTv;
    private UserDb userDb;
    private UserApi userApi;
    private ProgressDialog progressDialog;

    private CardView cashoutCard,paymentStatusCard,recentPaymentCard,logoutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();


        cashoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,WithdrawActivity.class));
            }
        });
        paymentStatusCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,PaymentStatusActivity.class));
            }
        });

        recentPaymentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,RecentPaymentActivity.class));
            }
        });
        logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDb.logoutUser();
             }
        });
    }

    private void init(){
            userDb=new UserDb(ProfileActivity.this);
            userApi=new UserApi(this);
            progressDialog=new ProgressDialog(this);

            nameTv=findViewById(R.id.p_nameTv);
            balanceTv=findViewById(R.id.p_BalanceTv);
            referCodeTv=findViewById(R.id.p_ReferCodeTv);
            paymentStatusCard=findViewById(R.id.p_paymentSatusCard);
            recentPaymentCard=findViewById(R.id.p_recentPaymentCard);
            nameShortTv=findViewById(R.id.p_nameShortTvId);
            logoutCard=findViewById(R.id.p_logoutCard);
            cashoutCard=findViewById(R.id.p_CashoutCard);
    }

    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        userApi.getCurrentUser(progressDialog, new UserResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, User user) {
                progressDialog.dismiss();
                balanceTv.setText(Currency.getApplicationCurrency(ProfileActivity.this)+user.getBalance());
                nameTv.setText(""+user.getName());
                referCodeTv.setText(""+user.getMyReferCode());
                nameShortTv.setText(""+user.getName().substring(0,1));
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
            }
        });
    }
}