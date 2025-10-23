package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private static final String PREFS = "login_prefs";
    private static final String KEY_USERNAME = "saved_username";
    private static final String KEY_PASSWORD = "saved_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EditText newUsername = findViewById(R.id.newUsername);
        EditText newEmail = findViewById(R.id.newEmail);
        EditText newPassword = findViewById(R.id.newPassword);
        Button registerButton = findViewById(R.id.registerButton);
        TextView loginLink = findViewById(R.id.loginLink);

        registerButton.setOnClickListener(v -> {
            String username = newUsername.getText().toString().trim();
            String email = newEmail.getText().toString().trim();
            String password = newPassword.getText().toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Enter username", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to SharedPreferences (simple local persistence for this assignment)
            SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_USERNAME, username);
            editor.putString(KEY_PASSWORD, password);
            editor.apply();

            Toast.makeText(RegistrationActivity.this, "Registration successful. Please login.", Toast.LENGTH_SHORT).show();

            // Navigate back to login
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            // Clear existing activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
