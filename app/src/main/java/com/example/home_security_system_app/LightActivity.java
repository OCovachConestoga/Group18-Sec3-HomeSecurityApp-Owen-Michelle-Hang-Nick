package com.example.home_security_system_app;
// imports
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        // Initialize Firebase DB
        // Firebase Database reference
        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

        // Creates a local TextView to pass to the Light object (for the timer)
        TextView lightDisplay = findViewById(R.id.lightTimerView);
        lightDisplay.setText("No Light Setup /o\\");

        // Create the necessary instances of the system for the command design pattern
        Light li = new Light(lightDisplay, myDatabase, this);
        liCMD = new LightOnCommand(li);
    }


    // Handles the setup button click
    public void setupButton(View view) {

        // Run the door locks command execute function
        liCMD.executeSetup(view);
    }

    // Handles the light on  button click
    public void buttonOnLight(View view){

        // Run the door locks command execute function
        liCMD.executeOn();
    }

    // Handles the light off button click
    public void buttonOffLight(View view) {

        // Run the light off execute function
        liCMD.executeOff();
    }
}




