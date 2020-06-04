package com.example.educationalgame;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.educationalgame.SciQuiz.LoadingActivity;
import com.example.educationalgame.WordDict.DictionaryActivity;


public class GameSelectionFragment extends Fragment {

    private Button firstGame;
    private Button secondGame;
    private Button thirdGame;
    private Button fourthGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // For the First Game (Operation What..!!)
        firstGame = view.findViewById(R.id.first_game);
        firstGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Log.d("FirstGameButton", "Clicked");
                NavHostFragment.findNavController(GameSelectionFragment.this)
                        .navigate(R.id.action_gameSelectionFragment_to_game2Fragment);
            }
        });
        //For Second Game (Word Dictionary)
        secondGame = view.findViewById(R.id.second_game);
        secondGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), LoadingActivity.class);
                startActivity(myIntent);
            }
        });
        // For the Third Game Word Dictionary
        thirdGame = view.findViewById(R.id.third_game);
        thirdGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(getContext(), DictionaryActivity.class);
                startActivity(myintent);
            }
        });
        // For the Fourth Game Sum them Up..
        fourthGame = view.findViewById(R.id.fourth_game);
        fourthGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(GameSelectionFragment.this)
                        .navigate(R.id.action_gameSelectionFragment_to_game1Fragment);
            }
        });
    }
}