package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLogs";
    private Button positiveBtn;
    private Button negativeBtn;
    private TextView resOneTV;
    private TextView scoreTV;
    private TextView bestResultTV;
    private TextView currentResultTV;
    private TextView helpTV;
    private int currentResult;
    private int bestResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Animation animationScale = AnimationUtils.loadAnimation(this, R.anim.result_scale);
        Animation animationScaleHelp = AnimationUtils.loadAnimation(this, R.anim.help_scale);

        negativeBtn = (Button)findViewById(R.id.negativeBtn);
        positiveBtn = (Button)findViewById(R.id.positiveBtn);

        //helpTV = (TextView)findViewById(R.id.helpTV);
        resOneTV = (TextView)findViewById(R.id.resOneTV);
        scoreTV = (TextView)findViewById(R.id.scoreTV);
        bestResultTV = (TextView)findViewById(R.id.bestResultTV);
        currentResultTV = (TextView)findViewById(R.id.currentResultTV);

        //animationScaleHelp.setRepeatCount(Animation.INFINITE);
        //helpTV.startAnimation(animationScaleHelp);
        scoreTV.startAnimation(animationScale);


        Intent intent = getIntent();
        bestResult = intent.getIntExtra("BEST_RESULT", 0);
        currentResult = intent.getIntExtra("CURRENT_RESULT", 0);
        Log.d("myLogs", "RESULT ACTIVITY  bestResult " + bestResult + " currentResult " + currentResult);

        bestResultTV.setText(String.valueOf(bestResult));
        currentResultTV.setText(String.valueOf(currentResult));

        if(currentResult >= bestResult){
            scoreTV.setText("NEW SCORE!");
            scoreTV.setTextColor(Color.GREEN);
            resOneTV.setTextColor(Color.GREEN);
            currentResultTV.setTextColor(Color.GREEN);
        }

        if(bestResult > currentResult){
            scoreTV.setText("TRY ONE MORE TIME!");
            scoreTV.setTextColor(Color.RED);
            resOneTV.setTextColor(Color.RED);
            currentResultTV.setTextColor(Color.RED);
        }

        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent negativeIntent = new Intent(ResultActivity.this, StartActivity.class);
                //startActivity(negativeIntent);
                finish();
                Log.d(LOG_TAG, " Negative intent");
            }
        });

        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent positiveIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(positiveIntent);
                Log.d(LOG_TAG, "Positive intent");
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "ResultActivity onPause");
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "ResultActivity onStop");
    }

}
