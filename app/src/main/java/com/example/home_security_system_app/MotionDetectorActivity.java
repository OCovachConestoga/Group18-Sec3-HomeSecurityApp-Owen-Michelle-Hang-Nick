package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Android activity class that handles gui inputs for the application
 */
public class MotionDetectorActivity extends AppCompatActivity {

    /**
     * attribute to hold an instance of the motion detector concrete command
     */
    private MotionDetectorOnCommand mdCMD;


    /**
     * This function is a default constructor for this class, it initializes everything needed for this class
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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

    /**
     * Handles the setup button click
     * @param view holds the current window view
     */
    public void setupButton(View view) {

        // Run the door locks command execute function
        mdCMD.executeSetup(view);
    }

    /**
     * Handles the start detecting button click
     * @param view holds the current window view
     */
    public void buttonStartDetecting(View view){

            // Run the door locks command execute function
            mdCMD.executeOn();
    }

    /**
     * Handles the stop detecting button click
     * @param view holds the current window view
     */
    public void buttonStopDetecting(View view){

            // Run the door locks command execute function
            mdCMD.executeOff();

    }


}
