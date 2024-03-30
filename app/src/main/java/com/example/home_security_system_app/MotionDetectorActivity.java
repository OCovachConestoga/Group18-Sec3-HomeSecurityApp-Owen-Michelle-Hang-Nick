package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This class handles the Android studio GUI interactions and bridges to the command design pattern
public class MotionDetectorActivity extends AppCompatActivity {

    // Create variable that are used by this class
    private MotionDetectorOnCommand mdCMD;


    // Function that handles loading the view
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Switches the view on the app to the proper view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motiondetector);

        // Initialize Firebase DB
        // Firebase Database reference
        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

        // Creates a local TextView to pass to the MotionDetector object
        // Display motion detector status back
        TextView motionDetectorDisplay = findViewById(R.id.motionDetectorView);
        motionDetectorDisplay.setText("No Motion Detector Setup /o\\");

        // Create the necessary instances of the system for the command design pattern
        MotionDetector md = new MotionDetector(motionDetectorDisplay, myDatabase, this);
        mdCMD = new MotionDetectorOnCommand(md);
    }

    // Handles the setup button click
    public void setupButton(View view) {

        // Run the door locks command execute function
        mdCMD.executeSetup(view);
    }

    // Handles the lock doors button click
    public void buttonStartDetecting(View view){

            // Run the door locks command execute function
            mdCMD.executeOn();
    }

    // Handles the unlock doors button click
    public void buttonStopDetecting(View view){

            // Run the door locks command execute function
            mdCMD.executeOff();

    }


}
