package com.example.storemanager_group4.activity.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.storemanager_group4.R;
import com.example.storemanager_group4.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;


import java.util.Objects;

public class RegistrationActivity extends BaseActivity {
    TextInputEditText regName, regEmail, regPassword, regConformPassword, regPhone;
    ProgressBar progressBar;
    TextInputLayout regNameLayout, regPasswordLayout, regConformPasswordLayout, regEmailLayout, regPhoneLayout;
    Button registrationButton;
    TextView loginText;

    Boolean flog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        regName = findViewById(R.id.regName);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        regPhone = findViewById(R.id.regPhone);
        regConformPassword = findViewById(R.id.regConformPassword);
        registrationButton = findViewById(R.id.registerButton);
        loginText = findViewById(R.id.loginText);
        regNameLayout = findViewById(R.id.regNameLayout);
        regPasswordLayout = findViewById(R.id.regPasswordLayout);
        regConformPasswordLayout = findViewById(R.id.regConformPasswordLayout);
        regEmailLayout = findViewById(R.id.regEmailLayout);
        regPhoneLayout = findViewById(R.id.regPhoneLayout);
        progressBar = findViewById(R.id.progressBar);

        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFill()) {
                    progressBar.setVisibility(View.VISIBLE);
                    // Create a new User object
                    User user = new User();
                    user.setFullName(Objects.requireNonNull(regName.getText()).toString());
                    user.setEmail(Objects.requireNonNull(regEmail.getText()).toString());
                    user.setPassword(Objects.requireNonNull(regPassword.getText()).toString());
                    user.setPhone(Objects.requireNonNull(regPhone.getText()).toString());

                    // Use UserDAO to register the user
                    createUser(user);
                }
            }
        });
    }

    private void createUser(final User user) {
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = task.getResult().getUser().getUid();
                            User newUser = new User(
                                    userId,
                                    user.getFullName(),
                                    user.getPhone(),
                                    user.getEmail(),
                                    user.getPassword()
                            );
                            databaseReference.child("Users").child(userId).setValue(newUser)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "User Successfully Registered", Toast.LENGTH_LONG).show();
                                                // Save to SharedPreferences and navigate to LoginActivity
                                                saveUserDetailsAndNavigate(user);
                                            } else {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "Failed to save user data", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : "Registration failed";
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void saveUserDetailsAndNavigate(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", user.getFullName());
        editor.putString("email", user.getEmail());
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public Boolean isFill() {
        if (regName.getText() == null || regName.getText().toString().isEmpty()) {
            regNameLayout.setError("Error");
            regNameLayout.setErrorEnabled(true);
        } else {
            regNameLayout.setError(null);
            regNameLayout.setErrorEnabled(false);
            if (regEmail.getText() == null || regEmail.getText().toString().isEmpty()) {
                regEmailLayout.setError("Error");
                regEmailLayout.setErrorEnabled(true);
            } else {
                regEmailLayout.setError(null);
                regEmailLayout.setErrorEnabled(false);
                if (regPassword.getText() == null || regPassword.getText().toString().isEmpty()) {
                    regPasswordLayout.setError("Error");
                    regPasswordLayout.setErrorEnabled(true);
                } else {
                    regPasswordLayout.setError(null);
                    regPasswordLayout.setErrorEnabled(false);
                    if (regConformPassword.getText() == null || regConformPassword.getText().toString().isEmpty()) {
                        regConformPasswordLayout.setError("Error");
                        regConformPasswordLayout.setErrorEnabled(true);
                    } else {
                        if (regPassword.getText().toString().equals(regConformPassword.getText().toString())) {
                            regConformPasswordLayout.setError(null);
                            regConformPasswordLayout.setErrorEnabled(false);
                            return true;
                        } else {
                            regConformPasswordLayout.setError("Passwords don't match");
                            regConformPasswordLayout.setErrorEnabled(true);
                        }
                    }
                }
            }
        }
        return false;
    }
}
