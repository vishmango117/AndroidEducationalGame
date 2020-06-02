package com.example.educationalgame;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            // if you are redirecting from a fragment then use getActivity() as the context.
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Handler h = new Handler();
        h.postDelayed(r,2000);
        //After Finishing Loading stuff Loading the Main Menu Activity
    }
}
