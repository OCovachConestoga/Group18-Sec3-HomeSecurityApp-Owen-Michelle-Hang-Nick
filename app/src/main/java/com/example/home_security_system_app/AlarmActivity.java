package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AlarmActivity extends AppCompatActivity {

    private AlarmsCommand almCMD;

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
        almCMD = new AlarmsCommand(alarms);

    }

    // Handles the setup button click
    public void buttonSetup(View view){
        // Run the door locks command execute function
        almCMD.executeSetup(view);
    }

    // Handles the Start Speaker button click
    public void buttonStartAlarm(View view){

        // Run the door locks command execute function
        almCMD.executeOn();
    }

    // Handles the unlock doors button click
    public void buttonStopAlarm(View view){

        // Run the door locks command execute function
        almCMD.executeOff();
    }
}