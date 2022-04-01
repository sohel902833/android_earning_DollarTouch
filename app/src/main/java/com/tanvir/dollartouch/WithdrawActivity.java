package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.RetrofitResponse;

import java.util.HashMap;

public class WithdrawActivity extends AppCompatActivity {

    private TextView shortNameTv,nameTv,selectedMethodTv,balanceTv;
    private CardView bkashCard,nagadCard,rechargeCard;
    private EditText accountNoEdittext;
    private Button cashoutButton;
    private UserDb userDb;
    private UserApi userApi;
    private ProgressDialog progressDialog;

    private static final String BKASH_TYPE="bKash";
    private static final String NAGAD_TYPE="nagad";
    private static final String RECHARGE_TYPE="recharge";


    String SELECTED_WITHDRAW_METHOD=BKASH_TYPE;
    private TextView bkashAmountTv,nagadAmountTv,rechargeAmountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        init();
        setupUI();
        setCardColor();
        bkashCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_WITHDRAW_METHOD=BKASH_TYPE;
                setCardColor();
            }
        });
        nagadCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_WITHDRAW_METHOD=NAGAD_TYPE;
                setCardColor();
            }
        });
        rechargeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_WITHDRAW_METHOD=RECHARGE_TYPE;
                setCardColor();
            }
        });


        cashoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountNo=accountNoEdittext.getText().toString();
                if(accountNo.isEmpty()){
                    accountNoEdittext.setError("Enter "+SELECTED_WITHDRAW_METHOD+" Account No.");
                    accountNoEdittext.requestFocus();
                }else{
                    withdraw(accountNo.trim());
                }
            }
        });






    }


    private void init(){

        userDb=new UserDb(this);
        userApi=new UserApi(this);
        progressDialog=new ProgressDialog(this);

        balanceTv=findViewById(R.id.w_balanceTv);
        shortNameTv=findViewById(R.id.w_shortNameTv);
        nameTv=findViewById(R.id.w_nameTv);
        selectedMethodTv=findViewById(R.id.w_selectedMethodTv);
        bkashCard=findViewById(R.id.w_bkashCard);
        nagadCard=findViewById(R.id.w_NagadCard);
        rechargeCard=findViewById(R.id.w_RechargeCard);
        accountNoEdittext=findViewById(R.id.w_accountNoEt);
        cashoutButton=findViewById(R.id.w_CashoutButton);
        bkashAmountTv=findViewById(R.id.w_bkashCard_Amount);
        nagadAmountTv=findViewById(R.id.w_NagadCard_Amount);
        rechargeAmountTv=findViewById(R.id.w_RechargeCard_Amount);
    }
    private void setupUI(){
        User user=userDb.getUserData();
        balanceTv.setText(Currency.getApplicationCurrency(WithdrawActivity.this)+user.getBalance());
        nameTv.setText(""+user.getName());
         shortNameTv.setText(""+user.getName().substring(0,1));
         bkashAmountTv.setText(Currency.getApplicationCurrency(WithdrawActivity.this)+"500");
         nagadAmountTv.setText(Currency.getApplicationCurrency(WithdrawActivity.this)+"250");
         rechargeAmountTv.setText(Currency.getApplicationCurrency(WithdrawActivity.this)+"50");

    }

    private void setCardColor(){

        if(SELECTED_WITHDRAW_METHOD.equals(RECHARGE_TYPE)){
            selectedMethodTv.setText("Enter "+SELECTED_WITHDRAW_METHOD+" Phone Number");
            accountNoEdittext.setHint("Enter "+SELECTED_WITHDRAW_METHOD+" Phone Number");

        }else{
            selectedMethodTv.setText("Enter "+SELECTED_WITHDRAW_METHOD+" Account No.");
            accountNoEdittext.setHint("Enter "+SELECTED_WITHDRAW_METHOD+" Account No.");
        }

        if(SELECTED_WITHDRAW_METHOD.equals(BKASH_TYPE)){
            bkashCard.setCardBackgroundColor(getResources().getColor(R.color.greenDark2));
            nagadCard.setCardBackgroundColor(getResources().getColor(R.color.white));
            rechargeCard.setCardBackgroundColor(getResources().getColor(R.color.white));
            selectedMethodTv.setText("Enter "+SELECTED_WITHDRAW_METHOD+" Account No.");
        }else if(SELECTED_WITHDRAW_METHOD.equals(NAGAD_TYPE)){
            bkashCard.setCardBackgroundColor(getResources().getColor(R.color.white));
            nagadCard.setCardBackgroundColor(getResources().getColor(R.color.greenDark2));
            rechargeCard.setCardBackgroundColor(getResources().getColor(R.color.white));
        }else if(SELECTED_WITHDRAW_METHOD.equals(RECHARGE_TYPE)){
            bkashCard.setCardBackgroundColor(getResources().getColor(R.color.white));
            nagadCard.setCardBackgroundColor(getResources().getColor(R.color.white));
            rechargeCard.setCardBackgroundColor(getResources().getColor(R.color.greenDark2));
        }
    }


    private void withdraw(String accountNo){
        progressDialog.setMessage("Sending Cashout Request..");
        HashMap<String,Object> withdrawMap=new HashMap<>();
        withdrawMap.put("amount",getBalance());
        withdrawMap.put("withdrawWith",SELECTED_WITHDRAW_METHOD);
        withdrawMap.put("accountNo",accountNo);

        userApi.sentWithdrawRequest(withdrawMap, progressDialog, new RetrofitResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(WithdrawActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(WithdrawActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });




    }


    private int getBalance(){
        if(SELECTED_WITHDRAW_METHOD.equals(BKASH_TYPE))
            return 500;
        else if(SELECTED_WITHDRAW_METHOD.equals(NAGAD_TYPE))
            return 250;
        else if(SELECTED_WITHDRAW_METHOD.equals(RECHARGE_TYPE))
            return 50;
        else
            return 0;
    }



}