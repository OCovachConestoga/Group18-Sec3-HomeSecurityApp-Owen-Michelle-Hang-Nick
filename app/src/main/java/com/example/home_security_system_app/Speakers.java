package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

/**
 * Receiver class that contains the functionality of the Alarm system
 */
public class Speakers{

    /**
     * Variable that holds the display of the actions
     */
    private final TextView speakerDisplay;

    /**
     * Variable that holds information about the application environment
     */
    private final Context context;

    /**
     * Variable that holds a Firebase reference
     */
    private final DatabaseReference myDatabase;

    /**
     * Variable that hold the state of being setup or not
     * Defaults to false when first loading up
     */
    private boolean isSetup = false;

    /**
     * Parameterized Constructor
     *
     * @param textView holds the display element
     * @param database hold reference to Firebase
     * @param context hold application environment
     */
   Speakers(TextView textView, DatabaseReference database, Context context){
       speakerDisplay = textView;
       myDatabase = database;
       this.context = context;
    }

    /**
     * Function to simulate setting up Speakers
     * @param view holds the current window view
     */
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
            myDatabase.child("SPEAKER_State").setValue("OFF");
            tempSetupButton.setText("Setup");
            speakerDisplay.setText("Speakers Disconnected \\o/");
        }

        // set the setup value to the opposite of what it was
        isSetup = !isSetup;
    }

    /**
     * Function to simulate Turning Speakers On
     */
    @SuppressLint("SetTextI18n")
    public void turnon(){
        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("SPEAKER_State").setValue("ON");
            speakerDisplay.setText("Speakers Turned On \\o/");
        }
        else
        {
            speakerDisplay.setText("No Speakers to turn on /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Function to simulate Turning Speakers Off
     */
    @SuppressLint("SetTextI18n")
    public void turnoff(){
        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("SPEAKER_State").setValue("OFF");
            speakerDisplay.setText("Speakers Turned Off \\o/");
        }

        else
        {
            speakerDisplay.setText("No Speakers to turn off /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }
}

