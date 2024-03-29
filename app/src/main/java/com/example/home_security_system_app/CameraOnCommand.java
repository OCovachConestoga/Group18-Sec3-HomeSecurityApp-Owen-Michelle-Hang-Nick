package com.example.home_security_system_app;

import android.view.View;

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
    @Override
    public void executeOn(View view){

        // Runs the Camera start function
        cam.on(view);
    }

    // Implementation of off execution
    @Override
    public void executeOff(View view){

        // Runs the  stop function
        cam.off(view);
    }

}
