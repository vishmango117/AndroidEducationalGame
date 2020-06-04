package com.example.educationalgame.SciQuiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educationalgame.R;


public class LoadingActivity extends AppCompatActivity {

    private Button button;
    private Button share;

    private TextView tvhighscore;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        button = findViewById(R.id.enter);
        tvhighscore = findViewById(R.id.highscore);
    }

    @Override
    protected void onStart() {
        super.onStart();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(LoadingActivity.this, QuizActivity.class);
                startActivityForResult(myintent, RESULT_FIRST_USER);
            }
        });
        prefs = getSharedPreferences("quizprefs", MODE_PRIVATE);
        editor = getSharedPreferences("quizprefs", MODE_PRIVATE).edit();

        tvhighscore.setText("Highscore: "+prefs.getString("Score", "0"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_FIRST_USER) {

        }
    }
}