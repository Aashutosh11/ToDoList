package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText login_email, login_pass;
    Button logIn, signUp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_email = findViewById(R.id.loginemail);
        login_pass = findViewById(R.id.loginpass);

        firebaseAuth = FirebaseAuth.getInstance();
        logIn = findViewById(R.id.loginbutton);
        signUp = findViewById(R.id.signbutton);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    private void login() {
        String loginemail = login_email.getText().toString().trim();
        String loginpass = login_pass.getText().toString().trim();

        if (TextUtils.isEmpty(loginemail)) {
            login_email.setError("Email field is empty");
            login_email.requestFocus();
        }
        if (TextUtils.isEmpty(loginpass)) {
            login_pass.setError("password field is empty");
            login_pass.requestFocus();
        }

        firebaseAuth.signInWithEmailAndPassword(loginemail, loginpass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(MainActivity.this, welcome.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "email and password is wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signup() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
