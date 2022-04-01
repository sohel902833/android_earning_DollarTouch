package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tanvir.dollartouch.Adapter.PaymentStatusAdapter;
import com.tanvir.dollartouch.Adapter.WithdrawListAdapter;
import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.WitdrawListResponse;

public class PaymentStatusActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PaymentStatusAdapter paymentStatusAdapter;
    private ProgressDialog progressDialog;
    private UserDb userDb;
    private UserApi userApi;


    private TextView shortNameTv,nameTv,balanceTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_status);
        init();
        setUI();
    }


    @Override
    protected void onStart() {
        super.onStart();
        progressDialog.setMessage("Loading..");
        userApi.getWithdrawList(progressDialog, new WitdrawListResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog, WithdrawList withdrawList) {
                progressDialog.dismiss();
                if(withdrawList.getWithdraw()!=null){
                    paymentStatusAdapter=new PaymentStatusAdapter(PaymentStatusActivity.this,withdrawList);
                    recyclerView.setAdapter(paymentStatusAdapter);
                }else{
                    Toast.makeText(PaymentStatusActivity.this, "Not Found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(PaymentStatusActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private  void init(){
        recyclerView=findViewById(R.id.paymentStatusRecyclerViewId);
        shortNameTv=findViewById(R.id.ps_ShortNameTv);
        nameTv=findViewById(R.id.ps_NameTv);
        balanceTv=findViewById(R.id.ps_balanceTv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
        userApi=new UserApi(this);
    }
    private void setUI(){
        User user=userDb.getUserData();
        shortNameTv.setText(""+user.getName().substring(0,1));
        nameTv.setText(""+user.getName());
        balanceTv.setText(Currency.getApplicationCurrency(PaymentStatusActivity.this)+user.getBalance());
    }

}