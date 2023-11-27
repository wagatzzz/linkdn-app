package com.example.linkdn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Find the button by its ID
        Button = findViewById(R.id.loginButton);

        // Set a click listener for the button
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to open the login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);


                // Start the login activity
                startActivity(intent);
            }
        });
    }
}