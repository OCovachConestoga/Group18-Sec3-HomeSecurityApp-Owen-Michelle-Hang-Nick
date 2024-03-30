package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Class that defines the Speakers system
public class Speakers{

    // Create variable that are used by this class
    private final TextView speakerDisplay;

    private final Context context;
    private boolean isSetup = false; // default to false when first loading up

    //Parameterized Constructor
   Speakers(TextView textView, Context context){
       speakerDisplay = textView;
       this.context = context;
    }

    // Function to simulate setting up Speakers
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
            speakerDisplay.setText("Speakers Setup \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            speakerDisplay.setText("Speakers Disconnected \\o/");
        }

        // set the setup value to the opposite of what it was
        isSetup = !isSetup;
    }


    // Function to simulate Turning Speakers On
    @SuppressLint("SetTextI18n")
    public void turnon(View view){
        // Checks if the system is setup or not
        if(isSetup)
            speakerDisplay.setText("Speakers Turned On \\o/");
        else
        {
            speakerDisplay.setText("No Speakers to turn on /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }

    // Function to simulate Turning Speakers Off
    @SuppressLint("SetTextI18n")
    public void turnoff(View view){
        // Checks if the system is setup or not
        if(isSetup)
            speakerDisplay.setText("Speakers Turned Off \\o/");
        else
        {
            speakerDisplay.setText("No Speakers to turn off /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }
}
