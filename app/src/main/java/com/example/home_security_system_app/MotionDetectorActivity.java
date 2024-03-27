package com.example.home_security_system_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MotionDetectorActivity extends AppCompatActivity {

    private TextView motionDetectorDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doorlocks);

    }

    public void buttonSetup(View view){
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("Motion Detector Setup \\o/");
    }

    public void buttonStartDetecting(View view){
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("Started Detecting \\o/");
    }

    public void buttonStopDetecting(View view){
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("Stopped Detecting \\o/");
    }

}
