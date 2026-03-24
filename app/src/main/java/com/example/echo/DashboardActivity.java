package com.example.echo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.echo.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    private static final String TAG = "DashboardActivityLog";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d(TAG, "onCreate method has successfully created.");

        Intent receivedIntent = getIntent();
        if (receivedIntent != null && receivedIntent.hasExtra("MESSAGE_KEY")) {
            String receivedString = receivedIntent.getStringExtra("MESSAGE_KEY");
            binding.messageDisplay.setText(receivedString);
            Log.d(TAG, "Message successfully displayed: " + receivedString);
        } else {
            Log.d(TAG, "No message received in Intent.");
        }
    }
}