package com.example.home_security_system_app;

import static android.Manifest.permission_group.CAMERA;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

// https://www.youtube.com/watch?v=bEhqGpI0kew&t=377s
/**
 * Android activity class that handles gui inputs for the application
 */
public class CameraActivity extends AppCompatActivity {

    /**
     * attribute to hold an instance of the camera concrete command
     */
    private CameraOnCommand camCMD;

    /**
     * This function is a default constructor for this class, it initializes everything needed for this class
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ActivityCompat.requestPermissions(this,
                new String[]{CAMERA},
                PackageManager.PERMISSION_GRANTED);

        TextureView cameraDisplay = findViewById(R.id.cameraFeedView);
        TextView cameraStatus = findViewById(R.id.cameraStatus);
        CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        Camera cam = new Camera(cameraStatus, cameraDisplay, cameraManager, this);
        camCMD = new CameraOnCommand(cam);
    }

    /**
     * Function to simulate setting up Camera
     * @param view holds the current window view
     */
    public void setupButton(View view) {
            camCMD.executeSetup(view);
    }

    // https://clouddevs.com/android/camera-api/
    /**
     * Handles the start alarm button click
     * @param view holds the current window view
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    public void buttonStartCamera(View view) {
        camCMD.executeOn();
    }

    /**
     * Handles the stop alarm button click
     * @param view holds the current window view
     */
    public void buttonStopCamera(View view) {
        camCMD.executeOff();
    }
}
