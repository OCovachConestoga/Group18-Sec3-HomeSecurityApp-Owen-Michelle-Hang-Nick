package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Android activity class that handles gui inputs for the application
 */
public class DoorLocksActivity extends AppCompatActivity {

    /**
     * attribute to hold an instance of the doorlocks concrete command
     */
    private DoorLocksOnCommand dlCMD;

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
        setContentView(R.layout.activity_doorlocks);

        // Creates a local TextView to pass to the DoorLocks object
        TextView doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("No DoorLocks Setup /o\\");

        // Initialize Firebase DB
        // Firebase Database reference
        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

        // Make the text input invisible
        EditText pinText = findViewById(R.id.setLockPinText);
        pinText.setVisibility(View.INVISIBLE);

        // Create the necessary instances of the system for the command design pattern
        DoorLocks dl = new DoorLocks(doorLockDisplay, pinText, myDatabase, this);
        dlCMD = new DoorLocksOnCommand(dl);
    }

    /**
     * Function to simulate setting up DoorLocks
     * @param view holds the current window view
     */
    public void buttonSetup(View view){
        // Run the door locks command execute function
        dlCMD.executeSetup(view);
    }

    /**
     * Handles the setLockPin button click
     * @param view holds the current window view
     */
    public void buttonSetLockPin(View view){

        // Run the door locks extra function
        dlCMD.setLockPin();
    }
    /**
     * Handles the lock doors button click
     * @param view holds the current window view
     */
    public void buttonLockDoors(View view){

        // Run the door locks command execute function
        dlCMD.executeOn();
    }

    /**
     * Handles the unlock doors button click
     * @param view holds the current window view
     */
    public void buttonUnlockDoors(View view){

        // Run the door locks command execute function
        dlCMD.executeOff();
    }
}
