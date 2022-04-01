package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.tanvir.dollartouch.Adapter.WithdrawListAdapter;
import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.Category;
import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.User;
import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.DataModel.WithdrawModel;
import com.tanvir.dollartouch.LocalDb.UserDb;
import com.tanvir.dollartouch.RetrofitResponse.WitdrawListResponse;

import java.util.ArrayList;
import java.util.List;

public class RecentPaymentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserApi userApi;
    private UserDb userDb;
    private ProgressDialog progressDialog;
    private WithdrawListAdapter withdrawListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_payment);

        init();


    }

    private  void init(){
        recyclerView=findViewById(R.id.recentPaymentRecyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userApi=new UserApi(this);
        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
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
                    withdrawListAdapter=new WithdrawListAdapter(RecentPaymentActivity.this,userDb.getUserData().getName(),getPaidWithdraw(withdrawList));
                    recyclerView.setAdapter(withdrawListAdapter);
                }else{
                    Toast.makeText(RecentPaymentActivity.this, "Not Found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                progressDialog.dismiss();
                Toast.makeText(RecentPaymentActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private WithdrawList getPaidWithdraw(WithdrawList withdrawList){
        List<WithdrawModel> paidWithdrawList=new ArrayList<>();
        for(int i=0; i<withdrawList.getWithdraw().size(); i++){
            WithdrawModel withdraw=withdrawList.getWithdraw().get(i);
            if(withdraw.getState().equals("paid")){
                paidWithdrawList.add(withdraw);
            }


        }
        return  new WithdrawList(paidWithdrawList);
    }



}