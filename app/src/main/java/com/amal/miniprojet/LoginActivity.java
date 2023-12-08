package com.amal.miniprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    TextView CAccount;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailVerif);
        password = findViewById(R.id.passwordVerif);

        progressBar = findViewById(R.id.progressBar);

        loginButton = findViewById(R.id.login);
        CAccount = findViewById(R.id.CreateAccount);

        fAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make sure fAuth is not null before using it
                if (fAuth != null) {
                    String mEmail = email.getText().toString().trim();
                    String mdp = password.getText().toString().trim();

                    if (TextUtils.isEmpty(mEmail)) {
                        email.setError("email is required");
                        return;
                    }

                    if (TextUtils.isEmpty(mdp)) {
                        password.setError("password is required");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    // authentication
                    fAuth.signInWithEmailAndPassword(mEmail, mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE); // Hide the progress bar
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "logged in successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish(); // Finish the LoginActivity to prevent going back
                            } else {
                                Toast.makeText(LoginActivity.this, "login failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    // Handle the case where fAuth is unexpectedly null
                    Toast.makeText(LoginActivity.this, "Authentication error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        CAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }
}
