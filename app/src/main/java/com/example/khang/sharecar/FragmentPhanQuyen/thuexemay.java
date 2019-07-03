package com.example.khang.sharecar.FragmentPhanQuyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khang.sharecar.R;
import com.example.khang.sharecar.RentBikeInformation;


public class thuexemay extends Fragment {

    private Button mXemay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dichungxe_phanquyen, container, false);
        mXemay=view.findViewById(R.id.btn_xemay);
        mXemay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RentBikeInformation.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
