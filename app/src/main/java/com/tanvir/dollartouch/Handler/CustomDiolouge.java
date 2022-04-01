package com.tanvir.dollartouch.Handler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import com.tanvir.dollartouch.LocalDb.SettingDb;
import com.tanvir.dollartouch.R;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CustomDiolouge {

   public static List<String> networkList;
    private SettingDb settingDb;

   public  void upgradeDialog(Activity context){
       settingDb=new SettingDb(context);
       AlertDialog.Builder builder=new AlertDialog.Builder(context);
       View view=context.getLayoutInflater().inflate(R.layout.upgrade_dialog,null);
       builder.setView(view);

       Button updateNowButton=view.findViewById(R.id.updateNowButtonId);
       Button updateLaterButton=view.findViewById(R.id.updateLaterButtonid);

       if(settingDb.getSetting().getUpgradePriority().equals("high")){
           updateLaterButton.setVisibility(View.GONE);
       }else if(settingDb.getSetting().getUpgradePriority().equals("low")){
           updateLaterButton.setVisibility(View.VISIBLE);
       }

       final AlertDialog dialog=builder.create();
       dialog.show();
       dialog.setCancelable(false);
       dialog.setCanceledOnTouchOutside(false);
       updateNowButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String appPackageName = context.getPackageName(); // getPackageName() from Context or Activity object
               try {
                   context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
               } catch (android.content.ActivityNotFoundException anfe) {
                   context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
               }
           }
       });

       updateLaterButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialog.dismiss();
           }
       });

   }
    public static void blockDiolouge(Activity context,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=context.getLayoutInflater().inflate(R.layout.block_dialog_layout2,null);
        builder.setView(view);

        TextView okButton=view.findViewById(R.id.okButtonId);
        TextView textView=view.findViewById(R.id.timeTvId);
        textView.setText(""+message);
        final AlertDialog dialog=builder.create();
        dialog.show();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    public static void blockDiolouge2(Activity context,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=context.getLayoutInflater().inflate(R.layout.block_diolouge,null);
        builder.setView(view);

        Button okButton=view.findViewById(R.id.okButtonId);
        TextView textView=view.findViewById(R.id.text1);
        textView.setText(""+message);
        final AlertDialog dialog=builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

    }

    public static void vpnDialog(Activity context) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        View view=context.getLayoutInflater().inflate(R.layout.block_diolouge,null);
        builder.setView(view);

        Button okButton=view.findViewById(R.id.okButtonId);
        TextView textView=view.findViewById(R.id.text1);
        textView.setText("Please Connect Vpn ( USA  UK )   Country.");
        okButton.setText("Connect Now");
        final AlertDialog dialog=builder.create();
        dialog.show();

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkvpn()){
                    dialog.dismiss();
               }else{
                   Toast.makeText(context, "Please Connect Vpn", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }
    public static boolean checkvpn(){
        networkList = new ArrayList<>();
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp()) {
                    networkList.add(networkInterface.getName());
                }
            }
        } catch (Exception ex) {
        }
        return networkList.contains("tun0");
    }
}
