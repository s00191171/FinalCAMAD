package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText EnterName;
    private Button StartGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        EnterName=findViewById(R.id.EnterName);
        StartGame=findViewById(R.id.StartGame);

        EnterName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                StartGame.setEnabled(true);
            }
        });
    }

    public void StartGame(View view) {
        Intent i = new Intent(MainActivity.this, MainActivity4.class);
        i.putExtra("EnterName", String.valueOf(EnterName));
        startActivity(i);
    }
}