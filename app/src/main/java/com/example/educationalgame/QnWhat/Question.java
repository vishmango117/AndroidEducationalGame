package com.example.educationalgame.QnWhat;

import java.util.Random;

public class Question {
    public int number1;
    public int number2;
    public String operation;
    public String operations[];
    public Random prng;
    public int answer;

    public Question(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
        this.prng = new Random();
        this.operations = new String[] {"+", "-", "*", "/"};
        this.operation = this.operations[prng.nextInt(4)];
    }

    public void getAnswer() {
        if(this.operation.equals("+")) {
            this.answer = this.number1 + this.number2;
        }
        else if(this.operation.equals("-")) {
            this.answer = this.number1 - this.number2;
        }
        else if(this.operation.equals("/")) {
            this.answer = this.number1 / this.number2;
        }
        else if(this.operation.equals("*")) {
            this.answer = this.number1 * this.number2;
        }
    }

}
