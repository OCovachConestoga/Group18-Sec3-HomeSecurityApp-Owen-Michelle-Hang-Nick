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
    public void executeOn(){

        // Runs the DoorLocks start function
        li.on();
    }

    // Implementation of off execution
    @Override
    public void executeOff(){

        // Runs the  stop function
        li.off();
    }

    // Function that handles the added light timer execution
    public void setTimer(){

        // Runs the DoorLocks setLockPin function
        li.setTimer();
    }
}
