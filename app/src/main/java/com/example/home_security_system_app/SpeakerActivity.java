package com.example.home_security_system_app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SpeakerActivity extends AppCompatActivity {

    private SpeakersCommand spkCMD;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Switches the view on the app to the proper view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        // Creates a local TextView to pass to the Speakers object
        TextView speakerDisplay = findViewById(R.id.speakerView);
        speakerDisplay.setText("No Speakers Setup /o\\");

        // Create the necessary instances of the system for the command design pattern
        Speakers speaker = new Speakers(speakerDisplay);
        spkCMD = new SpeakersCommand(speaker);

    }

    // Handles the setup button click
    public void buttonSetup(View view){
        // Run the door locks command execute function
        spkCMD.executeSetup(view);
    }

    // Handles the Start Speaker button click
    public void buttonStartSpeaker(View view){

        // Run the door locks command execute function
        spkCMD.executeOn(view);
    }

    // Handles the unlock doors button click
    public void buttonStopSpeaker(View view){

        // Run the door locks command execute function
        spkCMD.executeOff(view);
    }
}