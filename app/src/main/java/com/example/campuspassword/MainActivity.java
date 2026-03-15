package com.example.campuspassword;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.campuspassword.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String BACKGROUNDCOLOROFTHEROOMWALL = "Cream";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.submitBtn.setOnClickListener(v -> validateLogin());
    }

    public void validateLogin () {

        String studentID = binding.studentID.getText().toString().trim();
        String password = binding.password.getText().toString().trim();

        if(studentID.length() < 2) {
            binding.result.setText("Invalid Input!");
            binding.result.setTextColor(Color.RED);
            return;
        }

        String lastTwoDigits = studentID.substring(studentID.length() - 2);
        String correctPassword = BACKGROUNDCOLOROFTHEROOMWALL + lastTwoDigits;

        if(password.equals(correctPassword)) {
            binding.result.setText("Access Granted!");
            binding.result.setTextColor(Color.GREEN);
        } else {
            binding.result.setText("Access Denied!");
            binding.result.setTextColor(Color.RED);
        }

    }
}