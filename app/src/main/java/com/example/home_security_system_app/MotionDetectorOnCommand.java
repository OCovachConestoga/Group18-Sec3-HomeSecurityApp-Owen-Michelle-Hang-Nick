package com.example.home_security_system_app;

import android.view.View;

// Concrete Command Class that implements the commands for the MotionDetector
public class MotionDetectorOnCommand implements Command{

    // Create variable that are used by this class
    MotionDetector md;

    // Parameterized constructor
    MotionDetectorOnCommand(MotionDetector MD){
        md = MD;
    }

    // Implementation of setup execution
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        md.setup(view);
    }

    // Implementation of on execution
    @Override
    public void executeOn(View view){

        // Runs the DoorLocks start function
        md.start(view);
    }

    // Implementation of off execution
    @Override
    public void executeOff(View view){

        // Runs the DoorLocks stop function
        md.stop(view);
    }
}
