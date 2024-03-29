package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

// This class handles the Android studio GUI interactions and bridges to the command design pattern
public class MotionDetectorActivity extends AppCompatActivity {

    // Create variable that are used by this class
    private MotionDetectorOnCommand mdCMD;

    // Firebase Database reference
    private DatabaseReference myDatabase;

    // Display motion detector status back
    private TextView motionDetectorDisplay;

    // Check setup status
    private boolean isSetupDone = false;

    // Check startButton detection
    private boolean isDetectionStarted = false;

    // Function that handles loading the view
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Switches the view on the app to the proper view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motiondetector);

        // Initialize Firebase DB
        myDatabase = FirebaseDatabase.getInstance().getReference();

        // Creates a local TextView to pass to the MotionDetector object
        motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("No Motion Detector Setup /o\\");

        // Create the necessary instances of the system for the command design pattern
        MotionDetector md = new MotionDetector(motionDetectorDisplay);
        mdCMD = new MotionDetectorOnCommand(md);
    }

    // Handles the setup button click
    public void setupButton(View view) {

        // Run the door locks command execute function
        mdCMD.executeSetup(view);

        isSetupDone = true;
    }

    // Handles the lock doors button click
    public void buttonStartDetecting(View view){

        if(isSetupDone)
        {
            // Run the door locks command execute function
            mdCMD.executeOn(view);

            myDatabase.child("MOTION_Mode").setValue("ON");

            isDetectionStarted = true;

            // Listen for any changes in MOTION_State from Firebase
            motionStateListener();
        } else {
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }


    }

    // Handles the unlock doors button click
    public void buttonStopDetecting(View view){

        if(isSetupDone)
        {
            // Run the door locks command execute function
            mdCMD.executeOff(view);

            myDatabase.child("MOTION_Mode").setValue("OFF");

            isDetectionStarted = false;
        } else
        {
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this, "Please complete setup first.", Toast.LENGTH_SHORT).show();

        }

    }

    private void motionStateListener()
    {

        if(!isSetupDone || !isDetectionStarted)
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
