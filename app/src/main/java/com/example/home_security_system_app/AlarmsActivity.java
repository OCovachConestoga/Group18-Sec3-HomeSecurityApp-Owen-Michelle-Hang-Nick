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
public class AlarmsActivity extends AppCompatActivity {

    /**
     * attribute to hold an instance of the alarms concrete command
     */
    private AlarmsOnCommand almCMD;

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
        setContentView(R.layout.activity_alarm);

        // Creates a local TextView to pass to the Alarms object
        TextView alarmDisplay = findViewById(R.id.alarmView);
        alarmDisplay.setText("No Speakers Setup /o\\");

        // Initialize Firebase DB
        // Firebase Database reference
        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

        // Create the necessary instances of the system for the command design pattern
        Alarms alarms = new Alarms(alarmDisplay, myDatabase, this);
        almCMD = new AlarmsOnCommand(alarms);

    }

    /**
     * Handles the setup button click
     * @param view holds the current window view
     */
    public void buttonSetup(View view){
        // Run the door locks command execute function
        almCMD.executeSetup(view);
    }

    /**
     * Handles the start camera button click
     * @param view holds the current window view
     */
    public void buttonStartAlarm(View view){

        // Run the door locks command execute function
        almCMD.executeOn();
    }

    /**
     * Handles the stop camera button click
     * @param view holds the current window view
     */
    public void buttonStopAlarm(View view){

        // Run the door locks command execute function
        almCMD.executeOff();
    }
}