package com.example.game;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    int[] arrayB;
    Button Colour1, Colour2, Colour3, Colour4;
    TextView test, test2, test3;
    int score = 0;
    int[] GetData;
    int current =0;
    int round;
    int Wins;
    boolean correct = false;
    boolean choosing = false;
    private SensorManager mSensorManager;
    private Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        int[] colour = getIntent().getIntArrayExtra("colorArray");

        Bundle bundle = getIntent().getExtras();
        round = bundle.getInt("sequenceCount");

        arrayB = getIntent().getIntArrayExtra("numbers");

        Colour1 = findViewById(R.id.Colour1);
        Colour2 = findViewById(R.id.Colour2);
        Colour3 = findViewById(R.id.Colour3);
        Colour4 = findViewById(R.id.Colour4);

        test=findViewById(R.id.test);
        test2=findViewById(R.id.test2);
        test3=findViewById(R.id.test3);


        Colour1.setBackgroundColor(colour[0]);
        Colour2.setBackgroundColor(colour[1]);
        Colour3.setBackgroundColor(colour[2]);
        Colour4.setBackgroundColor(colour[3]);

        // choose the sensor you want
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        GetData = new int[arrayB.length];
    }

    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];


        if (x < 0f & !choosing) {

            GiveTime();
            GetData[current] = 2;
            checkIfCorrect();
            score = score + 4;
            current ++;

            if(!correct){
                // Go to Game over Screen
                Intent GameOver = new Intent(MainActivity3.this, MainActivity5.class);
                score = score + 0;
                GameOver.putExtra("score", score);
                startActivity(GameOver);
            }



        }

        else if (x > 0.8f & !choosing ) {

            GiveTime();
            GetData[current] = 4;
            checkIfCorrect();
            score = score + 4;
            current ++;
            if(!correct){
                test.setText("correct");
                // Go to Game over Screen
                Intent GameOver = new Intent(MainActivity3.this, MainActivity5.class);
                score = score + 0;
                GameOver.putExtra("score", score);
                startActivity(GameOver);
            }


        }

        if (y > 0.35f & !choosing ) {

            GiveTime();
            GetData[current] = 3;
            checkIfCorrect();
            score = score + 4;
            current ++;

            if(!correct){
                test.setText("correct");
                // Go to Game over Screen
                Intent GameOver = new Intent(MainActivity3.this, MainActivity5.class);
                score = score + 0;
                GameOver.putExtra("score", score);
                startActivity(GameOver);
            }


        }

        else if (y < -0.24f & !choosing ) {

            GiveTime();
            GetData[current] = 1;
            checkIfCorrect();
            current ++;
            score = score + 4;
            if(!correct){
                test.setText("correct");
                // Go to Game over Screen
                Intent GameOver = new Intent(MainActivity3.this, MainActivity5.class);
                score = score + 0;
                GameOver.putExtra("score", score);
                startActivity(GameOver);
            }

        }

    }

    public void GiveTime(){

        choosing = true;


        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                test2.setText("Your choice will be saved in.." + millisUntilFinished / 1000);
            }

            public void onFinish() {
                test3.setText("Please choose the next sequence");

                round--;

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        choosing = false;
                    }
                }, 2000); // Millisecond 1000 = 1 sec

            }
        }.start();

        if(round == 1){
            Wins = Wins + 2;
            Intent Sequece = new Intent(MainActivity3.this, MainActivity2.class);
            Sequece.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            Sequece.putExtra("gameRound",Wins);
            startActivity(Sequece);
        }

    }
    public void checkIfCorrect(){

        for(int i =0; i < GetData.length;i++){
            if(GetData[i] !=0) {
                if (GetData[i] == arrayB[i]) {
                    correct = true;

                } else {

                    correct = false;
                }
            }
            else {
                continue;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not used
    }

}