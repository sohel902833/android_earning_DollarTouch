package com.tanvir.dollartouch.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tanvir.dollartouch.DataModel.WithdrawList;
import com.tanvir.dollartouch.DataModel.WithdrawModel;
import com.tanvir.dollartouch.Handler.Currency;
import com.tanvir.dollartouch.R;
import com.tanvir.dollartouch.WithdrawActivity;

public class PaymentStatusAdapter extends RecyclerView.Adapter<PaymentStatusAdapter.MyViewHolder>{

    private Activity context;
    private WithdrawList withdrawList;

    public PaymentStatusAdapter(Activity context, WithdrawList withdrawList) {
        this.context = context;
        this.withdrawList = withdrawList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.payment_status_item_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       WithdrawModel withdraw=withdrawList.getWithdraw().get(position);
       holder.amountTv.setText(Currency.getApplicationCurrency(context)+withdraw.getAmount());
       holder.paymentMethodTv.setText(""+withdraw.getWithdrawWith());
       if(withdraw.getState().equals("pending")){
           holder.amountTv.setTextColor(context.getResources().getColor(R.color.redAsh));
           holder.paymentMethodTv.setTextColor(context.getResources().getColor(R.color.redAsh));
           holder.indigator.setBackground(context.getResources().getDrawable(R.drawable.red_ash_circle));
       }

    }

    @Override
    public int getItemCount() {
        return withdrawList.getWithdraw().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView paymentMethodTv,amountTv;
        View indigator;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            amountTv=itemView.findViewById(R.id.ps_amountTv);
            paymentMethodTv=itemView.findViewById(R.id.ps_withdrawTypeTv);
            indigator=itemView.findViewById(R.id.ps_IndigatorTv);
        }



    }



}
