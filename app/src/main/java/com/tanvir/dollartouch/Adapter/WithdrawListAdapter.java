package com.tanvir.dollartouch.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.dollartouch.DataModel.Category;
import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.DataModel.WithdrawModel;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.R;

public class WithdrawListAdapter extends RecyclerView.Adapter<WithdrawListAdapter.MyViewHolder>{

    private Activity context;
    private WithdrawList withdrawList;
    private String name;

    public WithdrawListAdapter(Activity context,String name, WithdrawList withdrawList) {
        this.context = context;
        this.name=name;
        this.withdrawList = withdrawList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.recent_payment_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       WithdrawModel withdraw=withdrawList.getWithdraw().get(position);

       holder.amountTv.setText(Currency.getApplicationCurrency(context) +withdraw.getAmount());
       holder.nameTv.setText(""+name.substring(0,4));
       holder.paymentMethodTv.setText(""+withdraw.getWithdrawWith());
       holder.statusTv.setText(""+withdraw.getState());
       holder.dateTv.setText("Date: "+withdraw.getTime());



    }

    @Override
    public int getItemCount() {
        return withdrawList.getWithdraw().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView nameTv,paymentMethodTv,statusTv,dateTv,amountTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            amountTv=itemView.findViewById(R.id.rp_amountTvId);
            nameTv=itemView.findViewById(R.id.rp_nameTvId);
            paymentMethodTv=itemView.findViewById(R.id.rp_paymentMethodTv);
            statusTv=itemView.findViewById(R.id.rp_paymentStatusTvId);
            dateTv=itemView.findViewById(R.id.rp_dateTvID);
        }



    }



}
