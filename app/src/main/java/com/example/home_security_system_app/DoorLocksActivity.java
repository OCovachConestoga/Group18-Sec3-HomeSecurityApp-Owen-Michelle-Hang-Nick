package com.example.home_security_system_app;

import static android.Manifest.permission.CAMERA;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class DoorLocksActivity extends AppCompatActivity {

    private TextView doorLockDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doorlocks);

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
