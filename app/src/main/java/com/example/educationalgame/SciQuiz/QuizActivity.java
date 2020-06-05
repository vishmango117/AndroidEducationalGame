package com.example.educationalgame.SciQuiz;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.educationalgame.R;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    private static final long COUNTDOWN_IN_MILLIS = 30000;


    //TextView for Displays
    private TextView tvQn;
    private TextView tvScore;
    private TextView tvQnCount;
    private TextView countdown;
    private RadioGroup rbgroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    private Button button_confirm_next;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private Long timeLeftMillis;

    private List<Question> questionList;

    private int questionCounter;
    private int questionCountTotal;

    private Question currentQuestion;

    private int score;

    private Boolean answered;

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);

        countdown = findViewById(R.id.clock);
        tvQn = findViewById(R.id.display_equation);
        tvScore = findViewById(R.id.score_counter);
        tvQnCount = findViewById(R.id.qnwhat_qnno);
        rbgroup = findViewById(R.id.rb_group);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        button_confirm_next = findViewById(R.id.btn_confirm_next);

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = countdown.getTextColors();

        QuizDBHelper dbHelper = new QuizDBHelper(this);
        questionList = dbHelper.getAllQuestions();

        questionCountTotal = questionList.size();
        Collections.shuffle(questionList);

        showNextQuestion();

        button_confirm_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    }
                   else {
                        Toast.makeText(QuizActivity.this, "Please Select an Answer", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });
        prefs = getSharedPreferences("quizprefs", MODE_PRIVATE);
        editor = getSharedPreferences("quizprefs", MODE_PRIVATE).edit();
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbgroup.clearCheck();

        if(questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            tvQn.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            questionCounter++;
            tvQnCount.setText("Question: " +questionCounter + "/" +questionCountTotal);
            answered = false;
            button_confirm_next.setText("Confirm");
            timeLeftMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        }
        else if(questionCounter == questionCountTotal) {
            finishQuiz();
        }
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
                checkAnswer();
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

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(rbgroup.getCheckedRadioButtonId());
        int answerNum = rbgroup.indexOfChild(rbSelected) +1;
        if(answerNum == currentQuestion.getAnswerNum()) {
            score++;
            tvScore.setText("Score: "+score);
        }

        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNum()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                tvQn.setText("Option 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                tvQn.setText("Option 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                tvQn.setText("Option 3 is correct");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                tvQn.setText("Option 4 is correct");
                break;
        }

        if(questionCounter < questionCountTotal) {
           button_confirm_next.setText("Next");
        }
        else {
            button_confirm_next.setText("Finish");
        }
    }

    private void finishQuiz() {
        String highscore = prefs.getString("Score", "0");
        if(Integer.parseInt(highscore) < score) {
            editor.putString("Score", String.valueOf(score));
            editor.apply();
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        }
        else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer !=null) {
            countDownTimer.cancel();
        }
    }
}