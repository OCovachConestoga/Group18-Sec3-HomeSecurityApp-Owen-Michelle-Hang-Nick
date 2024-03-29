package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Alarms {

    // Create variable that are used by this class
    private final TextView alarmDisplay;
    private boolean isSetup = false; // default to false when first loading up

    //Parameterized Constructor
    Alarms(TextView textView){
        alarmDisplay = textView;
    }

    // Function to simulate setting up Alarms
    @SuppressLint("SetTextI18n")
    public void setup(View view){

        // create a temp button to access its functions
        Button tempSetupButton = view.findViewById(R.id.setupButton);

        // Initialize a temp string to compare string values
        String btnText = tempSetupButton.getText().toString();

        // toggle button functionality
        if(btnText.equals("Setup"))
        {
            tempSetupButton.setText("Disconnect");
            alarmDisplay.setText("Alarms Setup \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            alarmDisplay.setText("Alarms Disconnected \\o/");
        }

        // set the setup value to the opposite of what it was
        isSetup = !isSetup;
    }


    // Function to simulate Turning Alarms On
    @SuppressLint("SetTextI18n")
    public void turnon(View view){
        // Checks if the system is setup or not
        if(isSetup)
            alarmDisplay.setText("Alarms Activated \\o/");
        else
            alarmDisplay.setText("No Alarms to turn on /o\\");

    }

    // Function to simulate Turning Alarms Off
    @SuppressLint("SetTextI18n")
    public void turnoff(View view){
        // Checks if the system is setup or not
        if(isSetup)
            alarmDisplay.setText("Alarms Deactivated \\o/");
        else
            alarmDisplay.setText("No Alarms to turn off /o\\");

    }
}