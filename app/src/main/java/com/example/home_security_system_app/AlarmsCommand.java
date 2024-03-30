package com.example.home_security_system_app;

import android.view.View;

public class AlarmsCommand implements Command {

    Alarms alarms;

    AlarmsCommand(Alarms al) {alarms = al;}

    @Override
    public void executeSetup(View view) {
        alarms.setup(view);
    }

    @Override
    public void executeOn() {
        alarms.turnon();
    }

    @Override
    public void executeOff() {
        alarms.turnoff();
    }
}
