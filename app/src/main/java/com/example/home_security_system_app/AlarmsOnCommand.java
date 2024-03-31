package com.example.home_security_system_app;

import android.view.View;

/**
 * Concrete command class that handles the execution of the alarm button presses
 */
public class AlarmsOnCommand implements Command {

    /**
     * Variable that holds on instance of the alarm receiver
     */
    Alarms alarms;

    /**
     * Parameterized constructor
     * @param al alarm instance
     */
    AlarmsOnCommand(Alarms al) {alarms = al;}

    /**
     * Function to execute setup function of the alarm
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view) {
        alarms.setup(view);
    }

    /**
     * Function to execute on function of the alarm
     */
    @Override
    public void executeOn() {
        alarms.turnon();
    }

    /**
     * Function to execute off function of the alarm
     */
    @Override
    public void executeOff() {
        alarms.turnoff();
    }
}
