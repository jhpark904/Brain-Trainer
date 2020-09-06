package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView goTextView;
    androidx.gridlayout.widget.GridLayout answers;
    TextView timerTV;
    TextView questionTV;
    TextView scoreTV;
    CountDownTimer timer;
    TextView a0TV;
    TextView a1TV;
    TextView a2TV;
    TextView a3TV;
    TextView resultTV;
    Button restartButton;

    boolean goActive = true;
    int answer;
    int questionCount;
    int correctCount;
    boolean timeLeft = true;
    List<Integer> answerChoices = new ArrayList<Integer>();

    public void setTimer() {
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTV.setText(Long.toString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                restartButton.setVisibility(View.VISIBLE);
                resultTV.setText("Done!");
                timeLeft = false;
            }
        }.start();
    }

    public void restart(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            answer = 0;
            questionCount = 0;
            correctCount = 0;
            timeLeft = true;

            restartButton.setVisibility(View.INVISIBLE);
            this.resultTV.setVisibility(View.INVISIBLE);

            this.timer.cancel();
            this.setTimer();
            this.answer = this.setQuestion();
            this.setChoices();
            this.setScore();

        }
    }

    public int setQuestion() {
        int firstNum = (int) (Math.random() * 20) + 1;
        int secondNum = (int) (Math.random() * 20) + 1;
        questionTV.setText(firstNum + " + " + secondNum );
        return firstNum + secondNum;
    }

    public void setChoices() {
        answerChoices.clear();
        answerChoices.add(this.answer);
        while (answerChoices.size() < 4) {
            while (true) {
                int a1 = (int) (Math.random() * 20) + 1;
                int a2 = (int) (Math.random() * 20) + 1;
                int num = a1 + a2;
                if (!answerChoices.contains(num)) {
                    answerChoices.add(num);
                    break;
                }
            }
            System.out.println(answerChoices);
            System.out.println(answer);
        }

        Collections.shuffle(answerChoices);
        a0TV.setText(Integer.toString(answerChoices.get(0)));
        a1TV.setText(Integer.toString(answerChoices.get(1)));
        a2TV.setText(Integer.toString(answerChoices.get(2)));
        a3TV.setText(Integer.toString(answerChoices.get(3)));

    }

    public void setScore() {
        scoreTV.setText(correctCount + "/" + questionCount);
    }

    public void compareAnswer(View view) {
        if (!goActive && timeLeft) {
            if (this.answerChoices.get(Integer.parseInt(view.getTag().toString())) == this.answer) {
                correctCount++;
                questionCount++;
                resultTV.setText("Correct");

            } else {
                questionCount++;
                resultTV.setText("Wrong");
            }
            this.resultTV.setVisibility(View.VISIBLE);
            this.setScore();
            this.answer = this.setQuestion();
            this.setChoices();

        }
    }


    public void go(View view) {
        if (goActive) {
            goActive = false;
            goTextView.setVisibility(View.INVISIBLE);
            answers.setVisibility(View.VISIBLE);
            timerTV.setVisibility(View.VISIBLE);
            questionTV.setVisibility(View.VISIBLE);
            scoreTV.setVisibility(View.VISIBLE);

            this.setTimer();
            this.answer = this.setQuestion();
            this.setChoices();
            this.setScore();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goTextView = findViewById(R.id.goTextView);
        answers = findViewById(R.id.answerGrid);
        timerTV = findViewById(R.id.timerTextView);
        questionTV = findViewById(R.id.questionTextView);
        scoreTV = findViewById(R.id.scoreTextView);
        a0TV = findViewById(R.id.answerTextView1);
        a1TV = findViewById(R.id.answerTextView2);
        a2TV = findViewById(R.id.answerTextView3);
        a3TV = findViewById(R.id.answerTextView4);
        resultTV = findViewById(R.id.resultTextView);
        restartButton = findViewById(R.id.restartButton);





    }
}