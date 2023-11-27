package com.example.linkdn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Spinner genderDropdown;
    private Button signupButton;
    private EditText usernameInput, emailInput, passwordInput, confirmPasswordInput, bioInput, skillsInput, phoneInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        usernameInput = findViewById(R.id.usernameInput);
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        confirmPasswordInput = findViewById(R.id.confirmPasswordInput);
        bioInput = findViewById(R.id.bioInput);
        skillsInput = findViewById(R.id.skillsInput);
        phoneInput = findViewById(R.id.phoneInput);
        genderDropdown = findViewById(R.id.genderDropdown);
        signupButton = findViewById(R.id.signupButton);


        // Set up the Spinner with gender options
        String[] genders = new String[]{"Choose gender", "Male", "Female", "Non-Binary", "Prefer Not To Say"};

// Set up the Spinner with gender options
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Set the prompt as the first item in the Spinner
        genderDropdown.setAdapter(genderAdapter);
        genderDropdown.setSelection(0);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                String username = usernameInput.getText().toString().trim();
                String confirm = confirmPasswordInput.getText().toString().trim();
                String bio = bioInput.getText().toString().trim();
                String skills = skillsInput.getText().toString().trim();
                String phone = phoneInput.getText().toString().trim();
                String gender = genderDropdown.getSelectedItem().toString();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all required fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirm)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign up success, store user information in the database
                                    String userId = mAuth.getCurrentUser().getUid();
                                    User newUser = new User(username, email, bio, skills, phone, gender);

                                    // Push the user data to the database under the user's ID
                                    mDatabase.child("users").child(userId).setValue(newUser);
                                    Toast.makeText(SignUpActivity.this, "Sign Up successful! ", Toast.LENGTH_SHORT).show();


                                } else {
                                    // If sign up fails, display a message to the user.
                                    Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }

                            private void updateUI(Object o) {

                            }
                        });

            }


        });
    }
}