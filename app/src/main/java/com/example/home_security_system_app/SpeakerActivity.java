package com.example.home_security_system_app;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SpeakerActivity extends AppCompatActivity {

    private TextView speakerDisplay;

    private Button setupButton;

    private Boolean isSetup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_speaker);

        speakerDisplay = findViewById(R.id.speakerView);
        speakerDisplay.setText("No Speaker is Connected!");

    }

    public void buttonSetup(View view)
    {
        setupButton = findViewById(R.id.setupButton);
        speakerDisplay = findViewById(R.id.speakerView);

        String buttonText = setupButton.getText().toString();

        if(buttonText.equals("Setup"))
        {
            setupButton.setText("Reset");
            speakerDisplay.setText("Speaker Sucessfully Connected");
        }
        else
        {
            setupButton.setText("Setup");
            speakerDisplay.setText("Speaker Sucessfully Disconnected");
        }

        isSetup = !isSetup;
    }
}