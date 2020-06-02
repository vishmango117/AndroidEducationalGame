package com.example.educationalgame.game2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.educationalgame.R;



public class Game2Fragment extends Fragment {

    private TextView displayEquation;
    private TextView ScoreDisplay;
    private Button addition;
    private Button subtraction;
    private Button division;
    private Button multiplication;

    private ProgressBar countdownTimer;
    private CountDownTimer countDownTimer;
    private int i;
    private int score;


    private Question myquestion;
    private String myoperation = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addition = view.findViewById(R.id.addition_operation);
        subtraction = view.findViewById(R.id.subtraction_operation);
        division = view.findViewById(R.id.division_operation);
        multiplication = view.findViewById(R.id.multiplication_operation);
        displayEquation = view.findViewById(R.id.display_equation);
        ScoreDisplay = view.findViewById(R.id.score_display);
        myquestion = new Question(5,4);

        score = 0;
        ScoreDisplay.setText("0");

        runCountdowntimer(view);
        runGame();

    }

    public void runGame() {
        displayEquation.setText(myquestion.number1 +" [] " + myquestion.number2 + " = 9");
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myoperation= "+";
                if(myoperation.equals(myquestion.operation)) {
                    displayEquation.setText(myquestion.number1 +" + " + myquestion.number2 + " = 9");
                    score++;
                    ScoreDisplay.setText(String.valueOf(score));
                    //Move to Next Question
                }
            }
        });
    }

    public void runValidation() {

    }

    public void runCountdowntimer(View view) {
        countdownTimer = view.findViewById(R.id.countdown_timer);
        countdownTimer.setProgress(i);
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress" + i + millisUntilFinished);
                i++;
                countdownTimer.setProgress((int) i * 100 / (20000 / 1000));
            }



            @Override
            public void onFinish() {
                i++;
                countdownTimer.setProgress(100);

            }
        };
        countDownTimer.start();
    }
}