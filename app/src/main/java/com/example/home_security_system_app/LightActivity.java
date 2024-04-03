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

/**
 * Android activity class that handles gui inputs for the application
 */
public class LightActivity extends AppCompatActivity {

    /**
     * attribute to hold an instance of the light concrete command
     */
    private LightOnCommand liCMD;

    /**
     * This function is a default constructor for this class, it initializes everything needed for this class
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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

    /**
     * Handles the setup button click
     * @param view holds the current window view
     */
    public void setupButton(View view) {

        // Run the door locks command execute function
        liCMD.executeSetup(view);
    }

    /**
     * Handles the light on button click
     * @param view holds the current window view
     */
    public void buttonOnLight(View view){

        // Run the door locks command execute function
        liCMD.executeOn();
    }

    /**
     * Handles the light off button click
     * @param view holds the current window view
     */
    public void buttonOffLight(View view) {

        // Run the light off execute function
        liCMD.executeOff();
    }
}




