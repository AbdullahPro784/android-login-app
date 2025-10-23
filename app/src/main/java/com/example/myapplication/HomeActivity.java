package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView welcomeMessage;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeMessage = findViewById(R.id.welcomeMessage);
        logoutButton = findViewById(R.id.logoutButton);

        String username = getIntent().getStringExtra("username");
        if (username != null && !username.isEmpty()) {
            welcomeMessage.setText("Welcome, " + username + "!");
        } else {
            welcomeMessage.setText("Welcome!");
        }

        logoutButton.setOnClickListener(v -> {
            // On logout, return to login screen
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            // clear activity stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
