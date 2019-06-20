package com.example.khang.sharecar;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SelectItem extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView mStartday, mEndday, mPrice, mLocation, mIntro, mInfo, mUser;
    ImageView imageView;
    Button mSelect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seclect_item);
        mStartday=findViewById(R.id.startday);
        mEndday=findViewById(R.id.endday);
        mLocation=findViewById(R.id.location);
        mPrice=findViewById(R.id.price);
        mSelect =findViewById(R.id.btn_select);
        imageView=findViewById(R.id.line4_iv);
        mUser=findViewById(R.id.namee);
        mIntro=findViewById(R.id.tv_intro);
        RentManagers rentManager= (RentManagers) getIntent().getSerializableExtra("rentManager");
        mLocation.setText(rentManager.getLocation());
        mStartday.setText(rentManager.getStartdate());
        mEndday.setText(rentManager.getEnddate());
        mPrice.setText(rentManager.getPrice());
        mIntro.setText(rentManager.getIntro());
        mUser.setText(rentManager.getUserId());
        Glide.with(SelectItem.this).load(rentManager.getUrl()).into(imageView);

        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();


        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User list=dataSnapshot.getValue(User.class);
                assert list != null;

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentManagers rentManager= (RentManagers) getIntent().getSerializableExtra("rentManager");
                rentManager.setUserIdBook(FirebaseAuth.getInstance().getCurrentUser().getUid());

                FirebaseDatabase.getInstance().getReference().child("post").child(rentManager.getId()).setValue(rentManager);
                Toast.makeText(SelectItem.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



}
