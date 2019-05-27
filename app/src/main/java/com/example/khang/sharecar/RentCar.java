package com.example.khang.sharecar;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class RentCar extends AppCompatActivity {


    Button mXemay, mOto;
    List <RentManagers> rentManagers=new ArrayList<>();
    private RecyclerView recyclerView;
    private RentCarAdapter rentCarAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        databaseReference=FirebaseDatabase.getInstance().getReference("image");


        recyclerView=findViewById(R.id.rv_rentcar);
        recyclerView.setHasFixedSize(true);

         mOto = (Button) findViewById(R.id.oto);
        mXemay = (Button) findViewById(R.id.xemay);

        mXemay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new XemayFragment());
            }
        });
// perform setOnClickListener event on Second Button
        mOto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                loadFragment(new OtoFragment());
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot rentSnapshot :dataSnapshot.getChildren()) {
            RentManagers list=rentSnapshot.getValue(RentManagers.class);
            RentManagers rentManager=new RentManagers();
                assert list != null;
                String location=list.getLocation();
            String startday=list.getStartdate();
            String enddate=list.getEnddate();
            String price=list.getPrice();
            String urlim=list.getUrl();
            rentManager.setLocation(location);
            rentManager.setStartdate(startday);
            rentManager.setEnddate(enddate);
            rentManager.setPrice(price);
            rentManager.setUrl(urlim);
            rentManagers.add(rentManager);
            }
                rentCarAdapter = new RentCarAdapter(rentManagers, getApplicationContext());
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RentCar.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(rentCarAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }






    private void loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit();
        }
    }
}

