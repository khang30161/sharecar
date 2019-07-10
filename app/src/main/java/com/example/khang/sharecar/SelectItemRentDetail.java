package com.example.khang.sharecar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SelectItemRentDetail extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView mStartday, mEndday, mPrice, mLocation, mIntro, mInfo, mUser;
    ImageView imageView;
    Button mDel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thuexe_detail);
        mStartday = findViewById(R.id.startday12);
        mEndday = findViewById(R.id.endday12);
        mLocation = findViewById(R.id.location12);
        mPrice = findViewById(R.id.price12);
        mDel = findViewById(R.id.exit);
        imageView = findViewById(R.id.line4_iv);
        mUser = findViewById(R.id.namee);
        mIntro = findViewById(R.id.tv_intro12);
        RentManagers rentManager = (RentManagers) getIntent().getSerializableExtra("rentManager");
        mLocation.setText(rentManager.getLocation());
        mStartday.setText(rentManager.getStartdate());
        mEndday.setText(rentManager.getEnddate());
        mPrice.setText(rentManager.getPrice());
        mIntro.setText(rentManager.getIntro());
        mUser.setText(rentManager.getUserId());
        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectItemRentDetail.this, InfoDetail.class);
                String prefname = "my_data";
                SharedPreferences pre = SelectItemRentDetail.this.getSharedPreferences(prefname, MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                String id = mUser.getText().toString();


                editor.putString("id", id);

                editor.apply();
                startActivity(intent);

            }

        });
        String id = mUser.getText().toString();

        Glide.with(SelectItemRentDetail.this).load(rentManager.getUrl()).into(imageView);
        //databaseReference = FirebaseDatabase.getInstance().getReference("post").child(id);
        mDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String key=dataSnapshot.getKey();
                        databaseReference = FirebaseDatabase.getInstance().getReference("post").child(key);
                        databaseReference. removeValue();
                        Toast.makeText(SelectItemRentDetail.this, "Đã xóa", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
