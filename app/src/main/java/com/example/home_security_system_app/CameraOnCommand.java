package com.example.home_security_system_app;

import android.os.Build;
import android.view.View;
import androidx.annotation.RequiresApi;

/**
 * Concrete command class that handles the execution of the camera button presses
 */
public class CameraOnCommand implements Command{

    /**
     * Variable that holds on instance of the camera receiver
     */
    Camera cam;

    /**
     * Parameterized constructor
     * @param CAM camera instance
     */
    CameraOnCommand(Camera CAM){
        cam = CAM;
    }

    /**
     * Function to execute setup function of the camera
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view){

        // Runs the Camera setup function
        cam.setup(view);
    }

    /**
     * Function to execute on function of the camera
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void executeOn(){

        // Runs the Camera start function
        cam.on();
    }

    /**
     * Function to execute off function of the camera
     */
    @Override
    public void executeOff(){

        // Runs the  stop function
        cam.off();
    }

}
