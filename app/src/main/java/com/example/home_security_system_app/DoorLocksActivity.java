package com.example.home_security_system_app;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DoorLocksActivity extends AppCompatActivity {

    private TextView doorLockDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doorlocks);

        doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("No DoorLocks Setup /o\\");
    }

    public void buttonSetup(View view){
        doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("DoorLocks Setup \\o/");
    }

    public void buttonLockDoors(View view){
        doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("DoorLocks Locked \\o/");
    }

    public void buttonUnlockDoors(View view){
        doorLockDisplay = findViewById(R.id.doorLocksView);
        doorLockDisplay.setText("DoorLocks Unlocked \\o/");
    }
}
