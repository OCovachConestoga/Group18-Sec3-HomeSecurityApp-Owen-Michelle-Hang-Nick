package com.example.home_security_system_app;

import android.view.View;

public class SpeakersOnCommand implements Command{

    Speakers speakers;

    // Constructor
    SpeakersOnCommand(Speakers sp) {speakers = sp;}

    @Override
    public void executeSetup(View view) {
        speakers.setup(view);
    }

    @Override
    public void executeOn() {
        speakers.turnon();
    }

    @Override
    public void executeOff() {
        speakers.turnoff();
    }
}
