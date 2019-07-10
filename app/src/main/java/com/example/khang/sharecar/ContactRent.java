package com.example.khang.sharecar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactRent extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView mStartday, mEndday, mPrice, mLocation, mIntro, mInfo, mUser, mIsbook;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_rent);
        mStartday = findViewById(R.id.startday12);
        mEndday = findViewById(R.id.endday12);
        mLocation = findViewById(R.id.location12);
        mPrice = findViewById(R.id.price12);
        TextView exit = findViewById(R.id.exit);
        imageView = findViewById(R.id.line4_iv);
        mUser = findViewById(R.id.namee);
        mIsbook = findViewById(R.id.isboking);
        mIntro = findViewById(R.id.tv_intro12);
        RentManagers rentManager = (RentManagers) getIntent().getSerializableExtra("rentManager");
        mIsbook.setText(rentManager.getUserIdBook());
        mLocation.setText(rentManager.getLocation());
        mStartday.setText(rentManager.getStartdate());
        mEndday.setText(rentManager.getEnddate());
        mPrice.setText(rentManager.getPrice());
        mIntro.setText(rentManager.getIntro());
        mUser.setText(rentManager.getUserId());
        Glide.with(ContactRent.this).load(rentManager.getUrl()).into(imageView);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mIsbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactRent.this, InfoDetail.class);
                String prefname = "my_data";
                SharedPreferences pre = ContactRent.this.getSharedPreferences(prefname, MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                String id = mIsbook.getText().toString();


                editor.putString("id", id);

                editor.apply();
                startActivity(intent);

            }

        });


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(uid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User list = dataSnapshot.getValue(User.class);
                assert list != null;

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



