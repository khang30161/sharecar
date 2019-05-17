package com.example.khang.sharecar;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


public class RentCar extends AppCompatActivity {


    Button mXemay, mOto;
    List <RentManagers> rentManagers=new ArrayList<>();
    private RecyclerView recyclerView;
    private RentCarAdapter rentCarAdapter;
    private Realm realm=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_car);
        Realm.init(this);
        recyclerView=findViewById(R.id.rv_rentcar);
        recyclerView.setHasFixedSize(true);

        getData();

// get the reference of Button's
         mOto = (Button) findViewById(R.id.oto);
        mXemay = (Button) findViewById(R.id.xemay);

// perform setOnClickListener event on First Button
        mXemay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load First Fragment
                loadFragment(new XemayFragment());
            }
        });
// perform setOnClickListener event on Second Button
        mOto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                loadFragment(new DashboardFragment());
            }
        });

    }

    private void getData() {

        rentManagers = realm.where(RentManagers.class).findAll();
        rentCarAdapter = new RentCarAdapter(this, rentManagers, new RentCarAdapter.Action() {
            @Override
            public void onClickItem(RentManagers manager, int position) {

            }

            @Override
            public void onLongClickItem(RentManagers manager, int position) {

            }


        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(rentCarAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                if (requestCode == 4545) {
                    setDatabyBundle(data);
                }
                break;

            case Activity.RESULT_CANCELED:
                if (requestCode == 4545) {
                    Toast.makeText(this, "RESULT_CANCELED 4545", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setDatabyBundle(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(WantRentActivity.BUNDLE);
            if (bundle != null) {
               String location = bundle.getString(WantRentActivity.LOCATION);
                 String startdate = bundle.getString(WantRentActivity.STARTDATE);
                String enddate=bundle.getString(WantRentActivity.ENDDATE);
                String price = bundle.getString(WantRentActivity.PRICE);
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("RentManager").push();


            databaseReference.setValue(true);

            }
        }
    };


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

