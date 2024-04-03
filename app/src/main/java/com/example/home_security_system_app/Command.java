package com.example.home_security_system_app;

import android.view.View;

/**
 * Interface class that defines the interface for concrete command executions
 */
public interface Command {

    /**
     * Definition of executeSetup function
     * View is needed because we are using android studio views
     * @param view holds the current window view
     */
    void executeSetup(View view);

    /**
     * Definition of executeOn function
     */
    void executeOn();

    /**
     * Definition of executeOff function
     */
    void executeOff();
}
