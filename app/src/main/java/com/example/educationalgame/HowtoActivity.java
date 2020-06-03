package com.example.educationalgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HowtoActivity extends AppCompatActivity {

    //For the Heading
    private TextView heading1;
    private TextView heading2;
    private TextView heading3;

    //For the Game Instructions
    private TextView instructions1;
    private TextView instructions2;
    private TextView instructions3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        heading1 = findViewById(R.id.heading1);
        heading2 = findViewById(R.id.heading2);
        heading3 = findViewById(R.id.heading3);

        instructions1 = findViewById(R.id.instructions1);
        instructions2 = findViewById(R.id.instructions2);
        instructions3 = findViewById(R.id.instructions3);

        heading1.setText("Sum Them Up");
        heading2.setText("Operation What..!!");
        heading3.setText("Science Quiz");

        instructions1.setText("Click on the numbers to sum them up to the target number");
        instructions2.setText("Find Which Operation gives the answer from the two numbers");
        instructions3.setText("A Quiz testing your abilities from Biology to Astronomy." +
                " Lets see how you fair");
    }
}