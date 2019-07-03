package com.example.khang.sharecar.FragmentPhanQuyen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.khang.sharecar.RentOtoInfomation;
import com.example.khang.sharecar.R;


public class thuexeoto extends Fragment {
    private Button mXeoto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thuexephanquyen, container, false);
        mXeoto=view.findViewById(R.id.btn_oto);
        mXeoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), RentOtoInfomation.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
