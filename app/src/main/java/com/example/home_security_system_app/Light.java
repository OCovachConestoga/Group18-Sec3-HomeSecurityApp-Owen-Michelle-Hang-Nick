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
public class Light{

    /**
     * Variable that holds the display of the actions
     */
    private final TextView lightDisplay;

    /**
     * Variable that holds a Firebase reference
     */
    private final DatabaseReference myDatabase;

    /**
     * Variable that holds information about the application environment
     */
    private final Context context;

    /**
     * Variable that hold the state of being setup or not
     * Defaults to false when first loading up
     */
    private boolean isSetup = false; // default to false when first loading up


    /**
     * Parameterized Constructor
     *
     * @param textView holds the display element
     * @param database hold reference to Firebase
     * @param context hold application environment
     */
    Light(TextView textView, DatabaseReference database, Context context){
        this.lightDisplay = textView;
        this.myDatabase = database;
        this.context = context;
    }

    /**
     * Function to simulate setting up Lights
     * @param view holds the current window view
     */
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
            myDatabase.child("LED_State").setValue("OFF");
            tempSetupButton.setText("Setup");
            lightDisplay.setText("Light Disconnected \\o/");
        }

        isSetup = !isSetup;
    }

    /**
     * Function to simulate turning on the light
     */
    @SuppressLint("SetTextI18n")
    public void on(){
        // on was start

        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("LED_State").setValue("ON");
            lightDisplay.setText("Light is Turned On \\o/");
        }
        else
        {
            lightDisplay.setText("No Light to Turn On /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Function to simulate turning off the light
     */
    @SuppressLint("SetTextI18n")
    public void off(){
        // was stop now off

        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("LED_State").setValue("OFF");
            lightDisplay.setText("Light is Turned Off \\o/");
        }
        else
        {
            lightDisplay.setText("No Light to turn off /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }
    }
}
