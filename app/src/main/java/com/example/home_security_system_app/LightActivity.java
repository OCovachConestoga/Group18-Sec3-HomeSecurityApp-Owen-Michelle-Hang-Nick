package com.example.home_security_system_app;
// imports
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// this class connects the GUI to the command pattern
public class LightActivity extends AppCompatActivity {

    // variable used by LightActivity class
    private LightOnCommand liCMD;

    // this function handles loading the view
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_light);

        // Creates a local TextView to pass to the Light object (for the timer)
        TextView lightDisplay = findViewById(R.id.lightTimerView);
        lightDisplay.setText("No Light Setup /o\\");

        // Create the necessary instances of the system for the command design pattern
        Light li = new Light(lightDisplay);
        liCMD = new LightOnCommand(li);
    }


    // Handles the setup button click
    public void setupLightButton(View view) {

        // Run the door locks command execute function
        liCMD.executeSetup(view);
    }

    // Handles the light on  button click
    public void buttonOnLight(View view){

        // Run the door locks command execute function
        liCMD.executeOn(view);
    }

    // Handles the light off button click
    public void buttonOffLight(View view){

        // Run the light off execute function
        liCMD.executeOff(view);
    }






        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

}




