package com.example.home_security_system_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DoorLocksActivity extends AppCompatActivity {

    private TextView doorLockDisplay;
    private Button tempSetupButton;
    private boolean isSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doorlocks);

        doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("No DoorLocks Setup /o\\");
    }

    public void buttonSetup(View view){

        tempSetupButton = findViewById(R.id.setupButton);
        doorLockDisplay = findViewById(R.id.doorLocksView);

        String btnText = tempSetupButton.getText().toString();

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

        isSetup = !isSetup;
    }

    public void buttonLockDoors(View view){

        doorLockDisplay = findViewById(R.id.doorLocksView);
        if(isSetup)
            doorLockDisplay.setText("DoorLocks Locked \\o/");
        else
            doorLockDisplay.setText("No DoorLocks to Lock /o\\");
    }

    public void buttonUnlockDoors(View view){
        doorLockDisplay = findViewById(R.id.doorLocksView);
        if(isSetup)
            doorLockDisplay.setText("DoorLocks Unlocked \\o/");
        else
            doorLockDisplay.setText("No DoorLocks to Unlock /o\\");
    }
}
