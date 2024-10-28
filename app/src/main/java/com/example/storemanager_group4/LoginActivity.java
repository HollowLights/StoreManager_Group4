package com.example.storemanager_group4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.storemanager_group4.dao.UserDAO;
import com.example.storemanager_group4.database.DbHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText loginEmail, loginPassword;
    TextInputLayout loginEmailLayout, loginPasswordLayout;
    public Button loginButton;
    public TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));

        // Initialize UI components
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginEmailLayout = findViewById(R.id.loginEmailLayout);
        loginPasswordLayout = findViewById(R.id.loginPasswordLayout);
        loginButton = findViewById(R.id.loginButton);
        registerText = findViewById(R.id.registerText);

        // Initialize UserDAO and SharedPreferences
        UserDAO userDAO = new UserDAO(this);  // Use UserDAO to manage user-related database operations
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        // Check if the user is already logged in
        if (sharedPreferences.getBoolean("isLogin", false)) {
            // Direct to MainActivity if already logged in
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // Handle register text click
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Handle login button click
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user inputs
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                // Validate inputs
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Authenticate user using UserDAO
                if (userDAO.authenticateUser(email, password)) {
                    // Save login status in SharedPreferences
                    sharedPreferencesEditor.putBoolean("isLogin", true);
                    sharedPreferencesEditor.apply();

                    // Start MainActivity
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    // Show error message if authentication fails
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}