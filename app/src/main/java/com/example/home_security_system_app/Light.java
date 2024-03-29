package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



// Class that defines the Light system
public class Light{

    // Create variable that are used by this class
    private final TextView lightDisplay;
    private boolean isSetup = false; // default to false when first loading up


    //Parameterized Constructor
    Light(TextView textView){
        lightDisplay = textView;
    }

    // Function to simulate setting up light
    @SuppressLint("SetTextI18n")
    public void setup(View view){
        Button tempSetupButton = view.findViewById(R.id.setupButton);

        String btnText = tempSetupButton.getText().toString();

        if(btnText.equals("Setup"))
        {
            tempSetupButton.setText("Disconnect");
            lightDisplay.setText("Light Set Up \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            lightDisplay.setText("Light Disconnected \\o/");
        }

        isSetup = !isSetup;
    }

    // Function to simulate turning on the light
    @SuppressLint("SetTextI18n")
    public void on(View view){ // on was start

        // Checks if the system is setup or not
        if(isSetup)
            lightDisplay.setText("Light is Turned On \\o/");
        else
            lightDisplay.setText("No Light to Turn On /o\\");
    }

    // Function to simulate turning off the light
    @SuppressLint("SetTextI18n")
    public void off(View view){ // was stop now off

        // Checks if the system is setup or not
        if(isSetup)
            lightDisplay.setText("Light is Turned Off \\o/");
        else
            lightDisplay.setText("No Light to turn off /o\\");
    }

    // Function to simulate setting up the light timer
    @SuppressLint("SetTextI18n")
    public void setTimer(View view){

        // Checks if the system is setup or not
        if(isSetup)
            lightDisplay.setText("Light Timer is Set Up \\o/");
        else
            lightDisplay.setText("No Light to Turn On /o\\");
    }

}