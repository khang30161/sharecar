package com.example.khang.sharecar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class XemayFragment extends Fragment {
    private Button mChothue, mMuonthue;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_xemay, container, false);
        mChothue=view.findViewById(R.id.want_to_rent);
        mMuonthue=view.findViewById(R.id.need_to_rent);
        mChothue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WantRentActivity.class);
                startActivity(intent);


            }
        });
        mMuonthue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NeedRentActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}


