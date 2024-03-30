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
public class CameraActivity extends AppCompatActivity {


    private CameraOnCommand camCMD;

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

    public void setupButton(View view) {
            camCMD.executeSetup(view);
    }

    // https://clouddevs.com/android/camera-api/

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void buttonStartCamera(View view) {
        camCMD.executeOn(view);
    }

    public void buttonStopCamera(View view) {
        camCMD.executeOff(view);
    }
}
