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

import com.example.khang.sharecar.ContactRent;
import com.example.khang.sharecar.R;
import com.example.khang.sharecar.RentCarAdapter;
import com.example.khang.sharecar.RentManagers;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class thuexe_contract extends Fragment {
    List<RentManagers> rentManagers = new ArrayList<>();
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private RentCarAdapter rentCarAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thuexe_contract, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("post" + "");
        recyclerView = view.findViewById(R.id.rv_thue_contract);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.orderByChild("userId").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                rentManagers.clear();
                for (DataSnapshot rentSnapshot : dataSnapshot.getChildren()) {
                    RentManagers rentManager = rentSnapshot.getValue(RentManagers.class);
                    if (rentManager.getUserIdBook() == null) {
                        continue;
                    } else {
                        rentManagers.add(rentManager);

                    }

                }
                rentCarAdapter = new RentCarAdapter(rentManagers, getActivity(), new RentCarAdapter.Action() {
                    @Override
                    public void onClickItem(RentManagers manager, int position) {
                        Intent intent = new Intent(getActivity(), ContactRent.class);
                        intent.putExtra("rentManager", manager);
                        getActivity().startActivity(intent);

                    }

                    @Override
                    public void onLongClickItem(RentManagers manager, int position) {

                    }
                });
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(rentCarAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
