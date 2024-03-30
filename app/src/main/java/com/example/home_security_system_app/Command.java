package com.example.home_security_system_app;

import android.view.View;

// Interface Class that defines the interface for concrete command executions
public interface Command {

    // Definition of executeSetup function
    // View is needed because we are using android studio views
    void executeSetup(View view);

    // Definition of executeOn function
    // View is needed because we are using android studio views
    void executeOn();

    // Definition of executeOff function
    // View is needed because we are using android studio views
    void executeOff();
}
