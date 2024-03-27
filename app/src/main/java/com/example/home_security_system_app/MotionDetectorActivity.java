package com.example.home_security_system_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MotionDetectorActivity extends AppCompatActivity {

    private TextView motionDetectorDisplay;
    private Button tempSetupButton;

    private boolean isSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_motiondetector);

        motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("No Motion Detector Setup /o\\");
    }

    public void buttonSetup(View view){
        tempSetupButton = findViewById(R.id.setupButton);
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);

        String btnText = tempSetupButton.getText().toString();

        if(btnText.equals("Setup"))
        {
            tempSetupButton.setText("Disconnect");
            motionDetectorDisplay.setText("Motion Detector Setup \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            motionDetectorDisplay.setText("Motion Detector Disconnected \\o/");
        }

        isSetup = !isSetup;
    }

    public void buttonStartDetecting(View view){
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);

        if(isSetup)
            motionDetectorDisplay.setText("Started Detecting \\o/");
        else
            motionDetectorDisplay.setText("No Motion Detector to Start /o\\");
    }

    public void buttonStopDetecting(View view){
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);

        if(isSetup)
            motionDetectorDisplay.setText("Stopped Detecting \\o/");
        else
            motionDetectorDisplay.setText("No Motion Detector to Stop /o\\");
    }

}
