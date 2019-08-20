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

public class SignUp extends AppCompatActivity {

    EditText signin_email,signin_pass;
    Button signInButton,backToLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signin_email=findViewById(R.id.signupemail);
        signin_pass=findViewById(R.id.signupass);

        firebaseAuth=FirebaseAuth.getInstance();

        signInButton=findViewById(R.id.user_signup_button);
        backToLogin=findViewById(R.id.backtologin);

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInUser();
            }
        });
    }

    private void signInUser() {
        String signEmail = signin_email.getText().toString().trim();
        String signPass = signin_pass.getText().toString().trim();

        if (TextUtils.isEmpty(signEmail))
        {
            signin_email.setError("Empty field");
            signin_email.requestFocus();
        }
        if(TextUtils.isEmpty(signPass))
        {
            signin_pass.setError("Field Empty");
            signin_pass.requestFocus();
        }

        firebaseAuth.createUserWithEmailAndPassword(signEmail,signPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUp.this,"success",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SignUp.this,"Error occured",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
