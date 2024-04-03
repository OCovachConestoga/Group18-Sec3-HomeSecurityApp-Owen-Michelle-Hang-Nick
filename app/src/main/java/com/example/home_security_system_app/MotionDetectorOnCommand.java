package com.example.home_security_system_app;

import android.view.View;

/**
 * Concrete command class that handles the execution of the motion detector button presses
 */
public class MotionDetectorOnCommand implements Command{

    /**
     * Variable that holds on instance of the motion detector receiver
     */
    MotionDetector md;

    /**
     * Parameterized constructor
     * @param MD motion detector instance
     */
    MotionDetectorOnCommand(MotionDetector MD){
        md = MD;
    }

    /**
     * Function to execute setup function of the motion detector
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        md.setup(view);
    }

    /**
     * Function to execute start function of the motion detector
     */
    @Override
    public void executeOn(){

        // Runs the DoorLocks start function
        md.start();
    }

    /**
     * Function to execute stop function of the motion detector
     */
    @Override
    public void executeOff(){

        // Runs the DoorLocks stop function
        md.stop();
    }
}
