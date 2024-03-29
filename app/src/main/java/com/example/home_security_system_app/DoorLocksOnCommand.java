package com.example.home_security_system_app;

import android.view.View;

// Concrete Command Class that implements the commands for DoorLocks
public class DoorLocksOnCommand implements Command {

    // Create variable that are used by this class
    DoorLocks dl;

    // Parameterized constructor
    DoorLocksOnCommand(DoorLocks DL){
        dl = DL;
    }

    // Implementation of setup execution
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        dl.setup(view);
    }

    // Function that handles the added setLockPin execution
    public void setLockPin(View view){

        // Runs the DoorLocks setLockPin function
        dl.setLockPin(view);
    }

    // Implementation of on execution
    @Override
    public void executeOn(View view){

        // Runs the DoorLocks lock function
        dl.lock(view);
    }

    // Implementation of off execution
    @Override
    public void executeOff(View view){

        // Runs the DoorLocks unlock function
        dl.unlock(view);
    }
}
