package com.example.home_security_system_app;

import static android.Manifest.permission_group.CAMERA;

import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.os.Build;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Collections;

// https://www.youtube.com/watch?v=bEhqGpI0kew&t=377s
public class CameraActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 200;

    private CameraOnCommand camCMD;
    private TextureView textureView;
    private TextView cameraStatus;
    private CameraManager cameraManager;
    private CameraDevice myCameraDevice;
    private CameraCaptureSession myCameraCaptureSession;
    private CaptureRequest.Builder captureRequestBuilder;
    private boolean isSetupDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        textureView = findViewById(R.id.cameraFeedView);
        cameraStatus = findViewById(R.id.cameraStatus);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        Camera cam = new Camera(cameraStatus);
        camCMD = new CameraOnCommand(cam);
    }

    public void setupButton(View view) {
        if (ContextCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {

            camCMD.executeSetup(view);
            isSetupDone = true;
        }
    }

    // https://clouddevs.com/android/camera-api/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                View view = findViewById(R.id.setupButton);
                camCMD.executeSetup(view);
                isSetupDone = true;
            } else {
                cameraStatus.setText("Please allow your permission!");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void buttonStartCamera(View view) {
        if (!isSetupDone) {
            cameraStatus.setText("Please setup camera first.");
            return;
        }

        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        Surface surface = new Surface(surfaceTexture);

        try {
            camCMD.executeOn(view);
            captureRequestBuilder = myCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            OutputConfiguration outputConfiguration = new OutputConfiguration(surface);

            SessionConfiguration sessionConfiguration = new SessionConfiguration(SessionConfiguration.SESSION_REGULAR,
                    Collections.singletonList(outputConfiguration),
                    getMainExecutor(),
                    new CameraCaptureSession.StateCallback() {
                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                            myCameraCaptureSession = cameraCaptureSession;
                            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                                    CameraMetadata.CONTROL_MODE_AUTO);

                            try {
                                myCameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null);
                            } catch (CameraAccessException e) {
                                throw new RuntimeException(e);
                            }

                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                            myCameraCaptureSession = null;
                        }
                    }
            );

            myCameraDevice.createCaptureSession(sessionConfiguration);

        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public void buttonStopCamera(View view) {
        try {
            camCMD.executeOff(view);
            myCameraCaptureSession.abortCaptures();
        } catch (Exception e) {
            cameraStatus.setText("Failed to stop camera:");
        }
    }
}
