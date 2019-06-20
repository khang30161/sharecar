package com.example.khang.sharecar;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class InfoFragment extends Fragment {
    private TextView mName;
    private TextView mPhone;
    private TextView mEmail, mLocal, mJob, mFacebook;
    private TextView mGender;
    private ImageView mEdit;
    private Button mLogout;
    DatabaseReference databaseReference;

    public static InfoFragment newInstance() {

        return new InfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mName = view.findViewById(R.id.name);
        mPhone = view.findViewById(R.id.mobileNumber);
        mEmail = view.findViewById(R.id.email);
        mGender = view.findViewById(R.id.gender);
        mLocal=view.findViewById(R.id.tv_local);
        mJob=view.findViewById(R.id.tv_job);
        mFacebook=view.findViewById(R.id.facebookacc);
        mLogout=view.findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), StartActivity.class);
                startActivity(intent);
                return;
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User list=dataSnapshot.getValue(User.class);
                assert list != null;
                User user=new User();
                String email=list.getEmail();
                String phone=list.getNumberphone();
                String username= list.getUsername();
                String gender=list.getGender();
                String facebook=list.getFacebook();
                String local=list.getLocal();
                String job=list.getJob();
                mName.setText(username);
                mPhone.setText(phone);
                mGender.setText(gender);
                mEmail.setText(email);
                mLocal.setText(local);
                mFacebook.setText(facebook);
                mJob.setText(job);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}