package com.example.khang.sharecar;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoDetail extends AppCompatActivity {
    private TextView mName;
    private TextView mPhone;
    private TextView mEmail, mLocal, mJob, mFacebook;
    private TextView mGender;
    private ImageView mEdit;
    private Button mLogout;
    DatabaseReference databaseReference;
    String prefname = "my_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_detail);
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.mobileNumber);
        mEmail = findViewById(R.id.email);
        mGender = findViewById(R.id.gender);
        mLocal = findViewById(R.id.tv_local);
        mJob = findViewById(R.id.tv_job);
        mFacebook = findViewById(R.id.facebookacc);

    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences pre = getSharedPreferences(prefname, MODE_PRIVATE);
        String id = pre.getString("id", "");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User list=dataSnapshot.getValue(User.class);
                assert list != null;
                User user=new User();
                String email=list.getEmail();
                String phone=list.getNumberphone();
                String username= list.getUsername();
                String gender=list.getGender();
                String facebook=list.getFacebook();
                String local=list.getLocal();
                String job=list.getJob();
                mName.setText(username);
                mPhone.setText(phone);
                mGender.setText(gender);
                mEmail.setText(email);
                mLocal.setText(local);
                mFacebook.setText(facebook);
                mJob.setText(job);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
