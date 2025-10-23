package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    public static final String EXTRA_EMAIL = "extra_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EditText emailInput = findViewById(R.id.emailInput);
        Button resetButton = findViewById(R.id.resetButton);
        TextView backToLogin = findViewById(R.id.backToLoginLink);

        resetButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(ForgetPasswordActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();
                return;
            }

            // In a real app you'd verify email and send link — for assignment just navigate to Reset screen
            Intent intent = new Intent(ForgetPasswordActivity.this, ResetPasswordActivity.class);
            intent.putExtra(EXTRA_EMAIL, email);
            startActivity(intent);
            finish();
        });

        backToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
