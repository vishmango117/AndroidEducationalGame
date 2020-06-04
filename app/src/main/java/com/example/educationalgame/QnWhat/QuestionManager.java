package com.example.educationalgame.QnWhat;

import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    public ArrayList<Question> questions;
    private int size;
    private Random prng;

    public QuestionManager() {
        this.questions = new ArrayList<>();
        prng = new Random();
        this.size = 0;
    }

}
