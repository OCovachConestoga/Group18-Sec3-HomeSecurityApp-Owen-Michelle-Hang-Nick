package com.example.home_security_system_app;

import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

// Concrete Command Class that implements the commands for the Camera
public class CameraOnCommand implements Command{

    // Create variable used by this class
    Camera cam;

    // Parameterized constructor
    CameraOnCommand(Camera CAM){
        cam = CAM;
    }

    // Implementation of setup execution
    @Override
    public void executeSetup(View view){

        // Runs the Camera setup function
        cam.setup(view);
    }

    // Implementation of on execution
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void executeOn(){

        // Runs the Camera start function
        cam.on();
    }

    // Implementation of off execution
    @Override
    public void executeOff(){

        // Runs the  stop function
        cam.off();
    }

}
