package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView mainTitleTV1;
    private TextView mainTitleTV2;

    private ImageView imageView;

    private Button startBtn;
    private Button aboutBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        /*mainTitleTV1 = (TextView)findViewById(R.id.mainTitleTV1);
        mainTitleTV2 = (TextView)findViewById(R.id.mainTitleTV2);*/

        startBtn = (Button)findViewById(R.id.startBtn);
        aboutBtn = (Button)findViewById(R.id.aboutBtn);
        exitBtn  = (Button)findViewById(R.id.exitBtn) ;

        imageView = (ImageView)findViewById(R.id.imageView);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        imageView.setAnimation(animation1);

        /*Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.alpha1);
        mainTitleTV2.setAnimation(animation2);*/

        Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate1);
        Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate2);
        Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.horizontaltranslate3);
        startBtn.setAnimation(animation3);
        aboutBtn.setAnimation(animation4);
        exitBtn.setAnimation(animation5);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_main_appears, R.anim.activity_start_gone);

            }
        });

        aboutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten1 = new Intent(StartActivity.this, InfoActivity.class);
                startActivity(inten1);
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
