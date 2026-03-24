package com.example.echo;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;
import com.example.echo.databinding.ActivityMainBinding;
import android.content.Intent;
import android.widget.Toast;

import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = " MainActivityLog ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG," onCreate method has successfully started.") ;

        binding.submitBtnExplicit.setOnClickListener(v ->{
            Log.i(TAG ,"The user clicked the Explicit button!");
            Toast.makeText(MainActivity.this," Button Clicked !", Toast.LENGTH_SHORT).show();
            Intent explicitIntent = new Intent(MainActivity.this, DashboardActivity.class);
            explicitIntent.putExtra("MESSAGE_KEY", binding.messageBox.getText().toString());
            startActivity(explicitIntent);
        });

        binding.submitBtnImplicit.setOnClickListener(v ->{
            Log.i(TAG, "The user clicked the Implicit button!");
            Toast.makeText(MainActivity.this," Button Clicked !", Toast.LENGTH_SHORT).show();
            Intent implicitIntent = new Intent(Intent.ACTION_VIEW);
            implicitIntent.setData(Uri.parse("https://developer.android.com"));
            startActivity(implicitIntent) ;
        });

    }
}