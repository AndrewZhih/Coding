package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "myLogs";
    public final String PREFS_KEY = "PREFS_KEY";
    public final String BEST_RESULT = "BEST_RESULT";

    private ImageButton mTrueButton;
    private ImageButton mFalseButton;

    private TextView mQuestionTextView;
    private TextView countTextView;

    private ProgressBar progressBar;

    private Timer timer = new Timer();
    private TimerTask timerTask;
    private int progress = 1000;

    private int mCurrentIndex = 0;

    private SoundPool soundPool;
    private final int MAX_STREAMS = 5;
    private int soundIdCorrect;
    private int soundIdWrong;

    private int currentResult = 0;
    private int bestResult;

    MyTask myTask = new MyTask();
    //timer = new Timer();

    private void updateQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        progress = 1000;
        //MyTask myTask = new MyTask();
        //timer.schedule(myTask, 100, 10);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (userPressedTrue == answerIsTrue) {
            //myTask.cancel();
            soundPool.play(soundIdCorrect, 1, 1, 0, 0, 1);
            updateQuestion();
            currentResult++;
            countTextView.setText(String.valueOf(currentResult));
        } else {
            if(currentResult >= bestResult) {
                bestResult = currentResult;
                SharedPreferences resultPrefs = this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = resultPrefs.edit();
                editor.putInt(BEST_RESULT, bestResult);
                editor.commit();
            }

            Log.d(LOG_TAG, "myTask cancel in if/else");
            myTask.cancel();
            soundPool.play(soundIdWrong, 1, 1, 0, 0, 1);
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("BEST_RESULT", bestResult);
            intent.putExtra("CURRENT_RESULT", currentResult);
            startActivity(intent);
            finish();
        }
    }

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_lighting, false),
            new Question(R.string.question_cry, true),
            new Question(R.string.question_earthworm, false),
            new Question(R.string.question_smells, true),
            new Question(R.string.question_bones, true),
            new Question(R.string.question_napoleon, false),
            new Question(R.string.question_goldfish, false),
            new Question(R.string.question_cells, true),
            new Question(R.string.question_hair, false),
            new Question(R.string.question_mint, true),
            new Question(R.string.question_twinkies, false),
            new Question(R.string.question_humans, true),
            new Question(R.string.question_santa, false),
            new Question(R.string.question_alcohol, false),
            new Question(R.string.question_segway, true),
            new Question(R.string.question_atom_age, true),
            new Question(R.string.question_whales, true),
            new Question(R.string.question_gold, true),
            new Question(R.string.question_atlantic, false),
            new Question(R.string.question_kelvin, true),
            new Question(R.string.question_sun, true),
            new Question(R.string.question_slug, true),
            new Question(R.string.question_great_wall, false),
            new Question(R.string.question_casino, true),
            new Question(R.string.question_surface, true),
            new Question(R.string.question_underwater, true),
            new Question(R.string.question_rubik, false),
            new Question(R.string.question_paul, false),
            new Question(R.string.question_jupiter, true),
            new Question(R.string.question_garlic, true),
            new Question(R.string.question_donald, true),
            new Question(R.string.question_black_box, false),
            new Question(R.string.question_crow, true),
            new Question(R.string.question_king, false),
            new Question(R.string.question_aztec, true),
            new Question(R.string.question_fax, true),
            new Question(R.string.question_coffee, true),
            new Question(R.string.question_wombat, false),
            new Question(R.string.question_oreo, true),
            new Question(R.string.question_unicorn, true),
            new Question(R.string.question_lincoln, false),
            new Question(R.string.question_termites, true),
            new Question(R.string.question_cows, false),
            new Question(R.string.question_earth, true),
            new Question(R.string.question_banana, false),
            new Question(R.string.question_octopus, false),
            new Question(R.string.question_moon, true),
            new Question(R.string.question_stars, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences resultPrefs = this.getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        bestResult = resultPrefs.getInt(BEST_RESULT, 0);
        Log.d(LOG_TAG, "Get best result " + bestResult);


        mTrueButton  = (ImageButton) findViewById(R.id.true_button );
        mFalseButton = (ImageButton) findViewById(R.id.false_button);

        countTextView = (TextView)findViewById(R.id.countTextView);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(progress);

        soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        soundIdCorrect = soundPool.load(this, R.raw.correct, 1);
        soundIdWrong = soundPool.load(this, R.raw.lose, 1);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "RUN in HANDLER in onCreate");
                //myTask = new MyTask();
                timer.schedule(myTask, 100, 10);
            }
        }, 900);

        QuestionFragment fragment = new QuestionFragment();
        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container, fragment);
        ft.commit();

    }

    @Override
    public void onStart(){
        super.onStart();
        Fragment fragment1 = getFragmentManager().findFragmentById(R.id.fragment_container);
        mQuestionTextView = ((TextView)fragment1.getView().findViewById(R.id.question_text_view));


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        myTask.cancel();
        timer.cancel();
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        myTask.cancel();
        timer.cancel();
        finish();
    }

    private class MyTask extends TimerTask{
        @Override
        public void run() {
            //Log.d(LOG_TAG, "RUN in MyTask class");
            --progress;
            if(progress >= 0){
                progressBar.setProgress(progress);
                if(progress == 0){
                    cancel();
                    soundPool.play(soundIdWrong, 1, 1, 0, 0, 1);
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    startActivity(intent);
                }
            }
        }
    }


}
