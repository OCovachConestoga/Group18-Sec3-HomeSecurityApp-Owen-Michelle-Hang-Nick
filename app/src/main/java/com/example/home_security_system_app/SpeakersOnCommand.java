package com.example.home_security_system_app;

import android.view.View;

/**
 * Concrete command class that handles the execution of the speaker button presses
 */
public class SpeakersOnCommand implements Command{

    /**
     * Variable that holds on instance of the speaker receiver
     */
    Speakers speakers;

    /**
     * Parameterized constructor
     * @param sp speaker instance
     */
    SpeakersOnCommand(Speakers sp) {speakers = sp;}

    /**
     * Function to execute setup function of the speakers
     * @param view holds the current window view
     */
    @Override
    public void executeSetup(View view) {
        speakers.setup(view);
    }

    /**
     * Function to execute on function of the speakers
     */
    @Override
    public void executeOn() {
        speakers.turnon();
    }
    /**
     * Function to execute off function of the speakers
     */
    @Override
    public void executeOff() {
        speakers.turnoff();
    }
}
