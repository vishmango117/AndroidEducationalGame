package com.example.educationalgame;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class GameSelectionFragment extends Fragment {

    private Button firstGame;
    private Button secondGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // For the First Game
        firstGame = view.findViewById(R.id.first_game);
        firstGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("FirstGameButton", "Clicked");
                NavHostFragment.findNavController(GameSelectionFragment.this)
                        .navigate(R.id.action_gameSelectionFragment_to_game1Fragment);
            }
        });
        //For Second Game
        secondGame = view.findViewById(R.id.second_game);
        secondGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(GameSelectionFragment.this)
                        .navigate(R.id.action_gameSelectionFragment_to_game2Fragment);
            }
        });
    }
}