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
public class Alarms {

    /**
     * Variable that holds the display of the actions
     */
    private final TextView alarmDisplay;

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
    Alarms(TextView textView, DatabaseReference database, Context context){
        alarmDisplay = textView;
        myDatabase = database;
        this.context = context;
    }

    /**
     * Function to simulate setting up Alarms
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
            alarmDisplay.setText("Alarms Setup \\o/");
        }
        else
        {
            myDatabase.child("ALARMS_State").setValue("OFF");
            tempSetupButton.setText("Setup");
            alarmDisplay.setText("Alarms Disconnected \\o/");
        }

        // set the setup value to the opposite of what it was
        isSetup = !isSetup;
    }

    /**
     * Function to simulate setting up Alarms
     */
    @SuppressLint("SetTextI18n")
    public void turnon(){
        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("ALARMS_State").setValue("ON");
            alarmDisplay.setText("Alarms Activated \\o/");
        }

        else
        {
            alarmDisplay.setText("No Alarms to turn on /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Function to simulate Turning Alarms Off
     */
    @SuppressLint("SetTextI18n")
    public void turnoff(){
        // Checks if the system is setup or not
        if(isSetup)
        {
            myDatabase.child("ALARMS_State").setValue("OFF");
            alarmDisplay.setText("Alarms Deactivated \\o/");
        }
        else
        {
            alarmDisplay.setText("No Alarms to turn off /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }
}
