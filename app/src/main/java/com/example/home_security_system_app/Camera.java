package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.Collections;


// Class that defines the Camera system
public class Camera{

    // Create variable that are used by this class
    private final TextView CameraStatus;

    private final TextureView CameraDisplay;

    private final Context context;

    private CameraDevice myCameraDevice;

    private final CameraManager cameraManager;

    private CameraCaptureSession myCameraCaptureSession;

    private CaptureRequest.Builder captureRequestBuilder;

    private boolean isSetup = false; // default to false when first loading up


    //Parameterized Constructor
    Camera(TextView textView, TextureView textureView, CameraManager cameraManager, Context context){
        this.CameraStatus = textView;
        this.CameraDisplay = textureView;
        this.cameraManager = cameraManager;
        this.context = context;
    }

    // Function to simulate setting up light
    @SuppressLint("SetTextI18n")
    public void setup(View view){
        Button tempSetupButton = view.findViewById(R.id.setupButton);

        String btnText = tempSetupButton.getText().toString();

        if(btnText.equals("Setup"))
        {
            tempSetupButton.setText("Disconnect");
            CameraStatus.setText("Camera Set Up \\o/");
            startCamera();
        }
        else
        {
            tempSetupButton.setText("Setup");
            CameraStatus.setText("Camera Disconnected \\o/");
        }

        isSetup = !isSetup;
    }

    // Function to simulate turning on the Camera
    @RequiresApi(api = Build.VERSION_CODES.P)
    @SuppressLint("SetTextI18n")
    public void on(){ // on was start

        // Checks if the system is setup or not
        if(isSetup)
        {
            CameraStatus.setText("Camera is Turned On \\o/");
            createCameraPreview();
        }
        else
        {
            CameraStatus.setText("No Camera to Turn On /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }
    }

    // Function to simulate turning off the Camera
    @SuppressLint("SetTextI18n")
    public void off(){ // was stop now off

        // Checks if the system is setup or not
        if(isSetup)
        {
            CameraStatus.setText("Camera is Turned Off \\o/");
            StopCamera();
        }
        else
        {
            CameraStatus.setText("No Camera to turn off /o\\");
            // https://developer.android.com/guide/topics/ui/notifiers/toasts
            Toast.makeText(this.context, "Please complete setup first.", Toast.LENGTH_SHORT).show();
        }
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
            String stringCameraID = cameraManager.getCameraIdList()[0];

            if (ActivityCompat.checkSelfPermission(this.context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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
    private void createCameraPreview()
    {
        SurfaceTexture surfaceTexture = CameraDisplay.getSurfaceTexture();
        Surface surface = new Surface(surfaceTexture);

        try {
            captureRequestBuilder = myCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            OutputConfiguration outputConfiguration = new OutputConfiguration(surface);

            SessionConfiguration sessionConfiguration = new SessionConfiguration(SessionConfiguration.SESSION_REGULAR,
                    Collections.singletonList(outputConfiguration),
                    this.context.getMainExecutor(),
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

    private void StopCamera()
    {
        try {
            myCameraCaptureSession.abortCaptures();
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
