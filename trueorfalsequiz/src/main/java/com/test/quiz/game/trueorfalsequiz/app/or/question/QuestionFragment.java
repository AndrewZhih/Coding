package com.test.quiz.game.trueorfalsequiz.app.or.question;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class QuestionFragment extends Fragment {

    TextView questionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.question_fragment, null);
        questionTextView = (TextView)view.findViewById(R.id.question_text_view);
        return view;
    }
}
