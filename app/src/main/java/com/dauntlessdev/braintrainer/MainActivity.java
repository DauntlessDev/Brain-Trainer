package com.dauntlessdev.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button[] buttonViews = new Button[4];TextView timerTextView;
    TextView scoreTextView;
    Button goButton;
    Button againButton;
    TextView questionTextView;
    TextView statusTextView;
    int score = 0;
    int numAnswered = 0;
    int randomAssignCorrect;

    public void goListener(View view){
        goButton.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        goButton.setEnabled(false);
        for(Button buttonView : buttonViews){
            buttonView.setVisibility(View.VISIBLE);
            buttonView.setEnabled(true);
        }
        startTimer();
        generateQuestions();
    }
    public void onclickButton(View view){
        String answer = view.getTag().toString().trim();
        numAnswered++;
        statusTextView.setText("Wrong!");

        if (answer.equals(String.valueOf(randomAssignCorrect).trim())){
            score++;
            statusTextView.setText("Correct!");
        }generateQuestions();
    }

    public void againListener(View view){
        startTimer();
        generateQuestions();
        score = 0;
        numAnswered = 0;
        scoreTextView.setText(score + "/" + numAnswered);
        statusTextView.setText("");
        againButton.setVisibility(View.INVISIBLE);
        againButton.setEnabled(false);
        for(Button buttonView : buttonViews){
            buttonView.setEnabled(true);
        }
    }

    public void startTimer(){
        new CountDownTimer(30000+100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText((millisUntilFinished/1000) + "s");
            }
            @Override
            public void onFinish() {
                statusTextView.setText("Done!");
                againButton.setVisibility(View.VISIBLE);
                againButton.setEnabled(true);
                for(Button buttonView : buttonViews){
                    buttonView.setEnabled(false);
                }
            }
        }.start();
    }

    public void generateQuestions(){
        Random random = new Random();
        int randomNum;
        int var1 = random.nextInt(30);
        int var2 = random.nextInt(30);
        questionTextView.setText(var1 + " + " + var2);
        int correctAnswer = var1 + var2;
        randomAssignCorrect = random.nextInt(4);
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(correctAnswer);
        buttonViews[randomAssignCorrect].setText(String.valueOf(correctAnswer));
        for (int i = 0; i < 4; i++){
            if(i == randomAssignCorrect) continue;

            randomNum = random.nextInt(55);
            while (integerArrayList.contains(randomNum)){
                randomNum = random.nextInt(55);
            }integerArrayList.add(randomNum);
            buttonViews[i].setText(String.valueOf(randomNum));
        }
        scoreTextView.setText(score + "/" + numAnswered);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);
        timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        statusTextView = findViewById(R.id.statusTextView);
        buttonViews[0] = findViewById(R.id.buttonView0);
        buttonViews[1] = findViewById(R.id.buttonView1);
        buttonViews[2] = findViewById(R.id.buttonView2);
        buttonViews[3] = findViewById(R.id.buttonView3);
        goButton = findViewById(R.id.goButton);
        againButton = findViewById(R.id.againButton);
    }
}