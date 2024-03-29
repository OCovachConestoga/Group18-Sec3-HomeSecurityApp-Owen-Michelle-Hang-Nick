package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// Class that defines the MotionDetector system
public class MotionDetector{

    // Create variable that are used by this class
    private final TextView motionDetectorDisplay;
    private boolean isSetup = false; // default to false when first loading up


    //Parameterized Constructor
    MotionDetector(TextView textView){
        motionDetectorDisplay = textView;
    }

    // Function to simulate setting up motion detectors
    @SuppressLint("SetTextI18n")
    public void setup(View view){
        Button tempSetupButton = view.findViewById(R.id.setupButton);

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

    // Function to simulate starting the motion detection
    @SuppressLint("SetTextI18n")
    public void start(View view){

        // Checks if the system is setup or not
        if(isSetup)
            motionDetectorDisplay.setText("Started Detecting \\o/");
        else
            motionDetectorDisplay.setText("No Motion Detector to Start /o\\");
    }

    // Function to simulate stopping the motion detection
    @SuppressLint("SetTextI18n")
    public void stop(View view){

        // Checks if the system is setup or not
        if(isSetup)
            motionDetectorDisplay.setText("Stopped Detecting \\o/");
        else
            motionDetectorDisplay.setText("No Motion Detector to Stop /o\\");
    }
}
