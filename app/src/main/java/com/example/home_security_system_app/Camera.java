package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



// Class that defines the Camera system
public class Camera{

    // Create variable that are used by this class
    private final TextView CameraDisplay;
    private boolean isSetup = false; // default to false when first loading up


    //Parameterized Constructor
    Camera(TextView textView){
        CameraDisplay = textView;
    }

    // Function to simulate setting up light
    @SuppressLint("SetTextI18n")
    public void setup(View view){
        Button tempSetupButton = view.findViewById(R.id.setupButton);

        String btnText = tempSetupButton.getText().toString();

        if(btnText.equals("Setup"))
        {
            tempSetupButton.setText("Disconnect");
            CameraDisplay.setText("Camera Set Up \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            CameraDisplay.setText("Camera Disconnected \\o/");
        }

        isSetup = !isSetup;
    }

    // Function to simulate turning on the Camera
    @SuppressLint("SetTextI18n")
    public void on(View view){ // on was start

        // Checks if the system is setup or not
        if(isSetup)
            CameraDisplay.setText("Camera is Turned On \\o/");
        else
            CameraDisplay.setText("No Camera to Turn On /o\\");
    }

    // Function to simulate turning off the Camera
    @SuppressLint("SetTextI18n")
    public void off(View view){ // was stop now off

        // Checks if the system is setup or not
        if(isSetup)
            CameraDisplay.setText("Camera is Turned Off \\o/");
        else
            CameraDisplay.setText("No Camera to turn off /o\\");
    }


}
