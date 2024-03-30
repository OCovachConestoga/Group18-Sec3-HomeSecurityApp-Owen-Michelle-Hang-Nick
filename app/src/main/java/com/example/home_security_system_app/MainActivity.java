package com.example.home_security_system_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView cameraCard;
    CardView doorLocksCard;
    CardView motionDetectorCard;
    CardView lightCard;
    CardView alarmCard;
    CardView speakerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));

        cameraCard = findViewById(R.id.cameraCard);
        doorLocksCard = findViewById(R.id.doorLocksCard);
        motionDetectorCard = findViewById(R.id.motionDetectorCard);
        lightCard = findViewById(R.id.lightCard);
        alarmCard = findViewById(R.id.alarmsCard);
        speakerCard = findViewById(R.id.speakerCard);


        cameraCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });

        doorLocksCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DoorLocksActivity.class);
            startActivity(intent);
        });

        motionDetectorCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MotionDetectorActivity.class);
            startActivity(intent);
        });

        lightCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LightActivity.class);
            startActivity(intent);
        });

        alarmCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
            startActivity(intent);
        });

        speakerCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SpeakerActivity.class);
            startActivity(intent);
        });

    }
}