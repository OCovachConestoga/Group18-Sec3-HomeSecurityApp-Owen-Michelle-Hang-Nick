package com.example.home_security_system_app;

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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.CAMERA;

import java.util.Collections;


// https://www.youtube.com/watch?v=bEhqGpI0kew&t=377s
public class CameraActivity extends AppCompatActivity {

    private TextureView textureView;
    private CameraCaptureSession myCameraCaptureSession;
    private CameraManager cameraManager;
    private CameraDevice myCameraDevice;
    private CaptureRequest.Builder captureRequestBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        ActivityCompat.requestPermissions(this,
                new String[]{CAMERA},
                PackageManager.PERMISSION_GRANTED);

        textureView = findViewById(R.id.cameraFeedView);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        startCamera();
    }

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            myCameraDevice = cameraDevice;
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            myCameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            myCameraDevice.close();
            myCameraDevice = null;
        }
    };

    private void startCamera() {
        try {
            String stringCameraID = cameraManager.getCameraIdList()[1];

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            cameraManager.openCamera(stringCameraID, stateCallback, null);
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public void buttonStartCamera(View view)
    {
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        Surface surface = new Surface(surfaceTexture);

        try {
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

    public void buttonStopCamera(View view)
    {
        try {
            myCameraCaptureSession.abortCaptures();
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }

}