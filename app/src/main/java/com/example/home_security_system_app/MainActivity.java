package com.example.home_security_system_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView cameraCard;
    CardView doorLocksCard;
    CardView motionDetectorCard;
    CardView lightCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_main));

        cameraCard = findViewById(R.id.cameraCard);
        doorLocksCard = findViewById(R.id.doorLocksCard);
        motionDetectorCard = findViewById(R.id.motionDetectorCard);
        lightCard = findViewById(R.id.lightCard);

        cameraCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        doorLocksCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoorLocksActivity.class);
                startActivity(intent);
            }
        });

        motionDetectorCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MotionDetectorActivity.class);
                startActivity(intent);
            }
        });

        lightCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LightActivity.class);
                startActivity(intent);
            }
        });

    }
}