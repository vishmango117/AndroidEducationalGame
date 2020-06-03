package com.example.educationalgame.game2;

import android.content.res.ColorStateList;
import android.graphics.Color;
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

import java.util.Locale;
import java.util.Random;


public class Game2Fragment extends Fragment {

    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView displayEquation;
    private TextView ScoreDisplay;
    private Button addition;
    private Button subtraction;
    private Button division;
    private Button multiplication;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private int score;

    private Long timeLeftMillis;
    private TextView countdown;

    public QuestionManager myquestions;
    private Question currentQn;
    private String myoperation;
    private Random prng;

    private int i;


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
        countdown = view.findViewById(R.id.clock);
        textColorDefaultCd = countdown.getTextColors();
        timeLeftMillis = COUNTDOWN_IN_MILLIS;
        prng = new Random();
        score = 0;
        ScoreDisplay.setText("0");
        myoperation = "";
        startCountDown();
        prepareGame();
        for(int i=0;i<5; i++) {
            runGame(i);
        }
    }

    public void prepareGame() {
        myquestions = new QuestionManager();
        for(int i =0; i<5;i++) {
            currentQn = new Question(prng.nextInt(10),prng.nextInt(10));
            currentQn.getAnswer();
            myquestions.questions.add(currentQn);
        }
    }


    public void runGame(int index) {
        currentQn = myquestions.questions.get(index);
           displayEquation.setText(currentQn.number1 + "[]" + currentQn.number2 + "=" + currentQn.answer);
           addition.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Log.d("ButtonStatus", "Clicked");
                   myoperation = "+";
                   System.out.println(currentQn.operation);
                   if(currentQn.operation.equals(myoperation)) {
                       Log.d("Equation", "Correct");
                       score++; // Update Score
                       displayEquation.setText(currentQn.number1 + "+" + currentQn.number2 + "=" + currentQn.answer);
                       return;
                   }
               }
           });
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonStatus", "Clicked");
                myoperation = "-";
                System.out.println(currentQn.operation);
                if(currentQn.operation.equals(myoperation)) {
                    Log.d("Equation", "Correct");
                    score++; // Update Score
                    displayEquation.setText(currentQn.number1 + "-" + currentQn.number2 + "=" + currentQn.answer);
                    return;
                }
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonStatus", "Clicked");
                myoperation = "/";
                System.out.println(currentQn.operation);
                if(currentQn.operation.equals(myoperation)) {
                    Log.d("Equation", "Correct");
                    score++; // Update Score
                    displayEquation.setText(currentQn.number1 + "/" + currentQn.number2 + "=" + currentQn.answer);
                    return;
                }
            }
        });
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ButtonStatus", "Clicked");
                myoperation = "*";
                System.out.println(currentQn.operation);
                if(currentQn.operation.equals(myoperation)) {
                    Log.d("Equation", "Correct");
                    score++; // Update Score
                    displayEquation.setText(currentQn.number1 + "*" + currentQn.number2 + "=" + currentQn.answer);
                    return;
                }
            }
        });
    }

    public void runValidation() {

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftMillis = Long.valueOf(0);
                updateCountDownText();
            }
        }.start();
    }

    public void updateCountDownText() {
        int minutes = (int) (timeLeftMillis / 1000) / 60;
        int seconds = (int) (timeLeftMillis /1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        countdown.setText(timeFormatted);
        if(timeLeftMillis < 10000) {
            countdown.setTextColor(Color.RED);
        }
        else {
            countdown.setTextColor(textColorDefaultCd);
        }
    }
}