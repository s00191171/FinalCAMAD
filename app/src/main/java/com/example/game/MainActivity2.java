package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.stream.IntStream;

public class MainActivity2 extends AppCompatActivity {

    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    Animation anim;

    StringBuilder sb;
    Button Colour1, Colour2, Colour3, Colour4, Play;


    int[] colorArray = new int[4];
    int sequenceCount = 4, n = 0;
    int[] gameSequence = new int[120];
    int arrayIndex = 0;

    CountDownTimer ct = new CountDownTimer(6000,  1500) {

        public void onTick(long millisUntilFinished) {
           // mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        public void onFinish() {
           // mTextField.setText("done!");
            // we now have the game sequence

            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
            // start next activity

            // put the sequence into the next activity
            Intent i = new Intent(MainActivity2.this, MainActivity3.class);
            i.putExtra("numbers", gameSequence);
            i.putExtra("colorArray", colorArray);
            startActivity(i);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        Colour1 = findViewById(R.id.Colour1);
        Colour2 = findViewById(R.id.Colour2);
        Colour3 = findViewById(R.id.Colour3);
        Colour4 = findViewById(R.id.Colour4);
        Play = findViewById(R.id.Play);

        for (int i = 0; i < 4; i++) {
            Random r = new Random();
            int red = r.nextInt(256);
            int green = r.nextInt(256);
            int blue = r.nextInt(256);
            int color = Color.rgb(red, green, blue);
            final int finalColor = color;
            boolean contains = IntStream.of(colorArray).anyMatch(x -> x == finalColor);
            if (contains) {
                r = new Random();
                red = r.nextInt(256);
                green = r.nextInt(256);
                blue = r.nextInt(256);
                color = Color.rgb(red, green, blue);
            }
            colorArray[i] = color;
        }
        Colour1 = findViewById(R.id.Colour1);
        Colour2 = findViewById(R.id.Colour2);
        Colour3 = findViewById(R.id.Colour3);
        Colour4 = findViewById(R.id.Colour4);
        Colour1.setBackgroundColor(colorArray[0]);
        Colour2.setBackgroundColor(colorArray[1]);
        Colour3.setBackgroundColor(colorArray[2]);
        Colour4.setBackgroundColor(colorArray[3]);
    }

    private void oneButton() {
        n = getRandom(sequenceCount);
        sb.append(String.valueOf(n) + ", ");
        Toast.makeText(this, "Number = " + n, Toast.LENGTH_SHORT).show();

        switch (n) {
            case 1:
                flashButton(Colour1);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(Colour2);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(Colour3);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(Colour4);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;
        }   // end switch
    }

    private void flashButton(Button button) {

        anim = new AlphaAnimation(1,0);
        anim.setDuration(1000); //You can manage the blinking time with this parameter

        anim.setRepeatCount(0);
        button.startAnimation(anim);

    }

    //
    // return a number between 1 and maxValue
    private int getRandom(int maxValue) {

        return ((int) ((Math.random() * maxValue) + 1));
    }


    public void StartGame2(View view) {

        sb = new StringBuilder("Result : ");
        ct.start();
    }
}