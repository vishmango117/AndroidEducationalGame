package com.example.educationalgame.QnWhat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.educationalgame.R;

import java.util.Locale;
import java.util.Random;


public class Game2Fragment extends Fragment {

    private static final long COUNTDOWN_IN_MILLIS = 30000;

    private TextView displayEquation;
    private TextView ScoreDisplay;
    private TextView questionCountDisplay;


    private Button addition;
    private Button subtraction;
    private Button division;
    private Button multiplication;
    private Button confirmbutton;

    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private int score;
    private int questionCounter;

    private Long timeLeftMillis;
    private TextView countdown;

    public QuestionManager myquestions;
    private Question currentQn;
    private String myoperation;
    private Random prng;

    private ColorStateList textColorDefaultBtn;

    private long backPressedTime;

    private int i;

    Runnable r;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Arithematic Operations
        addition = view.findViewById(R.id.addition_operation);
        subtraction = view.findViewById(R.id.subtraction_operation);
        division = view.findViewById(R.id.division_operation);
        multiplication = view.findViewById(R.id.multiplication_operation);
        confirmbutton = view.findViewById(R.id.next_question);

       r = new Runnable() {
            @Override
            public void run() {
                getNextQuestion();
            }
        };


        // All the text Displays
        displayEquation = view.findViewById(R.id.display_equation);
        ScoreDisplay = view.findViewById(R.id.score_counter);
        countdown = view.findViewById(R.id.clock);
        questionCountDisplay = view.findViewById(R.id.qnwhat_qnno);
        textColorDefaultCd = countdown.getTextColors();


        timeLeftMillis = COUNTDOWN_IN_MILLIS;
        textColorDefaultBtn=addition.getTextColors();
        prng = new Random();
        score = 0;
        ScoreDisplay.setText("Hello");
        myoperation = "";
        currentQn = new Question(prng.nextInt(10),prng.nextInt(10));
        if(currentQn.number1 > currentQn.number2) {
            currentQn.getAnswer();
        }
        startCountDown();
        runGame();
    }


    private void getNextQuestion() {
        if(questionCounter < 5) {
            while(true) {
                currentQn = new Question(prng.nextInt(50), prng.nextInt(50));
                if(!(currentQn.number1 < currentQn.number2 && currentQn.operation.equals("/"))) {
                    break;
                }
            }
            currentQn.getAnswer();
            displayEquation.setText(currentQn.number1 + "[]" + currentQn.number2 + "=" + currentQn.answer);
            ScoreDisplay.setText("Score"+ score);
            questionCounter++;

            questionCountDisplay.setText("Question: " +questionCounter + "/" +5);
            //answered = false;
            confirmbutton.setText("Confirm");
            timeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else if(questionCounter == 5) {
            NavHostFragment.findNavController(Game2Fragment.this)
                    .navigate(R.id.action_game2Fragment_to_gameSelectionFragment);
        }
    }


    public void runGame() {
        for(int i =0;i<5;i++) {
            questionCounter = 0;
            displayEquation.setText(currentQn.number1 + "[]" + currentQn.number2 + "=" + currentQn.answer);
            addition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonStatus", "Clicked");
                    myoperation = "+";
                    addition.setTextColor(Color.BLUE);
                    multiplication.setTextColor(textColorDefaultBtn);
                    subtraction.setTextColor(textColorDefaultBtn);
                    division.setTextColor(textColorDefaultBtn);
                }
            });
            subtraction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonStatus", "Clicked");
                    myoperation = "-";
                    addition.setTextColor(textColorDefaultBtn);
                    multiplication.setTextColor(textColorDefaultBtn);
                    subtraction.setTextColor(Color.BLUE);
                    division.setTextColor(textColorDefaultBtn);
                }
            });
            division.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonStatus", "Clicked");
                    myoperation = "/";
                    addition.setTextColor(textColorDefaultBtn);
                    multiplication.setTextColor(textColorDefaultBtn);
                    subtraction.setTextColor(textColorDefaultBtn);
                    division.setTextColor(Color.BLUE);
                }
            });
            multiplication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ButtonStatus", "Clicked");
                    myoperation = "*";
                    addition.setTextColor(textColorDefaultBtn);
                    multiplication.setTextColor(Color.BLUE);
                    subtraction.setTextColor(textColorDefaultBtn);
                    division.setTextColor(textColorDefaultBtn);
                }
            });
            confirmbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("ClickStatus", "ClickedConfirm");
                    runValidation();
                }
            });
        }
    }



    public void runValidation() {
        countDownTimer.cancel();
        if (currentQn.operation.equals(myoperation)) {
            System.out.println(myoperation + "," + currentQn.operation);
            Log.d("Equation", "Correct");
            score++; // Update Score
            displayEquation.setText(currentQn.number1 + currentQn.operation + currentQn.number2 + "=" + currentQn.answer);
        }
        else {
            displayEquation.setText(currentQn.number1 + currentQn.operation + currentQn.number2 + "=" + currentQn.answer);
        }
        Handler h = new Handler();
        h.postDelayed(r, 3000);
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