package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "1234";

    // SharedPreferences keys (optional: used if user registers)
    private static final String PREFS = "login_prefs";
    private static final String KEY_USERNAME = "saved_username";
    private static final String KEY_PASSWORD = "saved_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(v -> {
            EditText etName = findViewById(R.id.etName);
            EditText etPassword = findViewById(R.id.etPassword);
            TextView tvMessage = findViewById(R.id.tvMessage);
            etName.setText("");
            etPassword.setText("");
            tvMessage.setText("");
            Toast.makeText(MainActivity.this, "Fields are cleared!", Toast.LENGTH_SHORT).show();
        });

        // NAV: register & forgot password links
        TextView registerLink = findViewById(R.id.registerLink);
        TextView forgetLink = findViewById(R.id.forgetPasswordLink);

        registerLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
        });

        forgetLink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
            startActivity(intent);
        });
    }

    public void login(View v) {
        EditText etName = findViewById(R.id.etName);
        EditText etPassword = findViewById(R.id.etPassword);
        TextView tvMessage = findViewById(R.id.tvMessage);

        String username = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // check saved credentials first (if user used registration)
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String savedUser = prefs.getString(KEY_USERNAME, null);
        String savedPass = prefs.getString(KEY_PASSWORD, null);

        boolean ok = false;
        if (savedUser != null && savedPass != null) {
            if (username.equals(savedUser) && password.equals(savedPass)) ok = true;
        }

        // OR allow the built-in default admin credentials
        if (!ok) {
            if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) ok = true;
        }

        if (ok) {
            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            // Clear the back stack so user can't press Back to return to login after logout if you want:
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
            tvMessage.setText("Login failed. Please try again.");
        }
    }
}
