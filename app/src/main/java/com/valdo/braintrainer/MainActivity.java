package com.valdo.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {


    Button startBUtton;
    ArrayList<Integer>answers=new ArrayList<Integer>();
    int locationOfCOrrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView answertextView;
    TextView pointstetxtView;
    TextView timerTextView;
    Button bt3;
    Button bt4;
    Button bt5;
    Button bt6;
    Button playAgainButton;
    TextView sum;
    RelativeLayout gameRelativeLayout;

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        pointstetxtView.setText("0/0");
        answertextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                answertextView.setText("Your score is"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
            }
        }.start();


    }
    public void generateQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b= rand.nextInt(21);
        int c=a+b;
        sum.setText(Integer.toString(a)+"+"+Integer.toString(b));


        locationOfCOrrectAnswer=rand.nextInt(4);
        answers.clear();
        int incorrect;
        for(int i=0;i<=4;i++){
            if(i==locationOfCOrrectAnswer){
                answers.add(a+b);
            }else{
                incorrect=rand.nextInt(41);
                while(incorrect==a+b){
                    incorrect=rand.nextInt(41);
                }

                answers.add(incorrect);

            }
        }
        bt3.setText(Integer.toString(answers.get(0)));
        bt4.setText(Integer.toString(answers.get(1)));
        bt5.setText(Integer.toString(answers.get(2)));
        bt6.setText(Integer.toString(answers.get(3)));


    }

    public void startB(View view){

        startBUtton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
     public void chooseAnswer(View view) {


             if (Integer.toString(locationOfCOrrectAnswer).equals(view.getTag())) {
                 score++;
                 answertextView.setText("Correct!!");
                 pointstetxtView.setText(Integer.toString(score)+"/"+Integer.toString(0));

             }
              else {
                 answertextView.setText("Incorrect!!");
             }
         numberOfQuestions++;
         pointstetxtView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
         generateQuestion();
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        startBUtton=(Button)findViewById(R.id.goButton);
         sum=(TextView)findViewById(R.id.sumtextView3) ;
         bt3=(Button)findViewById(R.id.button3) ;
         bt4=(Button)findViewById(R.id.button4) ;
         bt5=(Button)findViewById(R.id.button5) ;
         bt6=(Button)findViewById(R.id.button6) ;
        answertextView=(TextView)findViewById(R.id.answertextView4) ;
        pointstetxtView=(TextView)findViewById(R.id.pointstetxtView2) ;
        timerTextView=(TextView)findViewById(R.id.timertextView) ;
        playAgainButton=(Button)findViewById(R.id.playAgainButton) ;
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout) ;


       
       // playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
