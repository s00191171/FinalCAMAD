package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity {

    TextView Score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        getSupportActionBar().hide();

        Score =  findViewById(R.id.PalyerScore);

        int Uscore = getIntent().getIntExtra("score",-1);

        Score.setText(String.valueOf("Your Score is: " + Uscore));
    }

    public void PlayAgain(View view) {
        Intent i = new Intent(MainActivity5.this, MainActivity2.class);
        startActivity(i);
    }

    public void HighScore(View view) {
        Intent i = new Intent(MainActivity5.this, MainActivity6.class);
        startActivity(i);
    }
}