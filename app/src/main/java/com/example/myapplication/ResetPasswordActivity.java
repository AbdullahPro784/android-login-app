package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private static final String PREFS = "login_prefs";
    private static final String KEY_USERNAME = "saved_username";
    private static final String KEY_PASSWORD = "saved_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        EditText newPass = findViewById(R.id.newPass);
        EditText confirmPass = findViewById(R.id.confirmPass);
        Button updatePassBtn = findViewById(R.id.updatePassButton);
        TextView backToLogin = findViewById(R.id.loginLinkFromReset);

        // Optional: show email passed from ForgetPasswordActivity
        String email = getIntent().getStringExtra(ForgetPasswordActivity.EXTRA_EMAIL);
        if (email != null) {
            // you could display it somewhere; skipping UI change to keep layout unchanged
        }

        updatePassBtn.setOnClickListener(v -> {
            String p1 = newPass.getText().toString().trim();
            String p2 = confirmPass.getText().toString().trim();

            if (p1.isEmpty() || p2.isEmpty()) {
                Toast.makeText(ResetPasswordActivity.this, "Enter both password fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!p1.equals(p2)) {
                Toast.makeText(ResetPasswordActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save updated password — if there's a saved username, keep it; otherwise we can prompt user later
            SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
            String savedUser = prefs.getString(KEY_USERNAME, null);

            SharedPreferences.Editor editor = prefs.edit();
            if (savedUser != null) {
                editor.putString(KEY_PASSWORD, p1);
                editor.apply();
                Toast.makeText(ResetPasswordActivity.this, "Password updated. Please login.", Toast.LENGTH_SHORT).show();
            } else {
                // no saved user — optionally create a default user record
                editor.putString(KEY_USERNAME, "user"); // placeholder username
                editor.putString(KEY_PASSWORD, p1);
                editor.apply();
                Toast.makeText(ResetPasswordActivity.this, "Password set. Please login.", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        backToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(ResetPasswordActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
