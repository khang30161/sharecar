package com.example.khang.sharecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registration extends AppCompatActivity {
    private EditText mREmail, mRPassword;
    private Button mSignup;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mREmail = findViewById(R.id.signup_input_email);
        mRPassword = findViewById(R.id.signup_input_password);
        mSignup = findViewById(R.id.btn_signup);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
        mAuth = FirebaseAuth.getInstance();
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this, DriverLoginActivity.class);
                startActivity(intent);
                finish();
                final String email = mREmail.getText().toString();
                final String password = mRPassword.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(Registration.this, "sign up error", Toast.LENGTH_SHORT).show();
                        } else {


                            Toast.makeText(Registration.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                        }

                   }

                });
                mAuth=FirebaseAuth.getInstance();
                String user_id = mAuth.getCurrentUser().getUid();
                databaseReference.child(user_id).setValue(true);
            };
                   });
    }
}

