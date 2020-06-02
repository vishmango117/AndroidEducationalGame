package com.example.educationalgame.Game1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.educationalgame.R;

import java.util.ArrayList;
import java.util.Random;


public class Game1Fragment extends Fragment {

    private TextView ScoreDisplay;
    private ProgressBar countdownTimer;
    private CountDownTimer countDownTimer;
    private int i;

    private GridView answergrid;
    private ArrayAdapter<String> myvalues;
    private ButtonAdapter buttonGrid;
    private Random prng;

    private AlertDialog.Builder dialog;
    private AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        i = 0;

        ScoreDisplay = view.findViewById(R.id.score_display);

        dialog = new AlertDialog.Builder(getContext());
        dialog.setMessage("Start the Game");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                countDownTimer.start();
                dialog.cancel();
            }
        });
        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NavHostFragment.findNavController(Game1Fragment.this)
                        .navigate(R.id.action_game1Fragment_to_gameSelectionFragment);
            }
        });
        alertDialog = dialog.create();
        alertDialog.show();
        answergrid = view.findViewById(R.id.answer_grid);
        buttonGrid = new ButtonAdapter(getContext());
        refreshGrid();
        answergrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("GridButton", "Clicked LOL");
            }
        });
    }






    public void refreshGrid() {
        answergrid.clearAnimation();
        buttonGrid = new ButtonAdapter(getContext());
        answergrid.setAdapter(buttonGrid);
    }
}