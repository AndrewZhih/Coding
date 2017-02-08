package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView mainTitleTV1;
    private TextView mainTitleTV2;

    private Button startBtn;
    private Button aboutBtn;
    private Button rateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mainTitleTV1 = (TextView)findViewById(R.id.mainTitleTV1);
        mainTitleTV2 = (TextView)findViewById(R.id.mainTitleTV2);

        startBtn = (Button)findViewById(R.id.startBtn);
        aboutBtn = (Button)findViewById(R.id.aboutBtn);
        rateBtn  = (Button)findViewById(R.id.rateBtn) ;

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        mainTitleTV1.setAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        mainTitleTV2.setAnimation(animation2);

        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate1);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate2);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate3);
        startBtn.setAnimation(animation3);
        aboutBtn.setAnimation(animation4);
        rateBtn.setAnimation(animation5);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_main_appears, R.anim.activity_start_gone);

            }
        });


    }
}
