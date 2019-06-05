package com.example.khang.sharecar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

public class Registration extends AppCompatActivity {
    private EditText mREmail, mRPassword, mUsername,mAge, mNumberphone ;
    private RadioButton mGenderMale;
    private Button mSignup;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mREmail = findViewById(R.id.signup_input_email);
        mRPassword = findViewById(R.id.signup_input_password);
        mUsername=findViewById(R.id.signup_input_name);
        mAge=findViewById(R.id.signup_input_age);
        mNumberphone=findViewById(R.id.signup_input_phone);
        mGenderMale=findViewById(R.id.male_radio_btn);
        mSignup = findViewById(R.id.btn_signup);
        (new Date()).getTime();
        new Date(1559564113903l);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
        mAuth = FirebaseAuth.getInstance();
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mRPassword.equals(mRPassword) == true){

                }
                final String email = mREmail.getText().toString();
                final String password = mRPassword.getText().toString();
                final String username=mUsername.getText().toString();
                final int age=Integer.parseInt(mAge.getText().toString());
                final String number=mNumberphone.getText().toString();
                final int gender=(mGenderMale.isChecked()==true)? 1 : 0;
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String user_id = mAuth.getCurrentUser().getUid();
                            User user=new User(email,user_id, username,age, gender,number);
                            databaseReference.child(user_id).setValue(user);

                            Toast.makeText(Registration.this, "sign up is Successful", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Registration.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {


                            Toast.makeText(Registration.this, "Sign Up Error", Toast.LENGTH_SHORT).show();
                        }

                   }

                });
            };
                   });
    }
}

