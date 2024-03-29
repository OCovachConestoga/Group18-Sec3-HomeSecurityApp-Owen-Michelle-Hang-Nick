package com.example.home_security_system_app;

import android.view.View;

// Concrete Command Class that implements the commands for the Light
public class LightOnCommand implements Command{

    // Create variable used by this class
    Light li;

    // Parameterized constructor
    LightOnCommand(Light LI){
        li = LI;
    }

    // Implementation of setup execution
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        li.setup(view);
    }

    // Implementation of on execution
    @Override
    public void executeOn(View view){

        // Runs the DoorLocks start function
        li.on(view);
    }

    // Implementation of off execution
    @Override
    public void executeOff(View view){

        // Runs the  stop function
        li.off(view);
    }

    // Function that handles the added light timer execution
    public void setTimer(View view){

        // Runs the DoorLocks setLockPin function
        li.setTimer(view);
    }
}





