package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This class handles the Android studio GUI interactions and bridges to the command design pattern
public class DoorLocksActivity extends AppCompatActivity {

    // Create variable that are used by this class
    private DoorLocksOnCommand dlCMD;

    // Function that handles loading the view
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

    // Handles the setup button click
    public void buttonSetup(View view){
        // Run the door locks command execute function
        dlCMD.executeSetup(view);
    }

    // Handles the setLockPin button click
    public void buttonSetLockPin(View view){

        // Run the door locks extra function
        dlCMD.setLockPin(view);
    }

    // Handles the lock doors button click
    public void buttonLockDoors(View view){

        // Run the door locks command execute function
        dlCMD.executeOn(view);
    }

    // Handles the unlock doors button click
    public void buttonUnlockDoors(View view){

        // Run the door locks command execute function
        dlCMD.executeOff(view);
    }
}
