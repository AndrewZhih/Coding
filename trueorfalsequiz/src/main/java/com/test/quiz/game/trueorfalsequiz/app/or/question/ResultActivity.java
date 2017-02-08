package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView bestResultTV;
    TextView currentResultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        bestResultTV = (TextView)findViewById(R.id.bestResultTV);
        currentResultTV = (TextView)findViewById(R.id.currentResultTV);

    }
}
