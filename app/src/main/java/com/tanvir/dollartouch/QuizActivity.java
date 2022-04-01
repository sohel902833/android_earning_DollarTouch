package com.tanvir.dollartouch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tanvir.dollartouch.ApiCall.UserApi;
import com.tanvir.dollartouch.DataModel.QuestionListModel;
import com.tanvir.dollartouch.DataModel.QuestionModel;
import com.tanvir.dollartouch.LocalDb.QuestionDb;
import com.tanvir.dollartouch.RetrofitResponse.QuestionListResponse;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private ProgressBar progressBar,loadingBar;
    private TextView questionTv,option1Tv,option2Tv,option3Tv;
    private CardView option1Card,option2Card,option3Card;
    private Button nextQuestionButton;

    int progress=0;
    String categoryId="0";
    private UserApi userApi;
    List<QuestionModel> questionList=new ArrayList<>();
    int currentState=0;
    QuestionDb questionDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        categoryId=getIntent().getStringExtra("categoryId");
        init();


        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentState+=1;
                fillUpQuestions();
            }
        });



        option1Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(option1Card);
            }
        });  option2Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(option2Card);
            }
        });  option3Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCardColor(option3Card);
            }
        });

//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                doWork();
//            }
//        });
//        thread.start();

    }

    private  void init(){
        questionTv=findViewById(R.id.q_questionTv);
        nextQuestionButton=findViewById(R.id.nextQuestionBtn);
        option1Tv=findViewById(R.id.q_option1Tv);
        option2Tv=findViewById(R.id.q_option2Tv);
        option3Tv=findViewById(R.id.q_option3Tv);
        option1Card=findViewById(R.id.q_option1Card);
        option2Card=findViewById(R.id.q_option2Card);
        option3Card=findViewById(R.id.q_option3Card);
        loadingBar=findViewById(R.id.loadingBar);
        userApi=new UserApi(this);
        questionDb=new QuestionDb(this);
        //set current state
        currentState=questionDb.getCurrentState(categoryId);

    }


    @Override
    protected void onStart() {
        super.onStart();
        loadingBar.setVisibility(View.VISIBLE);
        userApi.getQuestions(categoryId, new QuestionListResponse() {
            @Override
            public void onSuccess(String message, QuestionListModel questions) {
                loadingBar.setVisibility(View.GONE);
                questionList.clear();
                questionList=questions.getQuestions();
                fillUpQuestions();

            }

            @Override
            public void onError(String message) {
                loadingBar.setVisibility(View.GONE);
                Toast.makeText(QuizActivity.this, ""+message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fillUpQuestions(){
        if(questionList!=null && questionList.size()>currentState){
            QuestionModel currentQuestion=questionList.get(currentState);
            questionTv.setText("Q: "+currentQuestion.getQuestion());
            option1Tv.setText(""+currentQuestion.getOption1());
            option2Tv.setText(""+currentQuestion.getOption2());
            option3Tv.setText(""+currentQuestion.getOption3());
            questionDb.setCurrentState(currentState,categoryId);

        }else{
            Toast.makeText(this, "No More Question Is Here", Toast.LENGTH_SHORT).show();
            //need to remove later
            questionDb.setCurrentState(0,categoryId);
            finish();
        }
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
    private void setCardColor(CardView card){
        option1Card.setCardBackgroundColor(getResources().getColor(R.color.white));
        option2Card.setCardBackgroundColor(getResources().getColor(R.color.white));
        option3Card.setCardBackgroundColor(getResources().getColor(R.color.white));
        card.setCardBackgroundColor(getResources().getColor(R.color.greenDark2));
    }


}