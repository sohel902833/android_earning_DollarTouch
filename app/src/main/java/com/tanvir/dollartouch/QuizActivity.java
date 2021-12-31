package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class QuizActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    int progress=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        init();

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
            }
        });
        thread.start();

    }

    private  void init(){
        progressBar=findViewById(R.id.progressBarId);

    }

    private  void doWork(){
        for(progress=10; progress<=100; progress=progress+10){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }



}