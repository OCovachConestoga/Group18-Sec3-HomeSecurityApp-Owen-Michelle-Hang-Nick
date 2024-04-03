package com.example.home_security_system_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 * Main class that handles the change clicking listeners for our app to work
 * which initializes our systems and program
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variable that holds a card for the camera button
     */
    CardView cameraCard;

    /**
     * Variable that holds a card for the doorlocks button
     */
    CardView doorLocksCard;

    /**
     * Variable that holds a card for the motion detector button
     */
    CardView motionDetectorCard;

    /**
     * Variable that holds a card for the light button
     */
    CardView lightCard;

    /**
     * Variable that holds a card for the alarm button
     */
    CardView alarmCard;

    /**
     * Variable that holds a card for the speaker button
     */
    CardView speakerCard;

    /**
     * This function is a default constructor for this class, it initializes everything needed for this class
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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
            Intent intent = new Intent(MainActivity.this, AlarmsActivity.class);
            startActivity(intent);
        });

        speakerCard.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SpeakersActivity.class);
            startActivity(intent);
        });

    }
}