package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// Class that defines the DoorLocks system
public class DoorLocks{

    // Create variable that are used by this class
    private final TextView doorLockDisplay;
    private final EditText pinText;
    private final Context context;
    private boolean isSetup = false; // default to false when first loading up

    //Parameterized Constructor
    DoorLocks(TextView textView, EditText editText, Context context){
        doorLockDisplay = textView;
        pinText = editText;
        this.context = context;
    }

    // Function to simulate setting up doorlocks
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
            doorLockDisplay.setText("DoorLocks Setup \\o/");
        }
        else
        {
            tempSetupButton.setText("Setup");
            doorLockDisplay.setText("DoorLocks Disconnected \\o/");
        }

        // set the setup value to the opposite of what it was
        isSetup = !isSetup;
    }

    // Function to simulate the setup of a lock pin
    @SuppressLint("SetTextI18n")
    public void setLockPin(View view) {

        // checks if the DoorLock is setup
        if(isSetup)
        {
            // checks to see if the text box is visible or not
            if(pinText.getVisibility() == View.INVISIBLE)
            {
                // makes the lock pin text box visible to be able to input a pin
                doorLockDisplay.setText("Please Enter 4 Digit Pin");
                pinText.setVisibility(View.VISIBLE);
            }
            else
            {
                // makes the text box invisible so user doesn't see the text box all the time
                doorLockDisplay.setText("Pin Set \\o/");

                // if there is a system to actually set the pin to the input value, do so here

                pinText.setText("");
                pinText.setVisibility(View.INVISIBLE);
            }
        }
        else {
            doorLockDisplay.setText("No pin to set \\o/");
        }

    }

    // Function to simulate Locking doors
    @SuppressLint("SetTextI18n")
    public void lock(View view){

        // Checks if the system is setup or not
        if(isSetup)
            doorLockDisplay.setText("DoorLocks Locked \\o/");
        else
        {
            doorLockDisplay.setText("No DoorLocks to Lock /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }

    // Function to simulate Unlocking Doors
    @SuppressLint("SetTextI18n")
    public void unlock(View view){

        // Checks if the system is setup or not
        if(isSetup)
            doorLockDisplay.setText("DoorLocks Unlocked \\o/");
        else
        {
            doorLockDisplay.setText("No DoorLocks to Unlock /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }

    }
}
