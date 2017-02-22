package com.example.ramsai.eggtimerapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.ramsai.eggtimerapp.R.id.button;
import static com.example.ramsai.eggtimerapp.R.id.timerTextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView timerTextView;
    Button controllerButton;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        timerTextView.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft){
        int minutes = (int) secondsLeft/60;
        int seconds = secondsLeft - minutes * 60;
        String secondString = Integer.toString(seconds);
        if(seconds <= 9){

            secondString="0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" + secondString);


    }

    public void controlTimer(View view){

        if(counterIsActive == false){

        counterIsActive = true;
        seekBar.setEnabled(false);
            controllerButton.setText("Stop");

        countDownTimer =new CountDownTimer(seekBar.getProgress()*1000+100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                resetTimer();
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(),R.raw.thunder);
                mplayer.start();

            }
        }.start();

        }else{
            resetTimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        controllerButton = (Button)findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
