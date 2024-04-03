package com.example.home_security_system_app;

import android.view.View;

/**
 * Concrete command class that handles the execution of the light button presses
 */
public class LightOnCommand implements Command{

    /**
     * Variable that holds on instance of the light receiver
     */
    Light li;

    /**
     * Parameterized constructor
     * @param LI light instance
     */
    LightOnCommand(Light LI){
        li = LI;
    }

    /**
     * Function to execute setup function of the light
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view){

        // Runs the DoorLocks setup function
        li.setup(view);
    }

    /**
     * Function to execute on function of the light
     */
    @Override
    public void executeOn(){

        // Runs the DoorLocks start function
        li.on();
    }

    /**
     * Function to execute off function of the light
     */
    @Override
    public void executeOff(){

        // Runs the  stop function
        li.off();
    }
}
