package com.example.home_security_system_app;

import android.view.View;

/**
 * Concrete command class that handles the execution of the doorlocks button presses
 */
public class DoorLocksOnCommand implements Command {

    /**
     * Variable that holds on instance of the doorlocks receiver
     */
    DoorLocks dl;

    /**
     * Parameterized constructor
     * @param DL doorlocks instance
     */
    DoorLocksOnCommand(DoorLocks DL){
        dl = DL;
    }

    /**
     * Function to execute setup function of the doorlocks
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        dl.setup(view);
    }

    /**
     * Function that handles the added setLockPin execution
     */
    public void setLockPin(){

        // Runs the DoorLocks setLockPin function
        dl.setLockPin();
    }

    /**
     * Implementation of on execution
     */
    @Override
    public void executeOn(){

        // Runs the DoorLocks lock function
        dl.lock();
    }

    /**
     * Implementation of off execution
     */
    @Override
    public void executeOff(){

        // Runs the DoorLocks unlock function
        dl.unlock();
    }
}
