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

public class RegisterActivity extends AppCompatActivity {
    EditText name , email , password , phone;
    Button register;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    TextView linkToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        register = findViewById(R.id.register);

        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();

        linkToLogin = findViewById(R.id.CreateText);



        if (fAuth.getCurrentUser() != null ){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mdp = password.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)) {
                    email.setError("email is required");
                    return;
                }

                if(TextUtils.isEmpty(mdp)) {
                    password.setError("password is required");
                    return;
                }

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(mEmail,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this,"user created successfully " ,Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }

                        else {
                            Toast.makeText(RegisterActivity.this," error " + task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        linkToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}