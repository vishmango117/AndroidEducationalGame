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

import java.util.Random;


public class Game2Fragment extends Fragment {

    private TextView displayEquation;
    private TextView ScoreDisplay;
    private Button addition;
    private Button subtraction;
    private Button division;
    private Button multiplication;

    private ProgressBar countdownTimer;
    private CountDownTimer countDownTimer;
    private int score;


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
        prng = new Random();
        score = 0;
        ScoreDisplay.setText("0");
        myoperation = "";
        runCountdowntimer(view);
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

    public void runCountdowntimer(View view) {
        i = 0;
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