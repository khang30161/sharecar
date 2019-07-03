package com.example.khang.sharecar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShareCarCustomer extends AppCompatActivity {
    List<ShareManager> shareManager = new ArrayList<>();
    DatabaseReference databaseReference;
    FirebaseDatabase firebase;
    private RecyclerView recyclerView;
    private ShareCarAdapter shareCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_car_customer);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Share").child("post" + "");
        recyclerView = findViewById(R.id.rv_customer);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                for (DataSnapshot rentSnapshot : dataSnapshot.getChildren()) {
                    ShareManager shareManagers = rentSnapshot.getValue(ShareManager.class);
                    int seat=Integer.parseInt(shareManagers.getSeat());
                    if (shareManagers.getIsbooking1() != null && shareManagers.getIsBooking2() !=null && shareManagers.getIsBooking3()!=null && seat==4) {
                        continue;
                    } else if(shareManagers.getIsbooking1() != null && shareManagers.getIsBooking2() !=null && shareManagers.getIsBooking3()!=null && shareManagers.getIsBooking4() !=null && shareManagers.getIsBooking5() !=null && seat==6) {
                        continue;

                    }else {
                        shareManager.add(shareManagers);
                    }
                    //queryFilter();
                }
                shareCarAdapter = new ShareCarAdapter(this, shareManager,getApplicationContext(), new ShareCarAdapter.Action(){

                    @Override
                    public void onClickItem(ShareManager shareManager, int position) {
                        Intent intent = new Intent(ShareCarCustomer.this, SelectItemShareCustomer.class);
                        intent.putExtra("shareManager", shareManager);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClickItem(ShareManager shareManager, int position) {

                    }
                });
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(ShareCarCustomer.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(shareCarAdapter);
                //queryByUserId();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }}
