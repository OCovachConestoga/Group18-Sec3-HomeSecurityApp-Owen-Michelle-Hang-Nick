package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

// Class that defines the MotionDetector system
public class MotionDetector{

    // Create variable that are used by this class
    private final TextView motionDetectorDisplay;

    // Firebase Database reference
    private final DatabaseReference myDatabase;

    private final Context context;


    // Check startButton detection
    private boolean isDetectionStarted = false;

    private boolean isSetup = false; // default to false when first loading up


    //Parameterized Constructor
    MotionDetector(TextView textView, DatabaseReference database, Context context){
        this.motionDetectorDisplay = textView;
        this.myDatabase = database;
        this.context = context;
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
        {
            motionDetectorDisplay.setText("Started Detecting \\o/");
            myDatabase.child("MOTION_Mode").setValue("ON");
            isDetectionStarted = true;
            // Listen for any changes in MOTION_State from Firebase
            motionStateListener();
        }
        else
        {
            motionDetectorDisplay.setText("No Motion Detector to Start /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to simulate stopping the motion detection
    @SuppressLint("SetTextI18n")
    public void stop(View view){

        // Checks if the system is setup or not
        if(isSetup)
        {
            motionDetectorDisplay.setText("Stopped Detecting \\o/");
            myDatabase.child("MOTION_Mode").setValue("OFF");
            isDetectionStarted = false;
        }
        else
        {
            motionDetectorDisplay.setText("No Motion Detector to Stop /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }
    }



    private void motionStateListener()
    {

        if(!isSetup || !isDetectionStarted)
        {
            return;
        }

        myDatabase.child("MOTION_State").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String motionState = snapshot.getValue(String.class);

                motionDetectorDisplay.setText("Motion State: " + (motionState != null ? motionState: "No motion detected"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
