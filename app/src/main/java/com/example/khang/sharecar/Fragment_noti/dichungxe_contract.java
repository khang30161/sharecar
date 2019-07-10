package com.example.khang.sharecar.Fragment_noti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khang.sharecar.ContactDichungxe;
import com.example.khang.sharecar.R;
import com.example.khang.sharecar.ShareCarAdapter;
import com.example.khang.sharecar.ShareManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class dichungxe_contract extends Fragment {


    List<ShareManager> shareManager = new ArrayList<>();
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    // private RentCarAdapter rentCarAdapter;
    private ShareCarAdapter shareCarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dichungxe_contract, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Share").child("post" + "");
        recyclerView = view.findViewById(R.id.rv_dichung_contract);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                shareManager.clear();
                for (DataSnapshot rentSnapshot : dataSnapshot.getChildren()) {
                    ShareManager shareManagers = rentSnapshot.getValue(ShareManager.class);
                    int seat = Integer.parseInt(shareManagers.getSeat());
                    if (shareManagers.getIsbooking1() == null && shareManagers.getIsBooking2() == null && shareManagers.getIsBooking3() == null && seat == 4) {
                        continue;
                    } else if (shareManagers.getIsbooking1() == null && shareManagers.getIsBooking2() == null && shareManagers.getIsBooking3() == null && shareManagers.getIsBooking4() == null && shareManagers.getIsBooking5() == null && seat == 6) {
                        continue;

                    } else {
                        shareManager.add(shareManagers);
                    }

                }
                shareCarAdapter = new ShareCarAdapter(shareManager, getContext(), new ShareCarAdapter.Action() {

                    @Override
                    public void onClickItem(ShareManager shareManager, int position) {
                        Intent intent = new Intent(getActivity(), ContactDichungxe.class);
                        intent.putExtra("shareManager", shareManager);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongClickItem(ShareManager shareManager, int position) {

                    }
                });
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(shareCarAdapter);
                //queryByUserId();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

    }

}
