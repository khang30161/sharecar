package com.example.khang.sharecar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OtoFragment extends Fragment {
    private Button mWantcaroto, mNeedcaroto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_oto, container, false);
        mNeedcaroto=view.findViewById(R.id.need_to_rent_car);
        mWantcaroto=view.findViewById(R.id.want_to_rent_car);
        mWantcaroto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), WantRentActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }

}
