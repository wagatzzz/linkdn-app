package com.example.linkdn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class contact extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String userEmail; // Store user email for both actions
    private String userPhone; // Store user phone for the call action

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        if (intent != null) {
            String username = intent.getStringExtra("username");
            userEmail = intent.getStringExtra("email");
            String bio = intent.getStringExtra("bio");
            String skills = intent.getStringExtra("skills");
            userPhone = intent.getStringExtra("phone");
            String gender = intent.getStringExtra("gender");

            // Check for null values before using them
            if (userEmail == null) {
                Toast.makeText(this, "Email address is missing", Toast.LENGTH_SHORT).show();
            }

            if (userPhone == null) {
                Toast.makeText(this, "Phone number is missing", Toast.LENGTH_SHORT).show();
            }
        }

        // Find the "Send" and "Call" Buttons by their IDs
        Button sendButton = findViewById(R.id.sendButton);
        Button callButton = findViewById(R.id.callButton);

        // Set an OnClickListener to the "Send" Button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open an email intent with the email address in the "TO" field
                composeEmail(userEmail);
            }
        });

        // Set an OnClickListener to the "Call" Button
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Make a phone call with the stored phone number
                makePhoneCall(userPhone);
            }
        });
    }

    // Method to compose an email intent
    @SuppressLint("QueryPermissionsNeeded")
    private void composeEmail(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to make a phone call
    private void makePhoneCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No phone app found", Toast.LENGTH_SHORT).show();
        }
    }
}
