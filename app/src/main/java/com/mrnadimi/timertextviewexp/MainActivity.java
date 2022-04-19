package com.mrnadimi.timertextviewexp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mrnadimi.timertextview.TimerTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimerTextView timerTextView = findViewById(R.id.tt_text);
        timerTextView.setDateFormat("mm:ss");
        timerTextView.startTimer();
    }
}