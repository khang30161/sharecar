package com.example.khang.sharecar;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SelectItem extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView mStartday, mEndday, mPrice, mLocation, mIntro;
    ImageView imageView;
    Button mSelect;
    DataSnapshot rentSnapshot;

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
        mIntro=findViewById(R.id.tv_intro);
        RentManagers rentManager= (RentManagers) getIntent().getSerializableExtra("rentManager");
        mLocation.setText(rentManager.getLocation());
        mStartday.setText(rentManager.getStartdate());
        mEndday.setText(rentManager.getEnddate());
        mPrice.setText(rentManager.getPrice());
        mIntro.setText(rentManager.getIntro());

        Glide.with(SelectItem.this).load(rentManager.getUrl()).into(imageView);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("image");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
