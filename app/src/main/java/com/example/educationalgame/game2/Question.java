package com.example.educationalgame.game2;

import java.util.Random;

public class Question {
    public int number1;
    public int number2;
    public String operation;
    public static String operations[];
    public Random prng;

    public Question(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.prng = new Random();
        this.operations = new String[] {"+", "-", "*", "/"};
        this.operation = "+";
    }

}
