package com.example.khang.sharecar;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class SelectItem extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView mStartday, mEndday, mPrice, mLocation;
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


        databaseReference= FirebaseDatabase.getInstance().getReference().child("image");

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot chidSnap : dataSnapshot.getChildren()) {
                    Log.d("", "onChildAdded:" + dataSnapshot.getKey());
                    RentManagers managers=new RentManagers();
                    String mStartday=managers.getStartdate();
                    String mEn=managers.getEnddate();




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
