package com.example.storemanager_group4.activity.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.storemanager_group4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends BaseActivity {

    private TextInputEditText loginEmail, loginPassword;
    private TextInputLayout loginEmailLayout, loginPasswordLayout;
    private SharedPreferences sharedPreferences;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));

        // Initialize UI components
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        loginEmailLayout = findViewById(R.id.loginEmailLayout);
        loginPasswordLayout = findViewById(R.id.loginPasswordLayout);
        findViewById(R.id.loginButton).setOnClickListener(v -> attemptLogin());
        findViewById(R.id.registerText).setOnClickListener(v -> navigateToRegister());
        progressBar = findViewById(R.id.progressBar);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);

        // Check if the user is already logged in
        if (sharedPreferences.getBoolean("isLogin", false)) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    private void attemptLogin() {
        progressBar.setVisibility(View.VISIBLE);
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);

                            // Login successful
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("isLogin", true);
                            editor.apply();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void navigateToRegister() {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        finish();
    }
}
